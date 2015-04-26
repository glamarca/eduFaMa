package controllers.administration_application

import forms.UtilisateurForm
import models.dao.personne.personneDao
import models.dao.utilisateur.{utilisateurDao}
import models.entites.personne.Personne
import models.entites.utilisateur.Utilisateur
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._
import play.api.Play.current

object GestionUtilisateur extends Controller{

  val utilisateurForm = Form(
    mapping(
      "idUtilisateur" -> default(optional(number),None),
      "nomUtilisateur" -> default(optional(text),None),
      "nom" -> default(optional(text),None),
      "prenom" -> default(optional(text),None),
      "motPasse" -> default(optional(text),None),
      "email" -> default(optional(text),None)
    )(UtilisateurForm.apply)(UtilisateurForm.unapply)
  )

  def utilisateurIndex = Action { implicit request =>
    Ok(views.html.administration_application.utilisateur.gestionUtilisateur(utilisateurForm,None))
  }

  def afficheUtilisateur(id : Int) = Action { implicit request =>
    Ok
  }

  def afficheUtilisateurForm(id : Option[Int]) = Action{ implicit request =>
    if(id.isDefined){
      Ok
    }
    else{
      Ok(views.html.administration_application.utilisateur.utilisateurForm(utilisateurForm)("Creation"))
    }
  }

  def ajouterUtilisateur(form: UtilisateurForm) : UtilisateurForm = DB.withSession { implicit request =>
    val personnes = (personneDao.rechercherParNomPrenom(form.nom.get,form.prenom.get)).list
    personnes match {
      case Nil => {
          personneDao.dao.personnes += Personne(None,form.nom.get,form.prenom.get,form.email.get)
          val personne = (personneDao.rechercherParNomPrenom(form.nom.get,form.prenom.get)).list.head
          val date = new java.sql.Date(new java.util.Date().getTime())
          utilisateurDao.dao.utilisateurs += Utilisateur(None,form.nomUtilisateur.get,form.motPasse.get,date,date,personne.id.get)
          val utilisateur = (utilisateurDao.rechercherParNomMotPasse(form.nomUtilisateur.get,form.motPasse.get)).list.head
          UtilisateurForm(utilisateur.id,Some(utilisateur.nomUtilisateur),Some(personne.nom),Some(personne.prenom),Some(utilisateur.motPasse),Some(personne.email))
      }
      case x::Nil => {
        val date = new java.sql.Date(new java.util.Date().getTime())
        utilisateurDao.dao.utilisateurs += Utilisateur(None,form.nomUtilisateur.get,form.motPasse.get,date,date,x.id.get)
        val utilisateur = (utilisateurDao.rechercherParNomMotPasse(form.nomUtilisateur.get,form.motPasse.get)).list.head
        UtilisateurForm(utilisateur.id,Some(utilisateur.nomUtilisateur),Some(x.nom),Some(x.prenom),Some(utilisateur.motPasse),Some(x.email))
      }
    }
  }

  def modifierUtilisateur = Action { implicit request =>
    val formulaire = utilisateurForm.bindFromRequest.get
    if(formulaire.idUtilisateur.isDefined){
      Ok
    }
    else{
        val formulaireRetour = ajouterUtilisateur(formulaire)
        Ok(views.html.administration_application.utilisateur.ficheUtilisateur(formulaireRetour))
    }

  }

  def rechercherUtilisateur = Action { implicit request =>
    val formulaireRecherche = utilisateurForm.bindFromRequest.get
    Ok(views.html.administration_application.utilisateur.gestionUtilisateur(utilisateurForm,None))
  }

}
