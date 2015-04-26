package controllers.common

import play.api.Routes
import play.api.mvc._

object JavaScriptRouter extends Controller {
  def javascriptRoutes = Action { implicit request =>
    Ok(
      Routes.javascriptRouter("jsRoutes")(

      )
    ).as("text/javascript")
  }
}
