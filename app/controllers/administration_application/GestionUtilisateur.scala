package controllers.administration_application

import forms.UtilisateurForm
import models.dao.personne.{personneDao}
import models.dao.utilisateur.{utilisateurDao}
import models.entites.personne.Personne
import models.entites.utilisateur.Utilisateur
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages
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
      "email" -> default(optional(email),None)
    )(UtilisateurForm.apply)(UtilisateurForm.unapply)
  )

  def utilisateurIndex = Action { implicit request =>
    Ok(views.html.administration_application.utilisateur.gestionUtilisateur(utilisateurForm,None))
  }

  def afficheUtilisateur(id : Int) = DBAction { implicit request =>
    val utilisateur = (utilisateurDao.rechercherParId(id)).list.head
    val personne = (personneDao.rechercherParId(utilisateur.idPersonne)).list.head
    val ficheUtilisateur = UtilisateurForm(utilisateur.id,Some(utilisateur.nomUtilisateur),Some(personne.nom),Some(personne.prenom),Some(utilisateur.motPasse),Some(personne.email))
    Ok(views.html.administration_application.utilisateur.ficheUtilisateur(ficheUtilisateur))
  }

  def afficheUtilisateurForm(id : Option[Int]) = DBAction{ implicit request =>
    if(id.isDefined){
      val utilisateur = (utilisateurDao.rechercherParId(id.get)).list.head
      val personne = (personneDao.rechercherParId(utilisateur.idPersonne)).list.head
      val formulaire = utilisateurForm fill (UtilisateurForm(utilisateur.id,Some(utilisateur.nomUtilisateur),Some(personne.nom),Some(personne.prenom),Some(utilisateur.motPasse),Some(personne.email)))
      Ok(views.html.administration_application.utilisateur.utilisateurForm(formulaire)(Messages("modificationUtilisateur")))
    }
    else{
      Ok(views.html.administration_application.utilisateur.utilisateurForm(utilisateurForm)(Messages("creationUtilisateur")))
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

  def modifierUtilisateur(form: UtilisateurForm) : UtilisateurForm = DB.withSession { implicit request =>
    val utilisateur = (utilisateurDao.rechercherParId(form.idUtilisateur.get)).list.head
    val personne = (personneDao.rechercherParId(utilisateur.idPersonne)).list.head
    utilisateurDao.dao.utilisateurs.filter(_.id === form.idUtilisateur)
      .map(u => (u.nomUtilisateur,u.dateModification,u.motPasse))
      .update((form.nomUtilisateur.get,new java.sql.Date(new java.util.Date().getTime()),form.motPasse.get))
    personneDao.dao.personnes.filter(_.id === personne.id)
      .map(p => (p.nom,p.prenom,p.email))
      .update((form.nom.get,form.prenom.get,form.email.get))
    val utilisateurMaj = (utilisateurDao.rechercherParId(form.idUtilisateur.get)).list.head
    val personneMaj = (personneDao.rechercherParId(utilisateur.idPersonne)).list.head
    UtilisateurForm(utilisateur.id,Some(utilisateurMaj.nomUtilisateur),Some(personneMaj.nom),Some(personneMaj.prenom),Some(utilisateurMaj.motPasse),Some(personneMaj.email))
  }

  def modifierCreerUtilisateur = Action { implicit request =>
    utilisateurForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.administration_application.utilisateur.utilisateurForm(formWithErrors)(Messages("modificationUtilisateur")))
      },
      formulaire =>{
        if(formulaire.idUtilisateur.isDefined){
          val formulaireRetour = modifierUtilisateur(formulaire)
          Ok(views.html.administration_application.utilisateur.ficheUtilisateur(formulaireRetour))
        }
        else{
          val formulaireRetour = ajouterUtilisateur(formulaire)
          Ok(views.html.administration_application.utilisateur.ficheUtilisateur(formulaireRetour))
        }
      }
    )
  }

  def rechercherUtilisateur = DBAction { implicit request =>
    utilisateurForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.administration_application.utilisateur.gestionUtilisateur(formWithErrors,None))
      },
      formulaireRecherche => {
        val listeUtilisateurs = {
          utilisateurDao.rechercherParFormulaire(formulaireRecherche).list
            .map(utilisateur => (utilisateur,personneDao.rechercherParId(utilisateur.idPersonne).list.head))
        }
        Ok(views.html.administration_application.utilisateur.gestionUtilisateur(utilisateurForm,Some(listeUtilisateurs)))
      }
    )
  }

  def supprimerUtilisateur(id : Int) = DBAction {implicit request =>
    utilisateurDao.dao.utilisateurs.filter(_.id === id).mutate(_.delete)
    Ok(views.html.administration_application.utilisateur.gestionUtilisateur(utilisateurForm,None))
  }

}
