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