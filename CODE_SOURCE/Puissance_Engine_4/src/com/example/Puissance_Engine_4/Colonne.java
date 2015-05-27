package com.example.Puissance_Engine_4;

/**
 * Created by Benjamin on 27/05/2015.
 */
public class Colonne {
    public int colonne;
    public Case cases[];

    public Colonne(int colonne, int hauteur) {
        this.colonne = colonne;
        for (int i = 0; i < hauteur; i++) {
            this.cases[i] = new Case(colonne, i);
        }
    }
}
