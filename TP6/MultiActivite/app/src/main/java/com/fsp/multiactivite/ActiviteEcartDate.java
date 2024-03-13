/*  Activité secondaire de l'application qui effectue des calculs sur les dates
    ActiviteEcartDate.java                                              08/23
*/
package com.fsp.multiactivite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe activité secondaire de l'application qui effectue des calculs sur les
 * dates.
 * Elle effectue le calcul du nombre de jours qui séparent 2 dates
 *
 * @author Servières
 * @version 1.0
 */
public class ActiviteEcartDate extends AppCompatActivity {

    /**
     * Zone de texte permettant d'afficher le nombre de jours qui séparent 2 dates
     */
    private TextView resultatNombreJour;

    /**
     * Sélecteur de la première date pour calculer l'écart entre 2 dates
     */
    private DatePicker selecteurPourEcart1;

    /**
     * Sélecteur de la deuxième date pour calculer l'écart entre 2 dates
     */
    private DatePicker selecteurPourEcart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ecart_date);

        // on accède aux zones d'affichage et aux sélecteurs de dates
        resultatNombreJour = findViewById(R.id.resultat_ecart);
        selecteurPourEcart1 = findViewById(R.id.selecteurDate1);
        selecteurPourEcart2 = findViewById(R.id.selecteurDate2);
    }

    /**
     * Méthode invoquée automatiquement lors d'un clic sur le bouton
     * "Rechercher le nombre de jours qui séparent deux dates"
     *
     * @param view source du clic
     */
    @SuppressLint("SetTextI18n")
    public void clicRechercherEcart(View view) {

        // on accède aux jours, mois et ans sélectionnés
        int jour1 = selecteurPourEcart1.getDayOfMonth();
        int mois1 = selecteurPourEcart1.getMonth() + 1;
        int an1 = selecteurPourEcart1.getYear();
        int jour2 = selecteurPourEcart2.getDayOfMonth();
        int mois2 = selecteurPourEcart2.getMonth() + 1;
        int an2 = selecteurPourEcart2.getYear();

        // on place le résultat dans le TextView
        OutilDate.ecartDate(jour1, mois1, an1, jour2, mois2, an2);
        resultatNombreJour.setText(
                getResources().getString(R.string.resultat_ecart));
    }

    /**
     * Méthode invoquée automatiquement lors d'un clic sur le bouton
     * de remise à zéro
     *
     * @param view source du clic
     */
    public void clicRaz(View view) {
        resultatNombreJour.setText("");
    }

    /**
     * Méthode invoquée automatiquement lors d'un clic sur le bouton
     * de retour vers l'activité principale
     *
     * @param view source du clic
     */
    public void clicRetour(View view) {
        String message = "Merci d'avoir utilisé le calcul d'écart !\n" +
                "Dernière calcul avec " + selecteurPourEcart1.toString() + " et " + selecteurPourEcart2.toString()
                + ". Le jour est un mercredi";
        Intent intentionRetour = new Intent();
        intentionRetour.putExtra(MainActivity.MSG_RETOUR, message);
        setResult(Activity.RESULT_OK, intentionRetour);
        finish();
    }

}
