package models.dao.personne

import forms.UtilisateurForm
import models.entites.personne.{Personne, PersonneComponent}
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.{Config, Profile}

import scala.slick.driver.JdbcProfile
import scala.slick.lifted.TableQuery

class PersonneDao(override val profile: JdbcProfile) extends PersonneComponent with Profile {
  val personnes = TableQuery[Personnes]
}

object personneDao  {
  val dao = new PersonneDao(Config.driver)

  def rechercher(nom : Column[Option[String]],
                 prenom : Column[Option[String]],
                 email : Column[Option[String]]) = 
    dao.personnes filter { p =>
      Case.If(nom.isDefined).Then(p.nom === nom).Else(Some(true)) &&
      Case.If(prenom.isDefined).Then(p.prenom === prenom).Else(Some(true)) &&
      Case.If(email.isDefined).Then(p.email === email).Else(Some(true))
    }
  
   def rechercherParFormulaire(formulaire : UtilisateurForm) = rechercher(formulaire.nom,formulaire.prenom,formulaire.email)
  
   def rechercherParId(id : Int) = dao.personnes filter (p => p.id === id)

   def rechercherParNomPrenom(nom : String,prenom : String) = dao.personnes filter (p => p.nom === nom && p.prenom === prenom)
}