package models.dao.autorisation

import models.entites.autorisation.PermissionGroupeComponent
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.{Config, Profile}

import scala.slick.driver.JdbcProfile
import scala.slick.lifted.TableQuery

class PermissionGroupeDao(override val profile: JdbcProfile) extends PermissionGroupeComponent with Profile {
  val permissionsGroupes = TableQuery[PermissionsGroupes]
}

object permissionGroupeDao {

  val dao = new PermissionGroupeDao(Config.driver)

  def rechercher(idPermission: Column[Option[Int]], idGroupe: Column[Option[Int]]) =
    dao.permissionsGroupes filter { p =>
      Case.If(idPermission.isDefined).Then(p.idPermission === idPermission).Else(Some(true)) &&
        Case.If(idGroupe.isDefined).Then(p.idGroupe === idGroupe).Else(Some(true))
    }

  def RechercherParPermissionGroupeIds(idPermission: Option[Int], idGroupe: Option[Int]) = rechercher(idPermission, idGroupe)

}