package com.fsp.utilisationmenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView listeModule;

    private String[] modulesTroncCommun;
    private String[] modulesAxeA;
    private String[] modulesAxeB;
    private int[] coefTroncCommun;
    private int[] coefAxeA;
    private int[] coefAxeB;

    ArrayAdapter<String> adaptateur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listeModule = findViewById(R.id.liste_module);

        modulesTroncCommun =  getResources().getStringArray(R.array.tronc_commun);
        modulesAxeA =  getResources().getStringArray(R.array.parcours_A);
        modulesAxeB =  getResources().getStringArray(R.array.parcours_B);
        coefTroncCommun =  getResources().getIntArray(R.array.coef_tronc_commun);
        coefAxeA =  getResources().getIntArray(R.array.coef_parcours_A);
        coefAxeB =  getResources().getIntArray(R.array.coef_parcours_B);

        adaptateur = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        registerForContextMenu(listeModule);
    }

    public void clicTous(View view) {
        ArrayList<String> liste = new ArrayList<>();
        liste.addAll(Arrays.asList(modulesTroncCommun));
        liste.addAll(Arrays.asList(modulesAxeA));
        liste.addAll(Arrays.asList(modulesAxeB));


        adaptateur.clear();
        adaptateur.addAll(liste);

        listeModule.setAdapter(adaptateur);
        //listeModule.setOnItemClickListener(this);
    }

    public void clicTroncCommun(View view) {
        adaptateur.clear();
        adaptateur.addAll(Arrays.asList(modulesTroncCommun));

        listeModule.setAdapter(adaptateur);
        //listeModule.setOnItemClickListener(this);
    }

    public void clicAxeA(View view) {
        adaptateur.clear();
        adaptateur.addAll(Arrays.asList(modulesAxeA));

        listeModule.setAdapter(adaptateur);
        //listeModule.setOnItemClickListener(this);
    }

    public void clicAxeB(View view) {
        adaptateur.clear();
        adaptateur.addAll(Arrays.asList(modulesAxeB));

        listeModule.setAdapter(adaptateur);
        //listeModule.setOnItemClickListener(this);
    }

/*
    @SuppressLint("DefaultLocale")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String module = (String) parent.getItemAtPosition(position);
        int indexModule = Arrays.asList(modulesTroncCommun).indexOf(module);
        int coefficient = 0;
        String message = getString(R.string.message_toast_coef);

        if (Arrays.asList(modulesTroncCommun).contains(module)) {
            coefficient = coefTroncCommun[indexModule];
        } else if (Arrays.asList(modulesAxeA).contains(module)) {
            coefficient = coefAxeA[indexModule];
        } else if (Arrays.asList(modulesAxeB).contains(module)) {
            coefficient = coefAxeB[indexModule];
        }

        // On rétablie le coefficient à sa valeur correcte
        double coefficientReel = coefficient / 10.0;

        AlertDialog.Builder boiteDeDialogue = new AlertDialog.Builder(this);
        boiteDeDialogue.setTitle("Ressource sélectionnée");
        boiteDeDialogue.setMessage(String.format(message, module, coefficientReel));
        boiteDeDialogue.setPositiveButton("OK", null);
        boiteDeDialogue.show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_general, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idItemSelectionne = item.getItemId();

        if (idItemSelectionne == R.id.option_afficher_tous) {
            clicTous(null);
        } else if (idItemSelectionne == R.id.option_afficher_commun) {
            clicTroncCommun(null);
        } else if (idItemSelectionne == R.id.option_afficher_axeA) {
            clicAxeA(null);
        } else if (idItemSelectionne == R.id.option_afficher_axeB) {
            clicAxeB(null);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        new MenuInflater(this).inflate(R.menu.menu_contextuel, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo information =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int idItemSelectionne = item.getItemId();


        if (idItemSelectionne == R.id.action_voir_axe) {
            System.out.println("Voir l'axe");
            afficherDetail(information.position);
        } else if (idItemSelectionne == R.id.action_voir_coef) {
            System.out.println("Voir le coefficient");
            afficherDetail(information.position);
        } else if (idItemSelectionne == R.id.action_annuler) {
            System.out.println("Annuler");
        }

        return super.onContextItemSelected(item);
    }

    private void afficherDetail(int positionListe) {
        String module = adaptateur.getItem(positionListe);
        int indexModule = Arrays.asList(modulesTroncCommun).indexOf(module);
        int coefficient = 0;
        String message = getString(R.string.message_toast_coef);

        if (Arrays.asList(modulesTroncCommun).contains(module)) {
            coefficient = coefTroncCommun[indexModule];
        } else if (Arrays.asList(modulesAxeA).contains(module)) {
            coefficient = coefAxeA[indexModule];
        } else if (Arrays.asList(modulesAxeB).contains(module)) {
            coefficient = coefAxeB[indexModule];
        }

        // On rétablie le coefficient à sa valeur correcte
        double coefficientReel = coefficient / 10.0;

        AlertDialog.Builder boiteDeDialogue = new AlertDialog.Builder(this);
        boiteDeDialogue.setTitle("Ressource sélectionnée");
        boiteDeDialogue.setMessage(String.format(message, module, coefficientReel));
        boiteDeDialogue.setPositiveButton("OK", null);
        boiteDeDialogue.show();
    }
}