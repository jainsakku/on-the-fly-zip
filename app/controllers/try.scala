/*
  FOR DIRECTLY UPLOPADING THE JSON RESPONSE TO THE S3 ON THE FLY
  FUNCTION NAME -> s3FileUpload IN JsonDownload CLASS
  TO upload RESPONSE as fodler in s3 localhost/task/s3upload
  to download RESPONSE as zip localhost/task/s3download
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
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model._



class JsonDownload @Inject()(ws: WSClient, val controllerComponents: ControllerComponents) extends BaseController {

  def zip1() = Action {
  val zipSource: Source[ByteString, Unit] =
    StreamConverters
      .asOutputStream()
      .mapMaterializedValue(os => Future { fileByteData1(os);
        println("******" + os); os.close();println("os is closed")})


  println("-----------------------" + zipSource)
  (Ok.chunked(zipSource).withHeaders(
    CONTENT_TYPE -> "application/zip",
    CONTENT_DISPOSITION -> s"attachment; filename = test.zip"))

}

def fileByteData1(os: OutputStream): Unit = {

var listJson=List("""{
  "glossary": {
      "title": "example glossary",
  "GlossDiv": {
          "title": "S",
    "GlossList": {
              "GlossEntry": {
                  "ID": "SGML",
        "SortAs": "SGML",
        "GlossTerm": "Standard Generalized Markup Language",
        "Acronym": "SGML",
        "Abbrev": "ISO 8879:1986",
        "GlossDef": {
                      "para": "A meta-markup language, used to create markup languages such as DocBook.",
          "GlossSeeAlso": ["GML", "XML"]
                  },
        "GlossSee": "markup"
              }
          }
      }
  }
}
""",
"""{
  "glossary": {
      "title": "example glossary",
  "GlossDiv": {
          "title": "S",
    "GlossList": {
              "GlossEntry": {
                  "ID": "SGML",
        "SortAs": "SGML",
        "GlossTerm": "Standard Generalized Markup Language",
        "Acronym": "SGML",
        "Abbrev": "ISO 8879:1986",
        "GlossDef": {
                      "para": "A meta-markup language, used to create markup languages such as DocBook.",
          "GlossSeeAlso": ["GML", "XML"]
                  },
        "GlossSee": "markup"
              }
          }
      }
  }
}
""",
"""{
  "glossary": {
      "title": "example glossary",
  "GlossDiv": {
          "title": "S",
    "GlossList": {
              "GlossEntry": {
                  "ID": "SGML",
        "SortAs": "SGML",
        "GlossTerm": "Standard Generalized Markup Language",
        "Acronym": "SGML",
        "Abbrev": "ISO 8879:1986",
        "GlossDef": {
                      "para": "A meta-markup language, used to create markup languages such as DocBook.",
          "GlossSeeAlso": ["GML", "XML"]
                  },
        "GlossSee": "markup"
              }
          }
      }
  }
}
"""
)
var count=0
var buffer = new Array[Byte](1024)
  var zos = new ZipOutputStream(os)

  try{
for (js<-listJson){
  count=count+1
  var in:InputStream = new ByteArrayInputStream(js.getBytes());

  var ze= new ZipEntry(s"Something$count.json")
  zos.putNextEntry(ze)
  var len = in.read(buffer)

    while (len > 0) {
       zos.write(buffer, 0, len)

        len = in.read(buffer)
     }
     in.close()
   }
 }
   finally{


         zos.closeEntry()
         zos.close()
       }

  }
def s3FileUpload()= Action {

  var listJson=List("""{
    "glossary": {
        "title": "example glossary",
    "GlossDiv": {
            "title": "S",
      "GlossList": {
                "GlossEntry": {
                    "ID": "SGML",
          "SortAs": "SGML",
          "GlossTerm": "Standard Generalized Markup Language",
          "Acronym": "SGML",
          "Abbrev": "ISO 8879:1986",
          "GlossDef": {
                        "para": "A meta-markup language, used to create markup languages such as DocBook.",
            "GlossSeeAlso": ["GML", "XML"]
                    },
          "GlossSee": "markup"
                }
            }
        }
    }
  }
  """,
  """{
    "glossary": {
        "title": "example glossary",
    "GlossDiv": {
            "title": "S",
      "GlossList": {
                "GlossEntry": {
                    "ID": "SGML",
          "SortAs": "SGML",
          "GlossTerm": "Standard Generalized Markup Language",
          "Acronym": "SGML",
          "Abbrev": "ISO 8879:1986",
          "GlossDef": {
                        "para": "A meta-markup language, used to create markup languages such as DocBook.",
            "GlossSeeAlso": ["GML", "XML"]
                    },
          "GlossSee": "markup"
                }
            }
        }
    }
  }
  """,
  """{
    "glossary": {
        "title": "example glossary",
    "GlossDiv": {
            "title": "S",
      "GlossList": {
                "GlossEntry": {
                    "ID": "SGML",
          "SortAs": "SGML",
          "GlossTerm": "Standard Generalized Markup Language",
          "Acronym": "SGML",
          "Abbrev": "ISO 8879:1986",
          "GlossDef": {
                        "para": "A meta-markup language, used to create markup languages such as DocBook.",
            "GlossSeeAlso": ["GML", "XML"]
                    },
          "GlossSee": "markup"
                }
            }
        }
    }
  }
  """
  )
  var count=0
  var buffer = new Array[Byte](1024)
  val bucketName = "bxbd-subodh"
  val AWS_ACCESS_KEY = "AKIAVKW6ETGXM3PXMS4J"
  val AWS_SECRET_KEY = "q123Vuuusl5NHDgXhnpvZkFY8LTTAwbroquoMl3b"
  val yourAWSCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY)
  val amazonS3Client = new AmazonS3Client(yourAWSCredentials)

    try{
  for (js<-listJson){
    count=count+1

    var in:InputStream = new ByteArrayInputStream(js.getBytes())

    amazonS3Client.putObject(new PutObjectRequest(bucketName+ "/something6", s"something$count.json", in, new ObjectMetadata()))
  }
}

     finally{

         }
Ok("DONE")
    }




}
