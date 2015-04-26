package models.entites.utilisateur

import java.sql.Date

import play.api.db.slick.Profile


trait GroupeComponent { this : Profile =>
  import profile.simple._

  class Groupes(tag: Tag) extends Table[(Groupe)](tag, "GROUPE") {

    def id: Column[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def nom : Column[String] = column[String]("NOM",O.NotNull)
    def nomRef : Column[String] = column[String]("NOM_REF",O.NotNull)
    def dateCreation : Column[Date] = column[Date]("DATE_CREATION",O.NotNull)
    def dateModification : Column[Date] = column[Date]("DATE_MODIFICATION",O.NotNull)
    def utilisateurModif : Column[String] = column[String]("NOM_REF")
    def descritpion : Column[String] = column[String]("DESCRIPTION")

    override def * = (id.?,nom,nomRef,dateCreation,dateModification,utilisateurModif.?,descritpion.?) <>(Groupe.tupled, Groupe.unapply)

  }

}

case class Groupe(id : Option[Int],nom : String,nomRef : String,dateCreation : Date,dateModification : Date,utilisateurModif : Option[String],descritpion : Option[String])
