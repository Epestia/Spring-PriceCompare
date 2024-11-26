
# Documentation API - AuthenticationController

## Description

Le **AuthenticationController** gère l'authentification des utilisateurs en générant des tokens JWT basés sur les identifiants fournis. Il propose également une fonctionnalité pour rafraîchir un token existant.

---

## 1 : Endpoints pour l'authentification et la gestion des tokens

### 1. Générer un token JWT

```http
POST /api/authenticate
```

**Résumé**: Génère un token JWT après vérification des identifiants.  
**Corps de la requête**:
```json
{
  "username": "string",
  "password": "string"
}
```
**Réponses**:
- **200**: Token généré avec succès.  
  Exemple de réponse :
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
  ```

- **401**: Identifiants invalides.  
  Exemple de réponse :
  ```json
  {
    "error": "INVALID_CREDENTIALS"
  }
  ```

---

### 2. Rafraîchir un token JWT

```http
GET /api/refresh
```

**Résumé**: Rafraîchit un token JWT valide.  
**En-tête de la requête**:
- **Authorization**: `Bearer <token>`

**Réponses**:
- **200**: Token rafraîchi avec succès.  
  Exemple de réponse :
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
  ```

- **400**: Le token ne peut pas être rafraîchi.  
  Exemple de réponse :
  ```json
  {
    "error": "TOKEN_CANNOT_BE_REFRESHED"
  }
  ```

- **401**: Token invalide ou expiré.  
  Exemple de réponse :
  ```json
  {
    "error": "INVALID_TOKEN"
  }
  ```

# Documentation API - UserController

## 2 : Endpoints pour gérer les utilisateurs

### 1. Créer un nouvel utilisateur
- **URL**: `/api/utilisateurs`
- **Méthode**: `POST`
- **Résumé**: Crée un nouvel utilisateur dans le système.
- **Description**: Ajoute un nouvel utilisateur avec ses informations personnelles et ses rôles associés.
- **Réponses**:
    - **201**: Utilisateur créé avec succès.
    - **400**: Les données fournies sont invalides ou incomplètes.

---

### 2. Supprimer un utilisateur
- **URL**: `/api/utilisateurs/{id}`
- **Méthode**: `DELETE`
- **Résumé**: Supprime un utilisateur par son ID unique.
- **Description**: Supprime un utilisateur du système en fonction de son ID.
- **Réponses**:
    - **204**: Utilisateur supprimé avec succès.
    - **404**: Utilisateur non trouvé pour l'ID fourni.

---

### 3. Mettre à jour partiellement un utilisateur
- **URL**: `/api/utilisateurs/{id}`
- **Méthode**: `PATCH`
- **Résumé**: Met à jour certains champs d'un utilisateur existant.
- **Description**: Modifie partiellement les informations d'un utilisateur (par exemple, ville ou rôles), sans affecter les autres données.
- **Réponses**:
    - **200**: Utilisateur mis à jour avec succès.
    - **404**: Utilisateur non trouvé pour l'ID fourni.
    - **400**: Les données fournies sont invalides ou incomplètes.

---

### 4. Récupérer un utilisateur par ID
- **URL**: `/api/utilisateurs/{id}`
- **Méthode**: `GET`
- **Résumé**: Récupère un utilisateur à partir de son ID unique.
- **Description**: Obtient les détails de l'utilisateur en utilisant son identifiant unique.
- **Réponses**:
    - **200**: Utilisateur trouvé et retourné avec succès.
    - **404**: Aucun utilisateur trouvé pour l'ID fourni.

---

## Exemple de réponses pour la création (201)

### Créer un utilisateur (201) 
```json
{
  "id": 1,
  "nom": "Jean",
  "prenom": "Dupont",
  "ville": "Bruxelles",
  "email": "jean.dupont@example.com",
  "role": "USER"
}
```
# Ajout des rôles dans la base de données

## **Contexte**

