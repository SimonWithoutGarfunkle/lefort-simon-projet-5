# SafetyNet Alerts #
## Projet de formation OpenClassRooms pour le parcours DEV Java ##
#############  
Par Simon Lefort  
#############


# Presentation #
L'application SafetyNet Alerts a pour but d'envoyer des informations aux systèmes de services d'urgence. Elle expose différents endpoints qui fournissent des données au format Json.  
Les URL dédiées à la maintenance de la base de données (CRUD) sont détaillées ici : [Endpoints CRUD](https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DAJava_P5/Endpoints.pdf)  
Les URL dédiées aux besoins spécifiques des services d'urgences sont détaillées ici : [Endpoints Metier](https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DAJava_P5/URLs.pdf)<br><br><br>
     
# Technologie #

Ce service a été developpé avec : 
* Java 17
* Maven 3.9.1
* Spring-boot 3.1.0
* Eclipse
* Liste complète des dépendances dans le fichier pom.XML à la racine du projet

  
  
# Pré-requis #
Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre système : 
* Java 17 (ou +)
* Maven 3.9.1
* Le programme utilise le port 8080. Vérifiez qu'il est bien disponible.  
* Le programme contient une base de données JSon data.json situé ici \src\main\resources. Vous pouvez modifier son contenu tant que vous respectez sa structure et que vous ne modifiez pas son chemin et son nom.

# Étapes d'installation #
1 - Clonez le référentiel du projet en utilisant la commande suivante dans votre terminal :  
git clone https://github.com/SimonWithoutGarfunkle/lefort-simon-projet-5.git  <br><br>
2 - Accédez au répertoire du projet :  
cd lefort-simon-projet-5 <br><br>
3 - Compilez le projet à l'aide de Maven :  
mvn clean install  
Cette commande téléchargera les dépendances du projet, compilera les sources et exécutera les tests.
<br><br>
# Exécution du programme #
Une fois le projet compilé avec succès, vous pouvez lancer l'application en suivant les étapes ci-dessous :

1 - Assurez-vous d'être toujours dans le répertoire du projet lefort-simon-projet-5.  

2 - Exécutez la commande suivante pour démarrer l'application :  
java -jar target/lefort-simon-projet-5.jar  
Cela démarrera l'application Spring Boot et vous verrez les journaux indiquant que l'application a été démarrée.

3 - Accédez à l'application dans votre navigateur en ouvrant l'URL suivante :  
http://localhost:8080/persons  
