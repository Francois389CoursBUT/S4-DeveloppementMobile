/*
 * TP multiactivités : jeu mémorisation des mots => activité principale
 * MainActivity.java                    07/16
 */
package com.fsp.tp_jeumemoire;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Activité principale de l'application jeu de mémorisation des mots.
 * La vue permet à l'utilisateur de demander 5 mots choisis de manière aléatoire.
 * En cliquant sur le bouton "Jouer", il lance une deuxième activité dans laquelle
 * il devra retrouver les mots mémorisés.
 * @author Servières
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    public static final String SCORE = "com.fsp.tp_jeumemoire.SCORE";
    private static final String TAG = "François";

    /** Nombre de mots à mémoriser */
    public static final int NB_MOT = 5;

    /** Clé pour la liste des mots corrects transmis à l'activité fille "propositions" */
    public static final String CLE_MOT_CORRECT = "memorisemot.MOT_CORRECT";

    /** Tableau contenant les références sur les zones d'affichage
     *  des mots
     */
    private TextView[] tableTexteMot;

    /** Base de tous les mots possibles dans laquelle les 5 mots sont
     *  choisis au hasard*/
    private BaseDeMot base;

    /** Liste des mots choisis au hasard, proposés à l'utilisateur, et à mémoriser */
    private ArrayList<String> listeMotTire;

    /** Vrai ssi l'utilisateur a cliqué sur le bouton de sélection des mots */
    private boolean clicSur5Mots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clicSur5Mots = false;

        // on accède aux zones de texte destinées aux mots
        tableTexteMot = new TextView[NB_MOT];
        tableTexteMot[0] =  findViewById(R.id.mot1);
        tableTexteMot[1] =  findViewById(R.id.mot2);
        tableTexteMot[2] =  findViewById(R.id.mot3);
        tableTexteMot[3] =  findViewById(R.id.mot4);
        tableTexteMot[4] =  findViewById(R.id.mot5);

        // initialisation et création de la base de mots
        base = new BaseDeMot();
    }

    /**
     * Méthode exécutée lorsque l'utilisateur clique sur le bouton de tirage
     * au sort des 5 mots
     * @param bouton  bouton sur lequel le clic a été effectué
     */
    public void clic5mots(View bouton) {

        // tirage aléatoire de 5 mots
        ArrayList<String> listeMotTire = base.tirageAleatoire();

        // on place les 5 mots dans les TextView
		for (int i = 0; i < NB_MOT; i++) {
            tableTexteMot[i].setText(listeMotTire.get(i));
        }

        clicSur5Mots = true;
    }

    /**
     * Méthode exécutée lorsque l'utilisateur clique sur le bouton de
     * réinitialiation des mots proposés (réinitialisés à vide)
     * @param bouton  bouton sur lequel le clic a été effectué
     */
    public void clicRaz(View bouton) {
        for (int i = 0; i < NB_MOT; i++) {
            tableTexteMot[i].setText("");
        }
    }


    /**
     * Méthode exécutée lorsque l'utilisateur clique sur le bouton
     * pour jouer. Lancement de l'activité propositions
     * @param bouton  bouton sur lequel le clic a été effectué
     */
    public void clicJouer(View bouton) {
        if (!clicSur5Mots) {

            // l'utilisateur n'a pas lancé le tirage des 5 mots
            Toast pasDeMot = new Toast(this);
            pasDeMot.setText("Il faut générer des mots pour pouvoir jouer !");
            pasDeMot.show();
			
        } else {
            clicSur5Mots = false;

            /* Création d'une intention qui sera envoyée à l'activité "proposition"
             * accompagnée de la liste contenant les 5 mots proposés à l'utilisateur
             */
            // REMARQUE : on peut donner en argument de putExtra, une ArrayList
            // TODO
            ArrayList<String> motTire = new ArrayList<>();
            for (int i = 0; i < tableTexteMot.length; i++) {
                motTire.add((String) tableTexteMot[i].getText());
            }
            Log.d(TAG, "clicJouer: " + motTire);
            Intent versProposition = new Intent(MainActivity.this, PropositionActivity.class);
            versProposition.putExtra(PropositionActivity.CLE_MOT_TIRER, motTire);

            clicRaz(bouton);    // on vide les mots
            startActivity(versProposition);
        }
    }

}
