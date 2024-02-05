package com.fsp.gestioninteractivite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fsp.gestioninteractivite.OutilAleatoire;

public class MainActivity extends AppCompatActivity {

    TextView txtResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResultat = findViewById(R.id.txtResultat);
    }

    public void onClickPileFace(View bouton) {
        txtResultat.setText("Le tirage à donné " + OutilAleatoire.tiragePileFace());
    }

    public void onClickDe6(View bouton) {
        txtResultat.setText("Le tirage à donné : " + OutilAleatoire.tirageAleatoire(6));
    }

    public void onClickTirageLoto(View bouton) {
        txtResultat.setText("Valeur conseillée pour le loto : " + OutilAleatoire.tirageAleatoire(49));
    }
}