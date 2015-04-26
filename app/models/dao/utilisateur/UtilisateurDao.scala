package models.dao.utilisateur

import java.sql.Date

import forms.UtilisateurForm
import models.entites.utilisateur.{Utilisateur, UtilisateurComponent}
import play.api.db.slick.{Config, Profile}
import play.api.db.slick.Config.driver.simple._

import scala.slick.driver.JdbcProfile
import scala.slick.lifted.TableQuery

class UtilisateurDao(override val profile: JdbcProfile) extends UtilisateurComponent with Profile {
  val utilisateurs = TableQuery[Utilisateurs]
}

object utilisateurDao  {
  val dao = new UtilisateurDao(Config.driver)

  def rechercher(nomUtilisateur : Column[Option[String]],
                 motPasse : Column[Option[String]]) =
    dao.utilisateurs filter { u =>
      Case.If(motPasse.isDefined).Then(u.motPasse === motPasse).Else(Some(true)) &&
        Case.If(nomUtilisateur.isDefined).Then(u.nomUtilisateur === nomUtilisateur).Else(Some(true))
    }

  def rechercherParFormulaire(formulaire : UtilisateurForm) = rechercher(formulaire.nomUtilisateur,formulaire.motPasse)

  def rechercherParId(id : Int) = dao.utilisateurs filter (u => u.id === id)

  def rechercheParPersonne(idPersonne : Int) = dao.utilisateurs filter (u => u.idPersonne === idPersonne)

  def rechercherParNomMotPasse(nomUtilisateur : String,motPasse : String) = dao.utilisateurs filter (u => u.nomUtilisateur === nomUtilisateur && u.motPasse === motPasse)

}
