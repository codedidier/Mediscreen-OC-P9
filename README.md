# Mediscreen-OC-P9                                                                              ![Alt text](https://github.com/codedidier/Mediscreen-OC-P9/blob/main/uiaccess/src/main/resources/static/Logo_Mediscreen.jpeg)
### Application permettant d'identifier les patients à risque en générant un rapport indiquant la probabilité qu'un patient développe un diabète.
#### 1 Ce projet m'a permis d'acquérir des compétences:
- Dans l'utilisation de méthodes agile pour gérer un projet.
- La création d'applications en microservices.
- L'utilisation de base de données NoSQL. 

## 2 Technologies:
        Java 1.8
        SpringBoot 2.7.5
        MySQL 8.0
        MongoDB 			     
        Thymeleaf   
        Feign
        Junit
        JaCoCo 			    
        Docker	    
        Swagger
        
## 3 Pré-requis:
Install Java: - https://www.oracle.com/fr/java/technologies/javase-downloads.html

Install Maven - https://maven.apache.org/install.html

Install MySql: - https://dev.mysql.com/downloads/mysql

Intall MongoDB: - https://docs.mongodb.com/manual/administration/install-community/

Install Docker: - https://www.docker.com/products/docker-desktop

## 4 Architecture
### 5 Mediscreen est composé de 4 micro-services
- mspatient: pour la saisie des données démographiques du patient.
- mshistory: pour la saisies de l'historique des patients.
- msassess: pour le calcul du risque de développer un diabète.
- uiaccess: C'est le microservice qui permet l'accès à l'application, il communique avec les 3 autres grâce à Feign.

## 6 Documentations pour la visualisation des Endpoints:
- mspatient: http://localhost:8081/swagger-ui/
- mshistory: http://localhost:8082/swagger-ui/
- msassess:  http://localhost:8083/swagger-ui/
- uiaccess:  http://localhost:8080/swagger-ui/


