// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/saksham/Downloads/bitapi/conf/routes
// @DATE:Thu Jul 11 16:59:18 IST 2019

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseJsonDownload JsonDownload = new controllers.ReverseJsonDownload(RoutesPrefix.byNamePrefix());
  public static final controllers.Reverses3Service s3Service = new controllers.Reverses3Service(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseJsonDownload JsonDownload = new controllers.javascript.ReverseJsonDownload(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.Reverses3Service s3Service = new controllers.javascript.Reverses3Service(RoutesPrefix.byNamePrefix());
  }

}
