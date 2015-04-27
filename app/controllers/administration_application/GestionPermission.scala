package controllers.administration_application

import models.dao.autorisation.permissionDao
import models.entites.autorisation.Permission
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages
import play.api.mvc.{Action, Controller}
import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._
import play.api.Play.current

object GestionPermission extends Controller {

  /**
   * Le formulaire de creation et modification d'une permission
   */
  val formulairePermission = Form(
    mapping(
      "id" -> default(optional(number), None),
      "nom" -> nonEmptyText,
      "nomRef" -> nonEmptyText,
      "dateCreation" -> default(sqlDate,null),
      "description" -> default(optional(text), None)
    )(Permission.apply)(Permission.unapply)
  )

  /**
   * Le formualire de recherche d'une permission
   */
  val formulaireRecherche = Form(
    tuple(
      "nomRef" -> default(optional(text),None),
      "nom" -> default(optional(text),None)
    )
  )

  /**
   * Page d'accueil de la gestion des permissions
   * @return Une liste de permissions vide dans la vue
   */
  def permissionIndex = Action { implicit request =>
    Ok(views.html.administration_application.autorisation.permission.gestionPermission(None))
  }

  /**
   * Recherche une liste de permission en fonction de "fomulaireRecherche"
   * @return La Liste des permission trouvées , dans la vue de recherche
   */
  def rechercherPermission = DBAction { implicit request =>
    formulaireRecherche.bindFromRequest.fold(
      formWithError =>{
        BadRequest(views.html.administration_application.autorisation.permission.gestionPermission(None))
      },
      formulaireRecherche =>{
        val listePermissions = permissionDao.rechercherParNoms(formulaireRecherche._1,formulaireRecherche._2).list
        Ok(views.html.administration_application.autorisation.permission.gestionPermission(Some(listePermissions)))
      }
    )
  }

  /**
   * Permet d'afficher la fiche d'une permission
   * @param id L'id de la permission
   * @return La vue de la fiche de permission
   */
  def afficherPermission(id : Int) = DBAction { implicit request =>
      val permission = permissionDao.rechercherParId(id).first
      Ok
  }

  /**
   * Mise à jour de la permissiond ans la DB et renvoie de la nouvelle permission
   * @param permission La permission à mettre à jour
   * @return La permission mise à jour.
   */
  def modifierPermission(permission: Permission): Permission = DB.withSession { implicit request =>
    permissionDao.rechercherParId(permission.id.get).update(permission)
    permissionDao.rechercherParId(permission.id.get).first
  }

  /**
   * Création d'une permission dans la DB
   * @param permission La permission à créer
   * @return La fiche de la permission créée
   */
  def creerPermission(permission: Permission) : Permission = DB.withSession{implicit request =>
    permissionDao.dao.permissions += permission
    permissionDao.rechercherParId(permission.id.get).first
  }

  /**
   * Permet de lancer la creation ou la modification d'une permission en fonction de "formulairePermission"
   * @return La fiche de la permission crée
   */
  def creerModifierPermission = Action { implicit request =>
    formulairePermission.bindFromRequest.fold(
      formWithError => {
        BadRequest
      },
      formulaire => {
        if(formulaire.id.isDefined){
          val permissionRetour = modifierPermission(formulaire)
          Ok
        }
        else{
          val permissionRetour = creerPermission(formulaire)
          Ok
        }
      }
    )
  }

  /**
   * Supprime une permission
   * @param id L'id de la permission à supprimer
   * @return La vue de recherche des permissions
   */
  def supprimerPermission(id : Int) = DBAction {implicit request =>
     permissionDao.rechercherParId(id).mutate(_.delete)
    Ok(views.html.administration_application.autorisation.permission.gestionPermission(None))
  }

  def afficherPermissionForm(id : Option[Int]) = DBAction { implicit request =>
    if(id.isDefined){
      val permission = permissionDao.rechercherParId(id.get).first
      Ok(views.html.administration_application.autorisation.permission.permissionForm(formulairePermission.fill(permission))(Messages("modificationPermission")))
    }
    else{
      Ok(views.html.administration_application.autorisation.permission.permissionForm(formulairePermission)(Messages("creationPermission")))
    }
  }

}
