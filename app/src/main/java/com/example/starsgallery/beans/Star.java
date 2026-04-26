package com.example.starsgallery.beans;

public class Star {

    private int starId;
    private String fullName;
    private String photoUrl;
    private float score;
    private static int autoIncrement = 0;

    // Constructeur : crée une nouvelle star avec un identifiant automatique
    public Star(String fullName, String photoUrl, float score) {
        this.starId = ++autoIncrement;
        this.fullName = fullName;
        this.photoUrl = photoUrl;
        this.score = score;
    }

    // Getters
    public int getId() { return starId; }
    public String getName() { return fullName; }
    public String getImg() { return photoUrl; }
    public float getRating() { return score; }

    // Setters
    public void setName(String fullName) { this.fullName = fullName; }
    public void setImg(String photoUrl) { this.photoUrl = photoUrl; }
    public void setRating(float score) { this.score = score; }
}