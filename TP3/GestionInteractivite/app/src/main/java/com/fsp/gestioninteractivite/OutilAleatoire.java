/*
 * G?n?ration de valeurs al?atoires
 * OutilAleatoire.java										01/09/14
 */
package com.fsp.gestioninteractivite;


/**
 * G?n?ration de valeurs al?atoires
 * @author INFO2
 * @version 1.0
 *
 */
public class OutilAleatoire {

    /**
     * Renvoie une cha?ne de caract?res ?gale ? "pile" ou "face"
     * Le choix entre les 2 valeurs est fait de mani?re al?atoire
     */
    public static String tiragePileFace() {
        return Math.random() < 0.5 ? "pile" : "face";
    }

    /**
     * Renvoie un nombre entier choisi de manière aléatoire entre 0
     * et une borne maximum donnée en paramétre (borne incluse)
     * @param borneMaxi  borne maximum pour la g?n?ration du nombre al?atoire
     * @return un entier compris entre 0 et la borne maximum (incluse)
     */
    public static int tirageAleatoire(int borneMaxi) {
        return (int) (Math.random() * (borneMaxi+1));
    }

}