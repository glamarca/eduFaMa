package models.entites.autorisation

import java.sql.Date

import play.api.db.slick.Profile


trait PermissionComponent { this : Profile =>
  import profile.simple._

  class Permissions(tag: Tag) extends Table[(Permission)](tag, "PERMISSION") {

    def id: Column[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def nom : Column[String] = column[String] ("NOM",O.NotNull)
    def nomRef : Column[String] = column[String] ("NOM_REF",O.NotNull)
    def dateCreation : Column[Date] = column[Date]("DATE_CREATION",O.NotNull)
    def description : Column[String] = column[String]("DESCRIPTION",O.Nullable)

    override def * = (id.?,nom,nomRef,dateCreation,description.?) <>(Permission.tupled, Permission.unapply)

  }

}

case class Permission(id : Option[Int],nom : String,nomRef : String,dateCreation : Date,description : Option[String])
