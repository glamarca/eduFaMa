package models.entites.autorisation

import java.sql.Date

import play.api.db.slick.Profile


trait MembreGroupeComponent { this : Profile =>
  import profile.simple._

  class MembresGroupes(tag: Tag) extends Table[(MembreGroupe)](tag, "MEMBRE_GROUPE") {

    def id: Column[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def idPersonne : Column[Int] = column[Int]("ID_PERSONNE",O.NotNull)
    def idGroupe : Column[Int] = column[Int]("ID_GROUPE",O.NotNull)
    def dateAjout : Column[Date] = column[Date]("DATE_AJOUT",O.NotNull)

    override def * = (id.?,idPersonne,idGroupe,dateAjout) <>(MembreGroupe.tupled, MembreGroupe.unapply)

  }

}

case class MembreGroupe(id : Option[Int],idPersonne : Int,idGroupe : Int,dateAjout : Date)

