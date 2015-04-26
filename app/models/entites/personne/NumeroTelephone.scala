package models.entites.personne

import play.api.db.slick.Profile

trait NumeroTelephoneComponent { this : Profile =>
  import profile.simple._

  class NumerosTelephones(tag: Tag) extends Table[(NumeroTelephone)](tag, "NUMERO_TELEPHONE") {

    def id: Column[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)

    def typeNumero : Column[String] = column[String]("TYPE", O.NotNull)

    def idPersonne: Column[Int] = column[Int]("ID_PERSONNE", O.NotNull)

    override def * = (id.?, typeNumero, idPersonne) <>(NumeroTelephone.tupled, NumeroTelephone.unapply)

  }

}

case class NumeroTelephone(id : Option[Int],typeNumero : String,idPersonne : Int)
