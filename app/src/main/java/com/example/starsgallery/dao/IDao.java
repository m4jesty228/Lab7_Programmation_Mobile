package com.example.starsgallery.dao;

import java.util.List;

// Interface générique définissant les opérations CRUD de base
public interface IDao<T> {

    // Ajouter un nouvel élément
    boolean create(T item);

    // Mettre à jour un élément existant
    boolean update(T item);

    // Supprimer un élément
    boolean delete(T item);

    // Rechercher un élément par son identifiant
    T findById(int id);

    // Récupérer tous les éléments
    List<T> findAll();
}