Dans le cadre de la gestion des utilisateurs et des autorisations, trois nouveaux rôles ont été ajoutés manuellement dans la base de données afin de renforcer la gestion des permissions au sein de l'application.

## **Détails des rôles ajoutés**

Les trois rôles suivants ont été créés dans la base de données :

1. **Rôle "SUPER_ADMIN"**
2. **Rôle "ADMIN"**
3. **Rôle "USER"** (par défaut)

## **Mise à jour dans la base de données**

Les rôles ont été ajoutés à la table `roles` de la base de données, et les utilisateurs existants peuvent désormais être associés à ces rôles en fonction de leurs besoins.

### **Le SQL d'ajout des rôles** :

```sql
INSERT INTO roles (role_name) VALUES
                                ('SUPER_ADMIN'),
                                ('ADMIN'),
                                ('USER');
```
# Documentation API - ArticleController

## 3 : Endpoints pour gérer les articles

### 1. Récupérer tous les articles
- **URL**: `/api/articles`
- **Méthode**: `GET`
- **Résumé**: Récupère tous les articles dans le système.
- **Description**: Obtient une liste de tous les articles disponibles dans le système.
- **Réponses**:
  - **200**: Liste des articles récupérée avec succès.
  - **404**: Aucun article trouvé.

---

### 2. Récupérer un article par son ID
- **URL**: `/api/articles/{id}`
- **Méthode**: `GET`
- **Résumé**: Récupère un article à partir de son ID unique.
- **Description**: Obtenez les détails de l'article en fonction de son identifiant unique.
- **Réponses**:
  - **200**: Article récupéré avec succès.
  - **404**: Article non trouvé pour l'ID fourni.

---

### 3. Créer un nouvel article
- **URL**: `/api/articles`
- **Méthode**: `POST`
- **Résumé**: Crée un nouvel article dans le système.
- **Description**: Ajoute un nouvel article avec les informations spécifiées.
- **Réponses**:
  - **201**: Article créé avec succès.
  - **400**: Les données fournies sont invalides ou incomplètes.

---

### 4. Mettre à jour un article existant
- **URL**: `/api/articles/{id}`
- **Méthode**: `PUT`
- **Résumé**: Met à jour les détails d'un article existant.
- **Description**: Met à jour un article avec de nouvelles informations.
- **Réponses**:
  - **200**: Article mis à jour avec succès.
  - **404**: Article non trouvé pour l'ID fourni.
  - **400**: Les données fournies sont invalides ou incomplètes.

---

### 5. Supprimer un article
- **URL**: `/api/articles/{id}`
- **Méthode**: `DELETE`
- **Résumé**: Supprime un article par son ID unique.
- **Description**: Supprime un article en fonction de son identifiant unique.
- **Réponses**:
  - **204**: Article supprimé avec succès.
  - **404**: Article non trouvé pour l'ID fourni.

---

### 6. Trouver un article par son nom
- **URL**: `/api/articles/nom/{nom}`
- **Méthode**: `GET`
- **Résumé**: Trouve un article par son nom.
- **Description**: Recherche un article en fonction de son nom.
- **Réponses**:
  - **200**: Article récupéré avec succès.
  - **404**: Article non trouvé pour le nom fourni.

---

### 7. Trouver un article par son prix
- **URL**: `/api/articles/prix/{prix}`
- **Méthode**: `GET`
- **Résumé**: Trouve un article par son prix.
- **Description**: Recherche un article en fonction de son prix.
- **Réponses**:
  - **200**: Article récupéré avec succès.
  - **404**: Article non trouvé pour le prix spécifié.

---

### 8. Trouver un article par son nom et son unité
- **URL**: `/api/articles/nom-unite`
- **Méthode**: `GET`
- **Résumé**: Trouve un article par son nom et son unité.
- **Description**: Recherche un article en fonction de son nom et de son unité.
- **Réponses**:
  - **200**: Article récupéré avec succès.
  - **404**: Article non trouvé pour le nom et l'unité spécifiés.

---

## Exemple de réponses pour la création (201)

