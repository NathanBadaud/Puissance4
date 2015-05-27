package com.example.Puissance_Engine_4;

/**
 * Created by Benjamin on 27/05/2015.
 */
public class Plateau {
    public int largeur, hauteur;
    public Colonne colonnes[];

    public Plateau(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;

        for (int i = 0; i < largeur; i++) {
            this.colonnes[i] = new Colonne(largeur, hauteur);
        }
    }
}
