package controllers.accueil

import play.api.mvc.{Action, Controller}

/**
 * Created by sarace on 25/04/15.
 */
object Acceuil extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

}
