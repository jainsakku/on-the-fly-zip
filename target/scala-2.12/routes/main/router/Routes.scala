// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/saksham/Downloads/bitapi/conf/routes
// @DATE:Thu Jul 11 16:59:18 IST 2019

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  s3Service_1: controllers.s3Service,
  // @LINE:5
  JsonDownload_0: controllers.JsonDownload,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    s3Service_1: controllers.s3Service,
    // @LINE:5
    JsonDownload_0: controllers.JsonDownload
  ) = this(errorHandler, s3Service_1, JsonDownload_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, s3Service_1, JsonDownload_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """task/upload""", """controllers.s3Service.upload"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """task/Download/""" + "$" + """path<[^/]+>""", """controllers.s3Service.Download(path:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """task/s3download""", """controllers.JsonDownload.zip1()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """task/s3upload""", """controllers.JsonDownload.s3FileUpload()"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] lazy val controllers_s3Service_upload0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("task/upload")))
  )
  private[this] lazy val controllers_s3Service_upload0_invoker = createInvoker(
    s3Service_1.upload,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.s3Service",
      "upload",
      Nil,
      "GET",
      this.prefix + """task/upload""",
      """""",
      Seq()
    )
  )

  // @LINE:3
  private[this] lazy val controllers_s3Service_Download1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("task/Download/"), DynamicPart("path", """[^/]+""",true)))
  )
  private[this] lazy val controllers_s3Service_Download1_invoker = createInvoker(
    s3Service_1.Download(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.s3Service",
      "Download",
      Seq(classOf[String]),
      "GET",
      this.prefix + """task/Download/""" + "$" + """path<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:5
  private[this] lazy val controllers_JsonDownload_zip12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("task/s3download")))
  )
  private[this] lazy val controllers_JsonDownload_zip12_invoker = createInvoker(
    JsonDownload_0.zip1(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.JsonDownload",
      "zip1",
      Nil,
      "GET",
      this.prefix + """task/s3download""",
      """""",
      Seq()
    )
  )

  // @LINE:6
  private[this] lazy val controllers_JsonDownload_s3FileUpload3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("task/s3upload")))
  )
  private[this] lazy val controllers_JsonDownload_s3FileUpload3_invoker = createInvoker(
    JsonDownload_0.s3FileUpload(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.JsonDownload",
      "s3FileUpload",
      Nil,
      "GET",
      this.prefix + """task/s3upload""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case controllers_s3Service_upload0_route(params@_) =>
      call { 
        controllers_s3Service_upload0_invoker.call(s3Service_1.upload)
      }
  
    // @LINE:3
    case controllers_s3Service_Download1_route(params@_) =>
      call(params.fromPath[String]("path", None)) { (path) =>
        controllers_s3Service_Download1_invoker.call(s3Service_1.Download(path))
      }
  
    // @LINE:5
    case controllers_JsonDownload_zip12_route(params@_) =>
      call { 
        controllers_JsonDownload_zip12_invoker.call(JsonDownload_0.zip1())
      }
  
    // @LINE:6
    case controllers_JsonDownload_s3FileUpload3_route(params@_) =>
      call { 
        controllers_JsonDownload_s3FileUpload3_invoker.call(JsonDownload_0.s3FileUpload())
      }
  }
}