### Créer un article (201)
```json
{
  "id": 1,
  "nom": "Coca-Cola",
  "prix": 1.99,
  "unite": "Litre"
}
```

# Documentation API - PriceController

## 4 : Endpoints pour gérer les prix

### 1. Créer un nouveau prix
- **URL**: `/api/prices`
- **Méthode**: `POST`
- **Résumé**: Crée un nouveau prix pour un produit dans un magasin.
- **Description**: Ajoute un nouveau prix associé à un produit, un magasin et éventuellement une promotion.
- **Réponses**:
  - **201**: Prix créé avec succès.
  - **400**: Les données fournies sont invalides ou incomplètes.

---

### 2. Récupérer tous les prix
- **URL**: `/api/prices`
- **Méthode**: `GET`
- **Résumé**: Récupère tous les prix dans le système.
- **Description**: Obtient une liste de tous les prix disponibles dans le système.
- **Réponses**:
  - **200**: Liste des prix récupérée avec succès.
  - **404**: Aucun prix trouvé.

---

### 3. Récupérer un prix par ID
- **URL**: `/api/prices/{id}`
- **Méthode**: `GET`
- **Résumé**: Récupère un prix à partir de son ID unique.
- **Description**: Obtenez les détails du prix en fonction de son identifiant unique.
- **Réponses**:
  - **200**: Prix récupéré avec succès.
  - **404**: Prix non trouvé pour l'ID fourni.

---

### 4. Mettre à jour un prix existant
- **URL**: `/api/prices/{id}`
- **Méthode**: `PUT`
- **Résumé**: Met à jour les détails d'un prix existant.
- **Description**: Met à jour un prix avec de nouvelles informations, telles que le montant ou la période de validité.
- **Réponses**:
  - **200**: Prix mis à jour avec succès.
  - **404**: Prix non trouvé pour l'ID fourni.
  - **400**: Les données fournies sont invalides ou incomplètes.

---

### 5. Supprimer un prix
- **URL**: `/api/prices/{id}`
- **Méthode**: `DELETE`
- **Résumé**: Supprime un prix par son ID unique.
- **Description**: Supprime un prix en fonction de son identifiant unique.
- **Réponses**:
  - **204**: Prix supprimé avec succès.
  - **404**: Prix non trouvé pour l'ID fourni.

---

## Exemple de réponses pour la création (201)

### Créer un prix (201)
```json
{
  "priceID": 0,
  "article": {
    "articleId":5 ,
    "articleName": "Livres",
    "unit": {
      "unitId": 1,
      "unitName": "Kg"
    },
    "articleDefaultPrice": 3.96,
    "lastUpdated": "2024-11-22T13:45:59.657Z"
  },
  "store": {
    "storeId": 2,
    "storeName": "string",
    "storeCity": "string",
    "storeAddress": "string"
  },
  "startDate": "2024-11-22T13:45:59.657Z",
  "endDate": "2024-11-22T13:45:59.657Z",
  "price": 0,
  "previousPrice": 0,
  "isPromotion": true,
  "isEstimatedPrice": true
}
```
# Documentation API - StoreController

## 5 : Endpoints pour gérer les magasins

### 1. Récupérer tous les magasins
- **URL**: `/api/stores`
- **Méthode**: `GET`
- **Résumé**: Récupère tous les magasins dans le système.
- **Description**: Obtient une liste de tous les magasins disponibles dans le système.
- **Réponses**:
  - **200**: Liste des magasins récupérée avec succès.
  - **404**: Aucun magasin trouvé.

---

### 2. Récupérer un magasin par son ID
- **URL**: `/api/stores/{id}`
- **Méthode**: `GET`
- **Résumé**: Récupère un magasin à partir de son ID unique.
- **Description**: Obtenez les détails du magasin en fonction de son identifiant unique.
- **Réponses**:
  - **200**: Magasin récupéré avec succès.
  - **404**: Magasin non trouvé pour l'ID fourni.

---

