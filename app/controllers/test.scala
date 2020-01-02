/*
---->FROM ROUTES DOWNLOAD FUNCTION IS CALLED IN s3Service case class
---->FOR THE FILE DOWNLOAD localhost/task/download/FILENALE with extension
      eg http://localhost:9000/task/Download/test.csv
---->FOR ZIP DOWNLOAD FROM THE S3 ON THE FLY  localhost/task/download/NAME OF THE FOLDER
      eg http://localhost:9000/task/Download/test
*/




package controllers

import com.google.inject.Inject
import play.api.libs.ws.WSClient
import play.api.mvc.{BaseController, ControllerComponents}

import com.amazonaws.client.builder._
import com.amazonaws.regions.Region
import com.amazonaws.regions._
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.auth.BasicAWSCredentials
import java.io.File
import play.api._
import akka.http.scaladsl.model.StatusCodes
import com.amazonaws.services.s3.transfer._
import scala.Byte
import java.io._
import scala.collection.JavaConverters._
import java.io.{InputStream, InputStreamReader,BufferedInputStream}
import java.io.FileOutputStream
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream
import scala.concurrent.Future
import com.amazonaws.services.s3.model.{ListNextBatchOfObjectsRequest, ListObjectsRequest, ObjectListing}
import java.io.ByteArrayInputStream;
import akka.NotUsed
import akka.stream._
import akka.stream.scaladsl._
import akka.stream.stage._
import akka.util.{ByteString, ByteStringBuilder}
import scala.concurrent.ExecutionContext.Implicits.global


class s3Service @Inject()(ws: WSClient, val controllerComponents: ControllerComponents) extends BaseController {



   def fileByteData(os: OutputStream,path:String): Unit = {
    val bucketName = "bxbd-subodh"  //BUCKET FROM THE FOLDER IS DOWNLOADED

    val AWS_ACCESS_KEY = "AKIAVKW6ETGXM3PXMS4J"
    val AWS_SECRET_KEY = "q123Vuuusl5NHDgXhnpvZkFY8LTTAwbroquoMl3b"

    val yourAWSCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY)
    val amazonS3Client = new AmazonS3Client(yourAWSCredentials)
    val listObjectsRequest = new ListObjectsRequest().  //LISTING THE FILES INSIDE PARTICULAR FOLDER. THIS FUNCTION DISPLAYS ONLY FILES AND NOT SUBFOLDERS
         withBucketName(bucketName).
         withPrefix(s"$path/").
         withDelimiter("/")

  var objects = (amazonS3Client.listObjects(listObjectsRequest))
  val sBuffer = (asScalaBuffer(objects.getObjectSummaries()))
  var buffer = new Array[Byte](10000)

  var zos = new ZipOutputStream(os) //OUTPUT STREAM FOR THE Zip

  var ll=sBuffer.toList //CONVERTING JAVA LIST TO SCALA LIST

  try{
   do {
     for (objectSummary <- ll) {
         var key = objectSummary.getKey() //PATH OF THE FILE FOR EG MAPS/ABC.TXT
                 println(key)
              var arr=key.split("/") //GETTING THE NAME OF FILE ABC.TXT
              if(arr.length>1){
               var file_name = arr(arr.length-1)
               println(file_name)
               var appendPath=s"$path/"+file_name

               var a=amazonS3Client.getObject(bucketName,appendPath)
               var in:InputStream = new BufferedInputStream(a.getObjectContent())  //CONVERTING THE DATA INTO INPUT STREAM
               var ze= new ZipEntry(file_name) //MAKING NEW ENTRY IN THE ZIP
               zos.putNextEntry(ze)
               var len = in.read(buffer)
               while (len > 0) {
               			zos.write(buffer, 0, len)
                     len = in.read(buffer)
               		}
                  in.close()
                }

        }

     objects = amazonS3Client.listNextBatchOfObjects(objects);
   } while (objects.isTruncated())
 }finally{


      zos.closeEntry()
      zos.close()
      os.close()
    }

}
  def Download(path:String) = Action{
    val bucketName = "bxbd-subodh"
    val AWS_ACCESS_KEY = "AKIAVKW6ETGXM3PXMS4J"
    val AWS_SECRET_KEY = "q123Vuuusl5NHDgXhnpvZkFY8LTTAwbroquoMl3b"
    val yourAWSCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY)
    val amazonS3Client = new AmazonS3Client(yourAWSCredentials)
    var transfer=TransferManagerBuilder.defaultTransferManager()


    var flag=0  //FLAG TO CHECK IF PATH IS FILE OR FOLDER
    try{
        transfer.download(bucketName, path, new File(s"/Users/saksham/Desktop/project/$path"), 1000) //SAVING THE FILE LOCALLY ON SYSTEM


        flag=1

      }
      catch {
        case ex:Exception =>{}

      }

      if(flag==0) //IF FLAG ZERO THAT IS THE PATH IS FOLDER
      {
        val zipSource: Source[ByteString, Unit] =
          StreamConverters
            .asOutputStream()
            .mapMaterializedValue(os => Future {
              fileByteData(os,path)

              os.close()

            })

        Ok.chunked(zipSource).withHeaders(
          CONTENT_TYPE -> "application/zip",
          CONTENT_DISPOSITION -> s"attachment; filename = $path.zip")
      }
      else if(flag==1)
      {
        Ok("FILE DOWNLOADED")
      }
      else
      {
        InternalServerError("Error Exists")
      }




  }
  def upload=Action{
    val bucketName = "bxbd-subodh"
    val AWS_ACCESS_KEY = "AKIAVKW6ETGXM3PXMS4J"
    val AWS_SECRET_KEY = "q123Vuuusl5NHDgXhnpvZkFY8LTTAwbroquoMl3b"
    val yourAWSCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY)
    val amazonS3Client = new AmazonS3Client(yourAWSCredentials)
    var transfer=TransferManagerBuilder.defaultTransferManager()
    transfer.uploadDirectory(bucketName, "test4", new File("/Users/saksham/Downloads/upload/"), true)
    Ok("DONE")



  }
}
