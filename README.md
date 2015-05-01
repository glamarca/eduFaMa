# Application de Gestion de l'enseignement secondaire francophone de Belgique

## Download and Install Activator

Activator is a tool created by Typesafe to create and develop Scala applications. You can download Activator from the following address: https://www.typesafe.com/community/core-tools/activator-and-sbt

To install activator, simply unzip the downloaded file and add the absolute path to the new directory in the environment variable `$PATH`. You will be able to run the command `activator` from anywhere in your file system.

## Create a new MySQL Database

Enssecfra depends on a MySQL Server to work propertly. The installation varies according to your operating system. If you are not sure how to create a MySQL database, just follow the instructions at: http://www.hildeberto.com/2009/06/creating-a-new-mysql-database-for-dummies.html.

Make sure you are using the following values while creating the database:

Database name: enssecfra
Database user: ensecfra
User password: ensecfra2015

These values are hard coded in the applications, but can be customized in the file `conf/application.conf`. Change them when you put this application in production.

## Installation

After installing and configuring Activator and MySQL, now it's time to install Enssecfra. Clone the repository locally using (Git)[http://git-scm.com/downloads]:

    $ mkdir projects
    $ cd projects
    $ git clone https://github.com/glamarca/enssecfra.git

It will create the directory `enssecfra` where all cloned files will be located. To run the application:

    $ cd enssecfra
    $ activator

    [enssecfra] $ run

Once the server is started, you can open the application in your browser accessing the address: [http://localhost:9000].

## Contributing

To contribute to the project you have to follow the exactly same steps of the previous section, but cloning from your own fork:

1. Login on (GitHub)[https://github.com]
2. Visit the repository at: https://github.com/glamarca/enssecfra
3. Click on **Fork** to have your own copy of the project

The new clone url is the one from your own fork:

    $ git clone https://github.com/[your-username]/enssecfra.git
    
A few additional commands are necessary to keep your repository in sync with the original. Use `git remote` to link your local repository with the original:

    $ git remote add upstream https://github.com/glamarca/enssecfra.git

Everytime you want to update your fork with the changes made in the originalrun the following commands:

    $ git fetch origin upstream
    $ git merge upstream/master

The merge was done locally and are not yet on your Github fork. For that, you have to push local modifications to the server:

    $ git push origin master

Contributions to the original repository are done through pull requests. So, make sure you have updated your fork and solved all conflicts before sending your pull requests.
