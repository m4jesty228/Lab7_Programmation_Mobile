package com.example.starsgallery.service;

import com.example.starsgallery.beans.Star;
import com.example.starsgallery.dao.IDao;
import java.util.ArrayList;
import java.util.List;

// Service singleton gérant la liste des stars en mémoire
public class StarService implements IDao<Star> {

    private List<Star> catalogue;
    private static StarService uniqueInstance;

    // Constructeur privé : initialise la liste et charge les données de test
    private StarService() {
        catalogue = new ArrayList<>();
        loadSampleData();
    }

    // Retourne l'instance unique du service
    public static StarService getInstance() {
        if (uniqueInstance == null) uniqueInstance = new StarService();
        return uniqueInstance;
    }

    // Personnages d'animés avec leurs images et notes
    private void loadSampleData() {
        catalogue.add(new Star("Naruto Uzumaki", "https://pngimg.com/uploads/naruto/naruto_PNG30.png", 4.8f));
        catalogue.add(new Star("Monkey D. Luffy", "https://tse4.mm.bing.net/th/id/OIP.2scAWyogmZqYB9566FCrOwHaIr?rs=1&pid=ImgDetMain&o=7&rm=3", 3.2f));
        catalogue.add(new Star("Goku", "https://tse4.mm.bing.net/th/id/OIP.5-J1pNpCf7yPXhEj4vs9XAHaKq?rs=1&pid=ImgDetMain&o=7&rm=3", 4.9f));
        catalogue.add(new Star("Levi Ackerman", "https://tse3.mm.bing.net/th/id/OIP.hgimkb7HgCaeBL8hmI9WZQHaF9?rs=1&pid=ImgDetMain&o=7&rm=3", 4.6f));
        catalogue.add(new Star("Ichigo Kurosaki", "https://tse4.mm.bing.net/th/id/OIP.xAt5Q_b0AEYsp6qghza_-gHaKa?rs=1&pid=ImgDetMain&o=7&rm=3", 4.5f));
        catalogue.add(new Star("Sasuke Uchiha", "https://e1.pxfuel.com/desktop-wallpaper/729/1018/desktop-wallpaper-172-best-personajes-naruto-sasuke-full-body.jpg", 4.7f));
        catalogue.add(new Star("Light Yagami", "https://tse4.mm.bing.net/th/id/OIP.XbMlQNvN_6bmVHBAhOkhJAHaH6?rs=1&pid=ImgDetMain&o=7&rm=3", 4.4f));
        catalogue.add(new Star("Edward Elric", "https://tse4.mm.bing.net/th/id/OIP.fxzIyr0vNfSlyjlT9J7wqwAAAA?rs=1&pid=ImgDetMain&o=7&rm=3", 4.6f));
        catalogue.add(new Star("Zoro Roronoa", "https://tse3.mm.bing.net/th/id/OIP.IP1iaH_1C3X6Oe5krW7HjgHaJV?rs=1&pid=ImgDetMain&o=7&rm=3", 4.5f));
        catalogue.add(new Star("Eren Yeager", "https://tse1.mm.bing.net/th/id/OIP.MizAhI29VpYO5l7E5BYcxAHaPC?rs=1&pid=ImgDetMain&o=7&rm=3", 4.3f));
    }

    // Ajoute une nouvelle star à la liste
    @Override
    public boolean create(Star item) {
        return catalogue.add(item);
    }

    // Met à jour les informations d'une star existante
    @Override
    public boolean update(Star item) {
        for (Star current : catalogue) {
            if (current.getId() == item.getId()) {
                current.setName(item.getName());
                current.setImg(item.getImg());
                current.setRating(item.getRating());
                return true;
            }
        }
        return false;
    }

    // Supprime une star de la liste
    @Override
    public boolean delete(Star item) {
        return catalogue.remove(item);
    }

    // Recherche une star par son identifiant unique
    @Override
    public Star findById(int id) {
        for (Star current : catalogue) {
            if (current.getId() == id) return current;
        }
        return null;
    }

    // Retourne toute la liste des stars
    @Override
    public List<Star> findAll() {
        return catalogue;
    }
}