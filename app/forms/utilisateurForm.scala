package forms

import play.api.data.Form
import play.api.data.Forms._

case class UtilisateurForm(idUtilisateur: Option[Int], nomUtilisateur: Option[String], nom: Option[String], prenom: Option[String], motPasse: Option[String], email: Option[String])

