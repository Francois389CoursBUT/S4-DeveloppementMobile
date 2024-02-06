package com.fsp.gestioninteractivite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText inputPrixInitial;

    private TextView inputTauxReduction;

    private TextView inputEconomieRealisee;

    private TextView inputPrixAPayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputPrixInitial = findViewById(R.id.prix_init);
        inputTauxReduction = findViewById(R.id.taux_reduc);
        inputEconomieRealisee = findViewById(R.id.economie);
        inputPrixAPayer = findViewById(R.id.a_payer);
    }

    public void calculer(View view) {
        double prixInitial = Double.parseDouble(inputPrixInitial.getText().toString());
        double tauxReduction = Double.parseDouble(inputTauxReduction.getText().toString());

        if (100 <= tauxReduction && 0 < prixInitial && 0 < tauxReduction) {
            double economieRealisee = prixInitial * tauxReduction / 100;
            double prixAPayer = prixInitial - economieRealisee;

            inputEconomieRealisee.setText(String.valueOf(economieRealisee));
            inputPrixAPayer.setText(String.valueOf(prixAPayer));
        } else {
            //Afficher un toast pour afficher un message d'erreur

            Toast.makeText(this, "Le taux de réduction doit être supérieur ou égal à 100", Toast.LENGTH_LONG).show();

        }
    }

    public void annuler(View view) {
        inputPrixInitial.setText("");
        inputTauxReduction.setText("");
        inputEconomieRealisee.setText("");
        inputPrixAPayer.setText("");
    }


}