/*  Activité principale de l'application qui effectue des calculs sur les dates
    MainActivity.java                                               08/23
 */
package com.fsp.multiactivite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Classe activité principale de l'application qui effectue des calculs sur les
 * dates. La vue contient 2 boutons permettant de lancer des activités filles :
 *   - EcartDate  : nombre de jours qui séparent 2 dates
 *   - JourSemaine : jour de la semaine pour une date donnée
 * Les activités filles sont dotées d'un bouton de retour permettant de revenir
 * sur l'activité principale
 * @author  Servières
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> activiteEcartDate;
    private ActivityResultLauncher<Intent> activiteJourSemaine;

    public static String MSG_RETOUR = "MESSAGE_RETOUR";


    /** Zone de texte qui affiche le message de retour,
     *  après retour de l'activité fille
     */
    private TextView messageRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageRetour = findViewById(R.id.retour_activite);
        activiteJourSemaine = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::retourJourSemaine);
        activiteEcartDate = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::retourEcartDate);
    }

    private void retourEcartDate(ActivityResult resultat) {
        Intent intent = resultat.getData();

        if (resultat.getResultCode() == Activity.RESULT_OK) {
            String message = intent.getStringExtra(MSG_RETOUR);
            messageRetour.setText(message);
        }
    }

    private void retourJourSemaine(ActivityResult activityResult) {
        String message = "Merci dhvoir utilisé le calcul d'écart !\n" +
                "Dernier calcul avec 4/10/2023 et 6/10/2023.\n" +
                "L' écart est de 2 iour(s)";
        messageRetour.setText(message);
    }

    /**
     * Méthode appelée lors du clic sur le bouton de recherche du jour
     * de la semaine
     * @param bouton sur lequel l'utilisateur a cliqué
     */
    public void clicJourSemaine(View bouton) {
        Intent intention = new Intent(this, ActiviteJourSemaine.class);
        activiteJourSemaine.launch(intention);
    }

    /**
     * Méthode appelée lors du clic sur le bouton de calcul
     * de l'écart entre 2 dates
     * @param bouton sur lequel l'utilisateur a cliqué
     */
    public void clicEcartDate(View bouton) {
        Intent intention = new Intent(this, ActiviteEcartDate.class);
        activiteEcartDate.launch(intention);
    }

    

}