### 3. Créer un nouveau magasin
- **URL**: `/api/stores`
- **Méthode**: `POST`
- **Résumé**: Crée un nouveau magasin dans le système.
- **Description**: Ajoute un nouveau magasin avec les informations spécifiées.
- **Réponses**:
  - **201**: Magasin créé avec succès.
  - **400**: Les données fournies sont invalides ou incomplètes.

---

### 4. Mettre à jour un magasin existant
- **URL**: `/api/stores/{id}`
- **Méthode**: `PUT`
- **Résumé**: Met à jour les détails d'un magasin existant.
- **Description**: Met à jour un magasin avec de nouvelles informations, telles que l'adresse ou le nom.
- **Réponses**:
  - **200**: Magasin mis à jour avec succès.
  - **404**: Magasin non trouvé pour l'ID fourni.
  - **400**: Les données fournies sont invalides ou incomplètes.

---

### 5. Supprimer un magasin
- **URL**: `/api/stores/{id}`
- **Méthode**: `DELETE`
- **Résumé**: Supprime un magasin par son ID unique.
- **Description**: Supprime un magasin en fonction de son identifiant unique.
- **Réponses**:
  - **204**: Magasin supprimé avec succès.
  - **404**: Magasin non trouvé pour l'ID fourni.

---

## Exemple de réponses pour la création (201)

### Créer un magasin (201)
```json
{
  "storeId": 1,
  "storeName": "Supermarché ABC",
  "storeCity": "Bruxelles",
  "storeAddress": "Rue du Commerce 12"
}
```
# Documentation API - PriceReportController

## Description

L'API **PriceReportController** permet de gérer les rapports de prix signalés par les utilisateurs. Les fonctionnalités incluent la création, la récupération, la mise à jour et la suppression des rapports.

---

## 6 : Endpoints pour gérer les rapports de prix

### 1. Récupérer tous les rapports de prix

- **URL**: `/api/price-reports`
- **Méthode**: `GET`
- **Résumé**: Récupère tous les rapports de prix dans le système.
- **Description**: Obtient une liste de tous les rapports de prix disponibles, incluant les informations sur l'article, le magasin, l'utilisateur, le prix signalé, et d'autres détails.
- **Réponses**:
  - **200**: Liste des rapports de prix récupérée avec succès.
  - **404**: Aucun rapport de prix trouvé.

---

### 2. Récupérer un rapport de prix par son ID

- **URL**: `/api/price-reports/{id}`
- **Méthode**: `GET`
- **Résumé**: Récupère un rapport de prix à partir de son ID unique.
- **Description**: Obtenez les détails d’un rapport de prix en fonction de son identifiant unique.
- **Réponses**:
  - **200**: Rapport de prix récupéré avec succès.
  - **404**: Rapport de prix non trouvé pour l’ID fourni.

---

### 3. Créer un nouveau rapport de prix

- **URL**: `/api/price-reports`
- **Méthode**: `POST`
- **Résumé**: Crée un nouveau rapport de prix dans le système.
- **Description**: Ajoute un rapport de prix contenant les informations sur l'article, le magasin, l'utilisateur, et le prix signalé.
- **Réponses**:
  - **201**: Rapport de prix créé avec succès.
  - **400**: Les données fournies sont invalides ou incomplètes.

---

### 4. Mettre à jour un rapport de prix existant

- **URL**: `/api/price-reports/{id}`
- **Méthode**: `PUT`
- **Résumé**: Met à jour les détails d’un rapport de prix existant.
- **Description**: Met à jour un rapport de prix avec de nouvelles informations comme un prix signalé ou une validation.
- **Réponses**:
  - **200**: Rapport de prix mis à jour avec succès.
  - **404**: Rapport de prix non trouvé pour l’ID fourni.
  - **400**: Les données fournies sont invalides ou incomplètes.

---

### 5. Supprimer un rapport de prix

