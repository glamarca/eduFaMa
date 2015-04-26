package controllers.administration_application

import play.api.mvc.{Action, Controller}

/**
 * Created by sarace on 26/04/15.
 */
object GestionApplication extends Controller{

  def gestionApplicationIndex = Action {implicit request =>
    Ok(views.html.administration_application.gestionApplicationIndex())
  }

}
