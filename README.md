# 🎮 Gacha Game - Projet de Microservices

## 📝 À propos du projet

Le **Gacha Game** est un système basé sur le modèle de microservices qui simule un jeu où les joueurs peuvent collecter, entraîner et combattre avec des monstres. Il repose sur **Spring Boot** pour les services, **MongoDB** pour la gestion des données et **Docker** pour l'exécution.

L’architecture est divisée en trois APIs indépendantes qui communiquent entre elles :

1. **Authentification API (`authentification-api`)** - Gestion des utilisateurs et de l’authentification.
2. **Joueur API (`player-api`)** - Gestion des joueurs et de leurs monstres.
3. **Monstres API (`monster-api`)** - Gestion et amélioration des monstres.

## ⚙️ Technologies utilisées

- **Java 21** avec **Spring Boot**
- **MongoDB** comme base de données
- **Docker & Docker Compose** pour la conteneurisation
- **Swagger UI** pour la documentation et le test des endpoints REST
- **Spring RestTemplate** pour la communication entre APIs

---

## 📌 Fonctionnalités des APIs

### 1️⃣ Authentification API (`authentification-api`)
🔹 Permet aux utilisateurs de s'inscrire et de se connecter.  
🔹 Génère un token d'authentification (non JWT, mais simple token maison).  
🔹 Vérifie la validité des tokens envoyés par les autres APIs.

📍 **Endpoints principaux :**
- `POST /auth/register` ➝ Inscription d'un utilisateur.
- `POST /auth/login` ➝ Connexion et récupération du token.
- `GET /auth/validate?token=XXXX` ➝ Vérification de la validité du token.

---

### 2️⃣ Joueur API (`player-api`)
🔹 Gère les informations du joueur (expérience, niveau, liste de monstres).  
🔹 Communique avec l’API d’authentification pour sécuriser les endpoints.  
🔹 Permet l’acquisition et la suppression de monstres.

📍 **Endpoints principaux :**
- `POST /player/create` ➝ Création d’un joueur.
- `GET /player/{id}` ➝ Récupération des informations d’un joueur.
- `POST /player/{id}/gain-xp?amount=XX` ➝ Gain d'expérience.
- `POST /player/{id}/add-monster/{monsterId}` ➝ Ajout d’un monstre au joueur.

---

### 3️⃣ Monstres API (`monster-api`)
🔹 Gère les monstres du jeu avec leurs caractéristiques et compétences.  
🔹 Permet de créer et améliorer des monstres.  
🔹 Enregistre les statistiques après un combat.

📍 **Endpoints principaux :**
- `POST /monster/create` ➝ Création d'un monstre.
- `GET /monster/{id}` ➝ Récupération des informations d’un monstre.
- `POST /monster/{id}/level-up` ➝ Amélioration du monstre après un combat.

---

## 🚀 Démarrage du projet

### 1️⃣ Prérequis
Assurez-vous d’avoir :
- **Docker** et **Docker Compose** installés
- **Git** pour cloner le projet

### 2️⃣ Lancer les services
 
✅ Lancer toutes les APIs + MongoDB avec Docker Compose
``` bash
# Cette commande construit les images avant de démarrer les conteneurs.
# Elle exécute les conteneurs en mode premier plan, ce qui signifie que les logs et la sortie sont affichés directement dans le terminal.
# Vous pouvez arrêter le processus avec CTRL + C.
# Cas d'utilisation : 
#🔹Lorsque vous voulez voir les logs en direct et déboguer facilement.

docker-compose up --build
```

``` bash
# Le drapeau -d signifie mode détaché.
# Il construit les images et démarre les conteneurs en arrière-plan.
# Les logs ne s'affichent pas dans le terminal, mais les conteneurs restent en cours d'exécution.
# Cas d'utilisation : 
#🔹Lorsque vous souhaitez exécuter les services en arrière-plan et continuer à utiliser le terminal pour autre chose.

docker-compose up --build -d
```

✅ Lancer uniquement une API spécifique
``` bash
# Pour l'API Authentification
docker-compose up auth-api --build   
```
``` bash
# Pour l'API Joueur
docker-compose up player-api --build 
```

``` bash
# Pour l'API Monstres
docker-compose up monster-api --build 
```

✅ Arrêter tous les services
``` bash
docker-compose down
```

✅ Pour vérifier si les conteneurs tournent en mode détaché, utilisez :

```bash
docker ps
```

### 3️⃣ 🌐 Tester les APIs avec Swagger

Une fois les services démarrés, ouvrez Swagger UI pour tester les endpoints :

> Authentification API → http://localhost:8081/swagger-ui/index.html

> Joueur API → http://localhost:8082/swagger-ui/index.html

> Monstres API → http://localhost:8083/swagger-ui/index.html




