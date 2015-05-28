package com.example.Puissance_Engine_4;

/**
 * Created by Benjamin on 27/05/2015.
 */
public class Pion {
    public String statutPion;
    public Joueur proprietaire;

    public Pion(Joueur proprietaire) {
        this.proprietaire = proprietaire;
        this.statutPion = "restant";
    }
}
