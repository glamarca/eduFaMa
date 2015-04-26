package models.entites.personne


import play.api.db.slick.Profile

trait AdresseComponent { this : Profile =>
  import profile.simple._

  class Adresses(tag: Tag) extends Table[(Adresse)](tag, "ADRESSE") {

    def id: Column[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def rue : Column[String] = column[String]("RUE")
    def numeroRue: Column[Int] = column[Int]("NUMERO_RUE")
    def localite : Column[String] = column[String]("LOCALITE")
    def codePostal: Column[Int] = column[Int]("CODE_POSTAL")
    def ville : Column[String] = column[String]("VILLE",O.NotNull)
    def pays : Column[String] = column[String]("PAYS",O.NotNull)
    def idPersonne : Column[Int] = column[Int] ("ID_PERSONNE",O.NotNull)

    override def * = (id.?, rue.?, numeroRue.?,localite.?,codePostal.?,ville,pays,idPersonne) <>(Adresse.tupled, Adresse.unapply)

  }

}

case class Adresse(id : Option[Int],rue : Option[String],numeroRue : Option[Int],localite : Option[String],codePostal : Option[Int],ville : String,pays : String,idPersonne : Int)


