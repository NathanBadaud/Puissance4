package com.example.Puissance_Engine_4;

/**
 * Created by Benjamin on 27/05/2015.
 */
public class Case {
    public int positionX, positionY;
    public String statutCase;
    public Pion pion;

    public Case(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.statutCase = "libre";
    }

    public void affecterPion(Pion pion) {
        this.pion = pion;
        this.pion.proprietaire.pionsRestants -= 1;
    }
}
