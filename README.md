# Educational Facilities Management

# Current Status : Spec analysing
More informations will be added continually on the Wiki

## Goal
The goal of this application is to provide a scalable, robust and generic application to manage any kind of educational facilities , such as school, universities, institutes, ...

It has to cover all the functions that make the core business of an educational facilities :

1. Classes, Programs, Cycles, Requirement, Certification, External Activities,...
2. Enrollment, Graduation, Deliberation, Admission, Schedule, Organisation, ....
3. Teachers, Students, Administrative and Technical Staff, Lecturers,...
4. Building, Classrooms, Diningrooms, Sports Halls, Laboratories, Administrative Rooms, ....
...

This application will use [Scala](http://www.scala-lang.org/), [AKKA](http://akka.io/), [Play Framework](https://www.playframework.com/),  [Slick](http://slick.typesafe.com/), [Bootstrap](http://getbootstrap.com/) and [mongoDB](https://www.mongodb.org/) and [MySql](http://www.mysql.com) and will try to be as reactive as it can!


## Installing

### Download and Install [Activator](https://www.typesafe.com/community/core-tools/activator-and-sbt)

[Activator](https://www.typesafe.com/community/core-tools/activator-and-sbt) is a tool created by [Typesafe](https://www.typesafe.com) to create and develop [Scala](http://www.scala-lang.org/) applications. You can download [Activator](https://www.typesafe.com/community/core-tools/activator-and-sbt) from the following address: https://www.typesafe.com/community/core-tools/activator-and-sbt.

[Activator](https://www.typesafe.com/community/core-tools/activator-and-sbt) use [SBT](http://www.scala-sbt.org/) as build tool and dependencies manager. All [SBT](http://www.scala-sbt.org/) dependencies configuration links can be found on the [MVN Repository](http://mvnrepository.com/) , in the [SBT](http://www.scala-sbt.org/)tab

To install activator, simply unzip the downloaded file and add the absolute path to the new directory in the environment variable `$PATH`. You will be able to run the command `activator` from anywhere in your file system.

### Create a new [MySql](http://www.mysql.com) Database

eduFaMa depends on a MySQL Server to work propertly. The installation varies according to your operating system. If you are not sure how to create a [MySql](http://www.mysql.com) database, just follow the instructions at: http://www.hildeberto.com/2009/06/creating-a-new-mysql-database-for-dummies.html.

### Create a new mongoDB Database

TO DO

### Clone the repository

After installing and configuring [Activator](https://www.typesafe.com/community/core-tools/activator-and-sbt)  now it's time to install eduFaMa. Clone the repository locally using [Git](http://git-scm.com/downloads):

    $ mkdir projects
    $ cd projects
    $ git clone https://github.com/glamarca/eduFaMa.git

It will create the directory `eduFaMa` where all cloned files will be located.

### Run the Application

To run the application:

    $ cd eduFaMa
    $ activator

    [eduFaMa] $ run

Once the server is started, you can open the application in your browser accessing the address: http://localhost:9000 .

## Contributing

To contribute to the project you have to follow the exactly same steps of the previous section, but cloning from your own fork:

1. Login on [GitHub](https://github.com)
2. Visit the repository at: https://github.com/glamarca/eduFaMa
3. Click on **Fork** to have your own copy of the project

The new clone url is the one from your own fork:

    $ git clone https://github.com/[your-username]/eduFaMa.git

A few additional commands are necessary to keep your repository in sync with the original. Use `git remote` to link your local repository with the original:

    $ git remote add upstream https://github.com/glamarca/eduFaMa.git

Everytime you want to update your fork with the changes made in the original, run the following commands:

    $ git fetch origin upstream
    $ git merge upstream/master

The merge was done locally and are not yet on your Github fork. For that, you have to push local modifications to the server:

    $ git push origin master

Contributions to the original repository are done through pull requests. So, make sure you have updated your fork and solved all conflicts before sending your pull requests.
