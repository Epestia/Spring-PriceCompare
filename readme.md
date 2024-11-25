# Documentation API - UserController

## Endpoints pour gérer les utilisateurs

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

## Exemple de réponses

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
# Documentation API - ArticleController

## Endpoints pour gérer les articles

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

## Exemple de réponses

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

## Endpoints pour gérer les prix

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

## Exemple de réponses

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

## Endpoints pour gérer les magasins

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

## Exemple de réponses

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

## Endpoints pour gérer les rapports de prix

### 1. Récupérer tous les rapports de prix
- **URL**: `/api/price-reports`
- **Méthode**: `GET`
- **Résumé**: Récupère tous les rapports de prix générés.
- **Description**: Obtient une liste de tous les rapports de prix générés dans le système.
- **Réponses**:
  - **200**: Liste des rapports de prix récupérée avec succès.
  - **404**: Aucun rapport de prix trouvé.

---

### 2. Récupérer un rapport de prix par son ID
- **URL**: `/api/price-reports/{id}`
- **Méthode**: `GET`
- **Résumé**: Récupère un rapport de prix à partir de son ID unique.
- **Description**: Obtient les détails d'un rapport de prix en fonction de son identifiant unique.
- **Réponses**:
  - **200**: Rapport de prix récupéré avec succès.
  - **404**: Rapport de prix non trouvé pour l'ID fourni.

---

### 3. Créer un rapport de prix
- **URL**: `/api/price-reports`
- **Méthode**: `POST`
- **Résumé**: Crée un nouveau rapport de prix.
- **Description**: Génère un rapport de prix en fonction des articles, magasins et périodes spécifiés.
- **Réponses**:
  - **201**: Rapport de prix créé avec succès.
  - **400**: Requête mal formulée (données manquantes ou invalides).

---

# Documentation API - ExportController

## Endpoints pour gérer les exportations

### 1. Exporter les données au format CSV
- **URL**: `/api/exports/csv`
- **Méthode**: `GET`
- **Résumé**: Exporte les rapports de prix au format CSV.
- **Description**: Permet d'exporter les rapports de prix dans un fichier CSV pour une analyse hors ligne.
- **Réponses**:
  - **200**: Exportation CSV réussie.
  - **500**: Erreur interne lors de l'exportation CSV.

---

### 2. Exporter les données au format PDF
- **URL**: `/api/exports/pdf`
- **Méthode**: `GET`
- **Résumé**: Exporte les rapports de prix au format PDF.
- **Description**: Permet d'exporter les rapports de prix dans un fichier PDF pour une analyse lisible.
- **Réponses**:
  - **200**: Exportation PDF réussie.
  - **500**: Erreur interne lors de l'exportation PDF.

---