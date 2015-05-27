package com.example.Puissance_Engine_4;

/**
 * Created by Benjamin on 27/05/2015.
 */
public class Pion {
    public String couleur, statutPion;
    public Joueur proprietaire;

    public void creerPion(Joueur proprietaire, String couleur) {
        this.proprietaire = proprietaire;
        this.couleur = couleur;
        this.statutPion = "restant";
    }
}
