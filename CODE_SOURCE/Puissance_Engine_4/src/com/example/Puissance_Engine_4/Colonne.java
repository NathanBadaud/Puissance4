package com.example.Puissance_Engine_4;

/**
 * Created by Benjamin on 27/05/2015.
 */
public class Colonne {
    public int numeroColonne;
    public Case[] cases;
    public int premiereCaseLibre;

    public Colonne(int numeroColonne, int hauteur) {
        this.numeroColonne = numeroColonne;
        this.premiereCaseLibre = 0;

        this.cases = new Case[hauteur];
        for (int i = 0; i < hauteur; i++) {
            this.cases[i] = new Case(numeroColonne, i);
        }
    }

    public boolean colonneDisponible(int hauteur) {
        if (premiereCaseLibre >= hauteur) {
            return false;
        } else {
            return true;
        }
    }
}
