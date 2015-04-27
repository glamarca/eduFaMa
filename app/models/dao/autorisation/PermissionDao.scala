package models.dao.autorisation

import models.entites.autorisation.PermissionComponent
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.{Config, Profile}

import scala.slick.driver.JdbcProfile
import scala.slick.lifted.TableQuery

class PermissionDao(override val profile: JdbcProfile) extends PermissionComponent with Profile {
  val permissions = TableQuery[Permissions]
}

object permissionDao {
  val dao = new PermissionDao(Config.driver)

  def rechercher(nom : Column[Option[String]],nomRef : Column[Option[String]]) =
    dao.permissions filter { p =>
      Case.If(nom.isDefined).Then(p.nom === nom).Else(Some(true)) &&
      Case.If(nomRef.isDefined).Then(p.nomRef === nomRef).Else(Some(true))
  }

  def rechercherParNoms(nom : Option[String],nomRef : Option[String]) = rechercher(nom,nomRef)

  def rechercherParId(id : Int) = dao.permissions filter (p => p.id === id)


}
