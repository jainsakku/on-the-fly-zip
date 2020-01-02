// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/saksham/Downloads/bitapi/conf/routes
// @DATE:Thu Jul 11 16:59:18 IST 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:1
package controllers.javascript {

  // @LINE:5
  class ReverseJsonDownload(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def zip1: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.JsonDownload.zip1",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "task/s3download"})
        }
      """
    )
  
    // @LINE:6
    def s3FileUpload: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.JsonDownload.s3FileUpload",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "task/s3upload"})
        }
      """
    )
  
  }

  // @LINE:1
  class Reverses3Service(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:1
    def upload: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.s3Service.upload",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "task/upload"})
        }
      """
    )
  
    // @LINE:3
    def Download: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.s3Service.Download",
      """
        function(path0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "task/Download/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("path", path0))})
        }
      """
    )
  
  }


}
