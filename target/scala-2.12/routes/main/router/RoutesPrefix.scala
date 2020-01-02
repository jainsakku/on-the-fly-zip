// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/saksham/Downloads/bitapi/conf/routes
// @DATE:Thu Jul 11 16:59:18 IST 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
