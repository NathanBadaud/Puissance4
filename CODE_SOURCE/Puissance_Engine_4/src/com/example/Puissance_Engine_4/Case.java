package com.example.Puissance_Engine_4;

/**
 * Created by Benjamin on 27/05/2015.
 */
public class Case {
    public int positionX, positionY;
    public String statutCase;
    public Joueur proprietaire;

    public Case(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.statutCase = "libre";
    }

    public void affecterPion(Joueur proprietaire) {
        this.proprietaire = proprietaire;
        this.proprietaire.pionsRestants -= 1;
        this.statutCase = "occupée";
    }
}
