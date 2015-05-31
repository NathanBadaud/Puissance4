package com.example.Puissance_Engine_4;

import java.util.Random;

/**
 * Created by Benjamin on 30/05/2015.
 */
public class IA extends Joueur {

    public IA(Jeu jeu, String nom, String couleur, int pions) {
        super(jeu, nom, couleur, pions);
        this.estHumain = false;
    }

    //methodes specifiques à l'IA a developper : determiner un pion a placer...
    public int[] placementPion() {
        int[] coordonnees = new int[2]; // [0] = colonne, [1] = ligne
        do {
            coordonnees[0] = (int) (Math.random() * jeu.plateau.largeur);
            coordonnees[1] = jeu.determinerCaseDisponible(coordonnees[0]);
        } while (coordonnees[1] >= jeu.plateau.hauteur);
        jeu.placerPion(coordonnees[0], coordonnees[1]);
        return coordonnees;
    }
}
