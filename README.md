# StarsGallery 📱

Application Android développée dans le cadre d'un lab de développement mobile pour apprendre la gestion des **RecyclerView, Adapters, Filtrage dynamique et Animations**.

---

## 🎯 Objectifs
- Créer un écran de démarrage animé avec `SplashActivity`
- Afficher une liste de stars avec `RecyclerView` et `ViewHolder`
- Charger des images distantes avec **Glide** et `CircleImageView`
- Filtrer dynamiquement la liste via une `SearchView`
- Partager l'application via un menu d'options
- Modifier la note d'une star via un popup `AlertDialog`

---

## 🏗️ Structure du projet
```
StarsGallery/
├── ui/
│   ├── SplashActivity.java       # Écran de démarrage animé
│   └── ListActivity.java         # Activité principale + menu
├── adapter/
│   └── StarAdapter.java          # Adapter RecyclerView + filtre
├── beans/
│   └── Star.java                 # Modèle de données
├── dao/
│   └── IDao.java                 # Interface CRUD générique
├── service/
│   └── StarService.java          # Singleton + données
└── res/
    └── layout/
        ├── activity_splash.xml   # Layout écran de démarrage
        ├── activity_list.xml     # Layout liste principale
        ├── star_item.xml         # Layout d'un item de la liste
        └── star_edit_item.xml    # Layout du popup de notation
```

---

## ⚙️ Fonctionnement

| Action | Résultat |
|--------|----------|
| Lancement de l'app | Écran animé affiché pendant 5 secondes |
| Fin de l'animation | Redirection automatique vers la liste |
| Clic sur la loupe | Barre de recherche s'ouvre |
| Saisie d'un texte | Liste filtrée en temps réel |
| Clic sur le bouton partage | Sélecteur d'apps de partage s'ouvre |
| Clic sur une star | Popup de notation s'affiche |
| Validation de la note | Note mise à jour dans la liste |

---

## 🧩 Concepts clés
- **`RecyclerView`** — affichage performant de listes longues
- **`ViewHolder`** — recyclage des vues pour optimiser les performances
- **`Filterable`** — interface permettant le filtrage dynamique
- **`Glide`** — chargement et mise en cache des images distantes
- **`CircleImageView`** — affichage des images en forme circulaire
- **`AlertDialog`** — fenêtre contextuelle de notation
- **`ShareCompat`** — partage simplifié via les apps installées
- **`Singleton`** — pattern utilisé dans `StarService` pour une instance unique

---
## 🎬 Démonstration

https://github.com/user-attachments/assets/11a175b3-0add-4277-a4a7-9f091851050b

---
## 🛠️ Technologies utilisées
- **Langage** : Java
- **Min SDK** : API 24 (Android 7.0)
- **IDE** : Android Studio
- **Bibliothèques** : Glide, CircleImageView, RecyclerView

---

## 👤 Auteur
**DOSSAH Landry**  
ENSA Marrakech / GCDSTE  
Module : Programmation Mobile Android avec Java