- **URL**: `/api/price-reports/{id}`
- **Méthode**: `DELETE`
- **Résumé**: Supprime un rapport de prix par son ID unique.
- **Description**: Supprime un rapport de prix en fonction de son identifiant unique. Cette opération est irréversible.
- **Réponses**:
  - **204**: Rapport de prix supprimé avec succès.
  - **404**: Rapport de prix non trouvé pour l’ID fourni.

---

## Exemple de réponse pour la création (201)

```json
{
  "reportId": 1,
  "article": {
    "articleId": 101,
    "articleName": "Lait",
    "unit": {
      "unitId": 1,
      "unitName": "Litre"
    },
    "articleDefaultPrice": 1.2,
    "lastUpdated": "2024-11-20T08:45:00.000Z"
  },
  "store": {
    "storeId": 5,
    "storeName": "Supermarché ABC",
    "storeCity": "Bruxelles",
    "storeAddress": "Rue du Commerce 12"
  },
  "user": {
    "userId": 42,
    "firstName": "Alice",
    "lastName": "Dupont",
    "city": "Liège",
    "birthDate": "1990-05-15",
    "mail": "alice.dupont@example.com",
    "roles": [
      {
        "roleId": 2,
        "roleName": "USER"
      }
    ]
  },
  "reportedPrice": 1.1,
  "reportDate": "2024-11-20T09:00:00.000Z",
  "proofImage": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA",
  "isValidated": true
}
```
# Documentation API - ExportController

## Description

L'API **ExportController** gère les opérations relatives aux exportations de données, permettant de créer, lire, mettre à jour et supprimer des exportations.

---

## 7 : Endpoints pour gérer les exportations

### 1. Créer une nouvelle exportation

- **URL**: `/exports`
- **Méthode**: `POST`
- **Résumé**: Crée une nouvelle exportation de données.
- **Description**: Ajoute une nouvelle exportation en spécifiant les données exportées, l'utilisateur associé, et le type d'export.
- **Réponses**:
  - **201**: Exportation créée avec succès.
  - **400**: Données invalides ou incomplètes.

---

### 2. Récupérer toutes les exportations

- **URL**: `/exports`
- **Méthode**: `GET`
- **Résumé**: Récupère toutes les exportations disponibles.
- **Description**: Liste toutes les exportations présentes dans le système, incluant les informations liées aux utilisateurs et aux articles associés.
- **Réponses**:
  - **200**: Liste des exportations récupérée avec succès.
  - **404**: Aucune exportation trouvée.

---

### 3. Récupérer une exportation par ID

- **URL**: `/exports/{exportId}`
- **Méthode**: `GET`
- **Résumé**: Récupère une exportation à partir de son ID unique.
- **Description**: Permet de consulter les détails d'une exportation donnée grâce à son identifiant.
- **Réponses**:
  - **200**: Exportation récupérée avec succès.
  - **404**: Exportation non trouvée pour l'ID fourni.

---

### 4. Mettre à jour une exportation existante

- **URL**: `/exports/{exportId}`
- **Méthode**: `PUT`
- **Résumé**: Met à jour une exportation existante.
- **Description**: Modifie les informations d'une exportation existante, telles que son type, les données exportées, ou l'article lié.
- **Réponses**:
  - **200**: Exportation mise à jour avec succès.
  - **404**: Exportation non trouvée pour l'ID fourni.
  - **400**: Données invalides ou incomplètes.

---

### 5. Supprimer une exportation

- **URL**: `/exports/{exportId}`
- **Méthode**: `DELETE`
- **Résumé**: Supprime une exportation par son ID.
- **Description**: Efface une exportation existante de manière permanente.
- **Réponses**:
  - **204**: Exportation supprimée avec succès.
  - **404**: Exportation non trouvée pour l'ID fourni.

---

## Exemple de réponse pour la création (201)

