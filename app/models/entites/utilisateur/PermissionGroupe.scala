package models.entites.utilisateur

import java.sql.Date

import play.api.db.slick.Profile


trait PermissionGroupeComponent { this : Profile =>
  import profile.simple._

  class PermissionsGroupes(tag: Tag) extends Table[(PermissionGroupe)](tag, "PERMISSION_GROUPE") {

    def id: Column[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def idPermission : Column[Int] = column[Int]("ID_PERMISSION",O.NotNull)
    def idGroupe : Column[Int] = column[Int]("ID_GROUPE",O.NotNull)
    def dateAjout : Column[Date] = column[Date]("DATE_AJOUT",O.NotNull)

    override def * = (id.?,idPermission,idGroupe,dateAjout) <>(PermissionGroupe.tupled, PermissionGroupe.unapply)

  }

}

case class PermissionGroupe(id : Option[Int],idPermission : Int,idGroupe : Int,dateAjout : Date)
