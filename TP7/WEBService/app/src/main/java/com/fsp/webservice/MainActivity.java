/*
 *  Interrogation du Web service geo.api.gouv.fr
 *  Exemple d'utilisation d'un Web service en consultation              03/23
 */
package com.exercice.webservicegeovolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * TODO
 * @author 
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {


    /** URL pour consulter les informations relatives à un code postal */
    private static final String URL_COMMUNE = "https://geo.api.gouv.fr/communes?codePostal=%s";

    /** URL pour consulter les communes d'un département */
    private static final String URL_DEPARTEMENT = "https://geo.api.gouv.fr/departements/%s/communes";


    /** Zone de saisie du code postal */
    private EditText saisieCodePostal;

    /** Zone de saisie du département */
    private EditText saisieDepartement;

    /** Zone de saisie de la commune */
    private EditText saisieCommune;

    /** Zone pour afficher le résultat de la recherche (liste communes)*/
    private TextView zoneResultatCommune;

    /** Zone pour afficher le résultat de la recherche (code postal)*/
    private TextView zoneResultatCode;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // widgets recherche commune à partir du code postal
        saisieCodePostal =  findViewById(R.id.saisie_code_postal);
        zoneResultatCommune =  findViewById(R.id.zone_resultat);

        // widgets recherche code postal à partir de département et commune
        saisieDepartement = findViewById(R.id.saisie_departement);
        saisieCommune = findViewById(R.id.saisie_commune);
        zoneResultatCode = findViewById(R.id.zone_resultat_code);
    }



    /* ================================================================================
     *                     RECHERCHE COMMUNES A PARTIR DU CODE POSTAL
     * ================================================================================
     */

    /**
     * Efface toutes les zones de saisie et de résultat
     * @param view  Bouton à l'origine du clic
     */
    public void clicAnnuler(View view) {
        saisieCodePostal.setText("");
        zoneResultatCommune.setText("");
    }

    /**
     * Invoquée lors du clic sur le bouton rechercher
     * @param view  Bouton à l'origine du clic
     */
    public void clicRechercher(View view) {
        // TODO

    }

    /**
     * Gestion de la réponse à la requête de recherche de communes à partir d'un
     * code postal
     * Extraction des données de la réponse de la requête afin de les afficher
     * dans le TextView de résultat
     * @param reponse  réponse à la requête, sous la forme d'un JSONArray
     */
    public void setZoneResultatAvecObjetJson(JSONArray reponse){
        // TODO
    }




    /* ================================================================================
     *                     RECHERCHE CODE POSTAL A PARTIR DE COMMUNE
     * ================================================================================
     */

    /**
     * Efface toutes les zones de saisie et de résultat (recherche code postal)
     * @param view  Bouton à l'origine du clic
     */
    public void clicAnnulerCode(View view) {
        saisieDepartement.setText("");
        saisieCommune.setText("");
        zoneResultatCode.setText("");
    }


    /**
     * Invoquée lors du clic sur le bouton rechercher un code postal
     * @param view  Bouton à l'origine du clic
     */
    public void clicRechercherCode(View view) {
        // TODO

    }


    /**
     * Gestion de la réponse à la requête de recherche d'un code postal à partir d'un
     * département et d'une commune
     * Extraction des données de la réponse de la requête afin de les afficher
     * dans le TextView
     * @param reponse  réponse à la requête, sous la forme d'un JSONArray
     * @param communeCherchee nom de la commune dont le code postal est recherché
     */
    public void setZoneResultatCodeAvecObjetJson(JSONArray reponse, String communeCherchee){
        // TODO
    }



}