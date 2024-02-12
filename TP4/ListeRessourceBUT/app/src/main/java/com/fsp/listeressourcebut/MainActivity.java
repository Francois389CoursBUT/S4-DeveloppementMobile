package com.fsp.listeressourcebut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
    }

    public void clicTous(View view) {
        ArrayList<String> liste = new ArrayList<>();
        liste.addAll(Arrays.asList(modulesTroncCommun));
        liste.addAll(Arrays.asList(modulesAxeA));
        liste.addAll(Arrays.asList(modulesAxeB));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, liste);
        listeModule.setAdapter(adapter);
    }

    public void clicTroncCommun(View view) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, modulesTroncCommun);
        listeModule.setAdapter(adapter);
    }

    public void clicAxeA(View view) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, modulesAxeA);
        listeModule.setAdapter(adapter);
    }

    public void clicAxeB(View view) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, modulesAxeB);
        listeModule.setAdapter(adapter);
    }
}