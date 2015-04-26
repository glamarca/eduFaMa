# --- !Ups
create table UTILISATEUR (ID INTEGER not null AUTO_INCREMENT PRIMARY KEY,NOM_UTILISATEUR varchar(30) not null,MOT_PASSE varchar(30) not null,ID_PERSONNE INTEGER not null,DATE_CREATION DATE,DATE_MODIFICATION DATE );
create table PERSONNE (ID INTEGER not null AUTO_INCREMENT PRIMARY KEY,NOM varchar(30) not null,PRENOM varchar(30) not null,EMAIL varchar(50) not null);
create table ADRESSE (ID INTEGER not null AUTO_INCREMENT PRIMARY KEY,RUE varchar(30) not null,NUMERO_RUE INTEGER ,LOCALITE varchar(30),CODE_POSTAL INTEGER ,VILLE varchar(30) not null,PAYS varchar(30) not null,ID_PERSONNE INTEGER not null);
create table NUMERO_TELEPHONE (ID INTEGER not null AUTO_INCREMENT PRIMARY KEY,NUMERO Integer not null,TYPE VARCHAR(30) not null,ID_PERSONNE INTEGER not null);
create table GROUPE (ID INTEGER not null AUTO_INCREMENT PRIMARY KEY,NOM varchar(30) not null,NOM_REF varchar(30) not null,DATE_CREATION DATE,DATE_MODIFICATION DATE,UTILISATEUR_MODIF varchar(30),DESCRIPTION varchar(50));
create table MEMBRE_GROUPE (ID INTEGER not null AUTO_INCREMENT PRIMARY KEY,ID_PERSONNE INTEGER not null,ID_GROUPE INTEGER not null,DATE_AJOUT DATE);
create table PERMISSION (ID INTEGER not null AUTO_INCREMENT PRIMARY KEY,NOM varchar(30) not null,NOM_REF varchar(30) not null,DATE_CREATION DATE,DESCRIPTION varchar(50));
create table PERMISSION_GROUPE (ID INTEGER not null AUTO_INCREMENT PRIMARY KEY,ID_PERMISSION INTEGER not null,ID_GROUPE INTEGER not null,DATE_AJOUT DATE);

alter table MEMBRE_GROUPE add constraint FK_MEMBRE_GRP_GRP foreign key (ID_GROUPE) references GROUPE(ID) on update NO ACTION on delete NO ACTION;
alter table MEMBRE_GROUPE add constraint FK_MEMBRE_GRP_PERS foreign key (ID_PERSONNE) references PERSONNE(ID) on update NO ACTION on delete NO ACTION;
alter table PERMISSION_GROUPE add constraint FK_PERM_GRP_PERM foreign key (ID_PERMISSION) references PERMISSION(ID) on update NO ACTION on delete NO ACTION;
alter table PERMISSION_GROUPE add constraint FK_PERM_GRP_GRP foreign key (ID_GROUPE) references GROUPE(ID) on update NO ACTION on delete NO ACTION;

# --- !Downs
alter table MEMBRE_GROUPE drop foreign key FK_MEMBRE_GRP_GRP;
alter table MEMBRE_GROUPE drop foreign key FK_MEMBRE_GRP_PERS;
alter table PERMISSION_GROUPE drop foreign key FK_PERM_GRP_GRP;
alter table PERMISSION_GROUPE drop foreign key FK_PERM_GRP_PERM;

drop table UTILISATEUR;
drop table PERSONNE;
drop table ADRESSE;
drop table NUMERO_TELEPHONE;
drop table FONCTION;
drop table PERSONNE_FONCTION;
drop table GROUPE;
drop table MEMBRE_GROUPE;
drop table PERMISSION;
drop table PERMISSION_GROUPE;