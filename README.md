# Démonstration du fonctionnement de l'application

## Introduction
Cette application est développée avec Spring et permet de gérer les commandes d'un magasin.

## 1. Accès à l'application
L'application est accessible à l'adresse suivante :
```
http://localhost:8080/store/index
```

## 2. Gestion des clients
### Création d'un compte
Un utilisateur peut créer un compte en fournissant :
- Email (identifiant unique)
- Mot de passe
- Nom
- Prénom

**Capture d'écran :** ![Création de compte](https://github.com/salim2607/TP2-spring/blob/3473e5768a94e9cd3ed156863e601698cceaa0d0/photos/Capture%20d%E2%80%99%C3%A9cran%202025-03-06%20%C3%A0%2008.53.49.png)

### Connexion
L'utilisateur peut se connecter en saisissant son email et son mot de passe.


### Déconnexion
Un bouton "Déconnexion" permet de quitter la session.

## 3. Gestion des commandes
### Création d'une commande
Une commande peut être créée et associée à un client connecté.


### Affichage des commandes
L'utilisateur peut consulter la liste de ses commandes.

**Capture d'écran :** ![Liste des commandes](# Démonstration du fonctionnement de l'application

## Introduction
Cette application est développée avec Spring et permet de gérer les commandes d'un magasin.

## 1. Accès à l'application
L'application est accessible à l'adresse suivante :
```
http://localhost:8080/store/index
```

## 2. Gestion des clients
### Création d'un compte
Un utilisateur peut créer un compte en fournissant :
- Email (identifiant unique)
- Mot de passe
- Nom
- Prénom


### Connexion
L'utilisateur peut se connecter en saisissant son email et son mot de passe.



### Déconnexion
Un bouton "Déconnexion" permet de quitter la session.

## 3. Gestion des commandes
### Création d'une commande
Une commande peut être créée et associée à un client connecté.


### Affichage des commandes
L'utilisateur peut consulter la liste de ses commandes.


## 4. Gestion des articles
### Ajout d'un article dans une commande
L'utilisateur peut ajouter un article avec :
- Libellé
- Quantité
- Prix unitaire


### Suppression d'un article
Un article peut être supprimé d'une commande.

**Capture d'écran :** ![ Ajout et Suppression d'article](Capture d’écran 2025-03-06 à 09.07.08.png)

## 5. Impression des commandes
Une page permet d'afficher les articles d'une commande avec une option d'impression.

**Capture d'écran :** ![Impression commande](https://github.com/salim2607/TP2-spring/blob/93958a203b4139d35ef329401ff79cfb26190fc6/photos/Capture%20d%E2%80%99%C3%A9cran%202025-03-06%20%C3%A0%2009.07.08.png)

## 6. Sécurité et protection des routes
Seul un client connecté peut gérer ses commandes. Les accès non autorisés sont redirigés vers la page de connexion.



