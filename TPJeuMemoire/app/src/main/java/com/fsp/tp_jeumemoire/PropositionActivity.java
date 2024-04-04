package com.fsp.tp_jeumemoire;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PropositionActivity extends AppCompatActivity {

    /**
     * Clé pour le tableau des mots proposés par l'utilisteur
     */
    public static final String CLE_MOT_TIRER
            = "com.fsp.tp_jeumemoire.MOT_TIRER";

    public static final String CLE_MOT_UTILISATEUR
            = "com.fsp.tp_jeumemoire.MOT_UTILISATEUR";

    /**
     * Identifiants des widgets  EditText
     */
    private static final int[] IDENT_EDIT_TEXT = {R.id.saisie_mot1, R.id.saisie_mot2,
            R.id.saisie_mot3, R.id.saisie_mot4, R.id.saisie_mot5};


    /**
     * Zone de saisie pour les mots proposés par l'utilisateur
     */
    private EditText[] tableZoneSaisie;

    /**
     * Liste des mots corrects
     */
    private ArrayList<String> listeCorrecte;

    /**
     * Liste des mots proposés par l'utilisateur
     */
    private ArrayList<String> reponseUtilisateur;
    private static final String TAG = "François";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_proposition);

        getReponse();

        Intent intention = getIntent();
        listeCorrecte = intention.getStringArrayListExtra(CLE_MOT_TIRER);
        reponseUtilisateur = intention.getStringArrayListExtra(CLE_MOT_UTILISATEUR);

        Log.d(TAG, "onCreate: " + listeCorrecte);
        Log.d(TAG, "onCreate: " + reponseUtilisateur);

        if (reponseUtilisateur == null) {
            reponseUtilisateur = new ArrayList<>();
        } else {
            // on remplit les zones de saisie avec les mots déjà proposés
            for (int i = 0; i < reponseUtilisateur.size(); i++) {
                tableZoneSaisie[i].setText(reponseUtilisateur.get(i));
            }
        }

    }

    public void getReponse() {
        // on accède aux zones de saisie des mots
        tableZoneSaisie = new EditText[MainActivity.NB_MOT];
        for (int i = 0; i < IDENT_EDIT_TEXT.length; i++) {
            tableZoneSaisie[i] = findViewById(IDENT_EDIT_TEXT[i]);
        }
    }

    private ArrayList<String> getStringSaisie() {
        ArrayList<String> reponse = new ArrayList();
        for (EditText editText : tableZoneSaisie) {
            reponse.add(editText.getText().toString());
        }

        return reponse;
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton "Soumettre"
     *
     * @param bouton bouton sur lequel l'utilisateur a cliqué
     */
    public void clicSoumettre(View bouton) {

        // on constitue la liste des mots saisis par l'utilistateur
        reponseUtilisateur = getStringSaisie();


        // on compare la proposition de l'utilisateur et la réponse correcte
        // TODO
        boolean toutCorrect = true;
        for (String saisie : reponseUtilisateur) {
            Log.d(TAG, saisie);
            toutCorrect &= listeCorrecte.contains(saisie);
        }
        Log.d(TAG, "clicSoumettre: " + listeCorrecte);
        Log.d(TAG, "clicSoumettre: " + toutCorrect);

        /* Création d'une intention qui sera envoyée à l'activité "resultat"
         * accompagnée d'un tableau contenant les 5 mots corrects et les 5 mots
         * proposés par l'utilisateur
         */
        Intent versResultat = new Intent(PropositionActivity.this, ResultatActivity.class);
        versResultat.putStringArrayListExtra(ResultatActivity.CLE_MOT_CORRECT, listeCorrecte);
        versResultat.putStringArrayListExtra(ResultatActivity.CLE_MOT_UTILISATEUR, reponseUtilisateur);
        startActivity(versResultat);
        finish();
    }
}
