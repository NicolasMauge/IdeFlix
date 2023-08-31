# IdeFlix
Projet EPITA septembre 2023 
-----------------
## Installation

### Base PostgresSQL #1: 
Si nécessaire, lancer Services et désactiver le service postgresql-x64-… (il redémarre automatiquement avec Windows) 

- Créer un répertoire /data sur C:\
(ou bien créer un volume avec la commande ci-dessous)
```
docker volume create postgres-volume
```

**Nous avons travaillé avec la version 15.2 depuis le début : récupérer l'image postgres de notre version :**

`docker pull postgres:15.2`


**Pour lancer le SGBD en spécifiant bien l'utilisateur, le mot de passe et le répertoire de stockage sur le PC :**  
```
docker run -itd -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=SrixTo2B44 -p 5432:5432 -v /c/data:/var/lib/postgresql/data --name postgresql_ideflix postgres:15.2 
```
ou avec volume
```
docker run -itd -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=SrixTo2B44 -p 5432:5432 -v postgres-volume:/var/lib/postgresql/data --name postgresql_ideflix postgres:15.2 
```

**Créer les bases :**

**Première méthode : lancer PGADMIN PgAdmin va trouver le serveur**
Créer les tables :
- ideflix_bdd 
- ideflix_iam 

>Remarque : la base est conservée dans /data même si le container est supprimé. 

**Seconde méthode (en ligne de commande dans le docker) :**

Aller dans le container :

`docker exec -it postgresql_ideflix bash`

Aller dans Postgres

`psql -h localhost -U postgres`

et coller

`CREATE DATABASE ideflix_iam; GRANT ALL PRIVILEGES ON DATABASE ideflix_iam TO postgres; CREATE DATABASE ideflix_bdd; GRANT ALL PRIVILEGES ON DATABASE ideflix_bdd TO postgres;`

Lister les bases

`\l`

Pour quitter Postgres

`\q`

Pour quitte le container :

`exit`

### IAM #2

**Dockerfile IAM :** 
```
FROM openjdk:17-oracle 

EXPOSE 8080:8080 

ARG JAR_FILE=./exposition*.jar 

COPY ${JAR_FILE} app.jar 

 
ENTRYPOINT ["java", "-jar","-DCONFIG_PATH=/conf_iam/ideflix_iam_docker.properties","/app.jar"] 
```

>Dans un répertoire vide, par exemple \temp\jar_iam : 

- mettre le jar de l'IAM 

- son Dockerfile 

- le fichier de configuration "ideflix_iam_docker.properties" 

>A noter que le chemin /c/temp/conf_iam correspond à C:\temp\conf_iam qui doit contenir le fichier ideflix_iam_docker.properties 

**Créer l'image (à refaire si on recompile) :** 

`docker build -t image_ideflix_iam .` 

### APP #3
**Dockerfile APP (nommé Dockerfile) :** 

```
FROM openjdk:17-oracle 

EXPOSE 8081:8081 

ARG JAR_FILE=./exposition*.jar 

COPY ${JAR_FILE} app.jar 

ENTRYPOINT ["java", "-jar","-DCONFIG_PATH=/conf_app/ideflix_app_docker.properties","/app.jar"]
```

**Dans un répertoire vide, par exemple \temp\jar_app, mettre le jar de l'APP et son Dockerfile.** 

>Docker Desktop doit être lancé. 

**Dans un terminal, dans le répertoire \temp\jar_app** 

- créer l'image (à refaire si on recompile) : 

- docker build -t image_ideflix_app . 

>A noter que le chemin /c/temp/conf_app correspond à C:\temp\conf_app qui doit contenir le fichier ideflix_app_docker.properties 

### IHM #4
**Dockerfile pour PC (opérationnel)**  
```
# Utilisez l'image de base Node.js pour Angular
FROM node as builder  

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers de package.json et de package-lock.json
COPY package-lock.json . 
COPY package.json .

# Run npm clean install, including dev dependencies for @angular-devkit
RUN npm install

# Copy all files
COPY . /app

# Construire l'application Angular
RUN npm run build --prod

# Utilisez une image légère de serveur Web pour servir l'application
FROM nginx:alpine

# Copier les fichiers construits vers le répertoire de nginx
COPY --from=builder /app/dist/ideflix-ihm /usr/share/nginx/html 

# Exposer le port 4200 pour accéder à l'application Angular
EXPOSE 4200 

# Commande pour démarrer le serveur Nginx
ENTRYPOINT ["nginx", "-g", "daemon off;"] 
```

**Créer l'image de l'IHM**

>Depuis le répertoire contenant le Dockerfile

`docker build -t image_ideflix_ihm .` 

## Execution
lancer le docker-compose.yml avec les images construites précédemment
comande à lancer :

`docker-compose up -d`
