package controllers.administration_application

import play.api.mvc.{Action, Controller}

/**
 * Created by sarace on 28/04/15.
 */
object GestionAutorisation extends Controller {

  /**
   * Affiche la page des autorisations
   * @return
   */
  def autorisationIndex = Action {implicit request =>
    Ok(views.html.administration_application.autorisation.gestionAutorisation())
  }

}
