#on-the-fly-zip is a scala app developed using play framework to download files from amazon s3 directly into a zipped folder and upload the files on local system by compressing them into zip files diretly on amazon s3. Also to download json responses of an api directly as files or to upload json response in a zipped folder on amazon s3. Do find out routes file for different end points

FROM ROUTES DOWNLOAD FUNCTION IS CALLED IN s3Service case class

FOR THE FILE DOWNLOAD localhost/task/download/FILENALE with extension
   eg http://localhost:9000/task/Download/test.csv
      
FOR ZIP DOWNLOAD FROM THE S3 ON THE FLY  localhost/task/download/NAME OF THE FOLDER
   eg http://localhost:9000/task/Download/test


FOR DIRECTLY UPLOPADING THE JSON RESPONSE TO THE S3 ON THE FLY
  		FUNCTION NAME -> s3FileUpload IN JsonDownload CLASS


TO upload RESPONSE as fodler in s3 localhost/task/s3upload

To download RESPONSE as zip localhost/task/s3download

