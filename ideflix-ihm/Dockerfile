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
#CMD ["node_modules/.bin/ng", "serve", "--host", "0.0.0.0"]