```json
{
  "exportId": 1,
  "user": {
    "userId": 42,
    "firstName": "Alice",
    "lastName": "Dupont",
    "city": "Liège",
    "birthDate": "1990-05-15",
    "mail": "alice.dupont@example.com",
    "passwordHash": "hashed_password",
    "roles": [
      {
        "roleId": 2,
        "roleName": "USER"
      }
    ]
  },
  "article": {
    "articleId": 101,
    "articleName": "Lait",
    "unit": {
      "unitId": 1,
      "unitName": "Litre"
    },
    "articleDefaultPrice": 1.2,
    "lastUpdated": "2024-11-20T08:45:00.000Z"
  },
  "exportDate": "2024-11-20T09:00:00.000Z",
  "exportType": "CSV",
  "exportData": "base64_encoded_string"
}
```
# Documentation API - NotificationController

## **Description**

Le contrôleur **NotificationController** permet de gérer les notifications dans l'application. Les fonctionnalités incluent la création, la lecture, la mise à jour et la suppression des notifications.

---

##  8 : Endpoints pour gérer les Notifications

### **1. Créer une nouvelle notification**
- **URL**: `/api/notifications`
- **Méthode**: `POST`
- **Résumé**: Crée une nouvelle notification.
- **Description**: Permet d'ajouter une nouvelle notification en précisant l'utilisateur, l'article, le magasin, et les informations associées.
- **Réponses**:
  - **201**: Notification créée avec succès.
  - **400**: Données invalides ou incomplètes.

---

### **2. Récupérer toutes les notifications**
- **URL**: `/api/notifications`
- **Méthode**: `GET`
- **Résumé**: Récupère toutes les notifications disponibles.
- **Description**: Liste toutes les notifications enregistrées dans le système.
- **Réponses**:
  - **200**: Liste des notifications récupérée avec succès.
  - **404**: Aucune notification trouvée.

---

### **3. Récupérer une notification par ID**
- **URL**: `/api/notifications/{notificationId}`
- **Méthode**: `GET`
- **Résumé**: Récupère une notification par son ID unique.
- **Description**: Permet de consulter les détails d'une notification grâce à son identifiant.
- **Réponses**:
  - **200**: Notification récupérée avec succès.
  - **404**: Notification non trouvée pour l'ID fourni.

---

### **4. Mettre à jour une notification**
- **URL**: `/api/notifications/{notificationId}`
- **Méthode**: `PUT`
- **Résumé**: Met à jour une notification existante.
- **Description**: Modifie les informations d'une notification, comme le type, le message ou le statut de lecture.
- **Réponses**:
  - **200**: Notification mise à jour avec succès.
  - **404**: Notification non trouvée pour l'ID fourni.
  - **400**: Données invalides ou incomplètes.

---

### **5. Supprimer une notification**
- **URL**: `/api/notifications/{notificationId}`
- **Méthode**: `DELETE`
- **Résumé**: Supprime une notification par son ID.
- **Description**: Efface une notification de manière permanente.
- **Réponses**:
  - **204**: Notification supprimée avec succès.
  - **404**: Notification non trouvée pour l'ID fourni.

---

## **Exemple de réponse pour la création (201)**

```json
{
  "notificationId": 1,
  "user": {
    "userId": 42,
    "firstName": "Alice",
    "lastName": "Dupont",
    "city": "Liège",
    "birthDate": "1990-05-15",
    "mail": "alice.dupont@example.com",
    "passwordHash": "hashed_password",
    "roles": [
      {
        "roleId": 2,
        "roleName": "USER"
      }
    ]
  },
  "article": {
    "articleId": 101,
    "articleName": "Lait",
    "unit": {
      "unitId": 1,
      "unitName": "Litre"
    },
    "articleDefaultPrice": 1.2,
    "lastUpdated": "2024-11-20T08:45:00.000Z"
  },
  "store": {
    "storeId": 10,
    "storeName": "Carrefour",
    "storeCity": "Bruxelles",
    "storeAddress": "Rue du Commerce 123"
  },
  "notificationDate": "2024-11-20T09:00:00.000Z",
  "notificationType": "Price Drop",
  "notificationMessage": "Le prix de Lait a baissé dans Carrefour.",
  "read": false
}
```