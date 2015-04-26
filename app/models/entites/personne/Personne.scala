package models.entites.personne

import play.api.db.slick.Profile


trait PersonneComponent { this : Profile =>
  import profile.simple._

  class Personnes(tag: Tag) extends Table[(Personne)](tag, "PERSONNE") {

    def id: Column[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def nom : Column[String] = column[String]("NOM",O.NotNull)
    def prenom : Column[String] = column[String]("PRENOM",O.NotNull)
    def email : Column[String] = column[String]("EMAIL",O.NotNull)

    override def * = (id.?,nom,prenom,email) <>(Personne.tupled, Personne.unapply)

  }

}

case class Personne(id : Option[Int],nom : String,prenom : String,email : String)