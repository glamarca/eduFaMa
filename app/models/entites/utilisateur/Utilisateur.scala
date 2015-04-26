package models.entites.utilisateur

import java.sql.Date

import play.api.db.slick.Profile

trait UtilisateurComponent { this : Profile =>
  import profile.simple._

  class Utilisateurs(tag: Tag) extends Table[(Utilisateur)](tag, "UTILISATEUR") {

    def id: Column[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def nomUtilisateur : Column[String] = column[String]("NOM_UTILISATEUR",O.NotNull)
    def motPasse : Column[String] = column[String]("MOT_PASSE",O.NotNull)
    def dateCreation : Column[Date] = column[Date]("DATE_CREATION",O.NotNull)
    def dateModification : Column[Date] = column[Date]("DATE_MODIFICATION",O.NotNull)
    def idPersonne : Column[Int] = column[Int] ("ID_PERSONNE",O.NotNull)

    override def * = (id.?,nomUtilisateur,motPasse,dateCreation,dateModification,idPersonne) <>(Utilisateur.tupled, Utilisateur.unapply)

  }

}

case class Utilisateur(id : Option[Int],nomUtilisateur : String,motPasse : String,dateCreation : Date,dateModification : Date,idPersonne : Int)
