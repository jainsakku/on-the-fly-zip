// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/saksham/Downloads/bitapi/conf/routes
// @DATE:Thu Jul 11 16:59:18 IST 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:1
package controllers {

  // @LINE:5
  class ReverseJsonDownload(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def zip1(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "task/s3download")
    }
  
    // @LINE:6
    def s3FileUpload(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "task/s3upload")
    }
  
  }

  // @LINE:1
  class Reverses3Service(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:1
    def upload(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "task/upload")
    }
  
    // @LINE:3
    def Download(path:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "task/Download/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("path", path)))
    }
  
  }


}
