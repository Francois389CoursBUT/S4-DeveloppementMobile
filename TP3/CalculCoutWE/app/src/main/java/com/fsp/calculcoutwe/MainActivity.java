package com.fsp.calculcoutwe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int RADIO_BUTTON_2_JOURS = R.id.radioButton2Jours;
    public static final int RADIO_BUTTON_3_JOURS = R.id.radioButton3Jours;
    public static final int RADIO_BUTTON_4_JOURS = R.id.radioButton4Jours;
    public static final int RADIO_BUTTON_5_JOURS = R.id.radioButton5Jours;
    private RadioGroup radioGroupNbJours;

    private EditText editTextBudgetTransport;

    private EditText editTextBudgetParNuit;

    private EditText editTextBudgetParRepas;

    private CheckBox checkBoxInclure2RepasDernierJour;

    private EditText editTextBudgetVisites;

    private EditText textViewEstimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupNbJours = findViewById(R.id.radioGroupNbJours);
        editTextBudgetTransport = findViewById(R.id.editTextBudgetTransport);
        editTextBudgetParNuit = findViewById(R.id.editTextBudgetParNuit);
        editTextBudgetParRepas = findViewById(R.id.editTextBudgetParRepas);
        checkBoxInclure2RepasDernierJour = findViewById(R.id.checkBoxInclure2RepasDernierJour);
        editTextBudgetVisites = findViewById(R.id.editTextBudgetVisites);
        textViewEstimation = findViewById(R.id.textViewEstimation);

    }

    /**
     * Méthode appelée lors du clic sur le bouton "Annuler"
     * Elle permet de réinitialiser les champs du formulaire
     *
     * @param view
     */
    public void annuler(View view) {
        radioGroupNbJours.clearCheck();
        radioGroupNbJours.check(R.id.radioButton2Jours);

        editTextBudgetTransport.setText("");
        editTextBudgetParNuit.setText("");
        editTextBudgetParRepas.setText("");

        checkBoxInclure2RepasDernierJour.setChecked(false);
        editTextBudgetVisites.setText("");
        textViewEstimation.setText("");
    }

    /**
     * Revoie la valeur d'un champ de type EditText
     * Si le champ est vide, la méthode affiche renvoie 0
     * Si le champ contient autre chose qu'un nombre, la méthode renvoie une NumberFormatException
     *
     * @param editText
     * @return
     */
    private double getValue(EditText editText, String nomChamp) throws NumberFormatException {
        String value = editText.getText().toString();
        double result;
        if (value.isEmpty()) {
            result = 0;
        } else {
            try {
                result = Double.parseDouble(value);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Le champ " + nomChamp + " doit contenir un nombre");
            }
        }
        return result;
    }

    /**
     * Méthode appelée lors du clic sur le bouton "Calculer"
     * Elle permet de calculer le budget total du voyage
     *
     * @param view
     */
    public void calculer(View view) {
        try {
            double budgetTransport = getValue(editTextBudgetTransport, "Budget transport");
            double budgetParNuit = getValue(editTextBudgetParNuit, "Budget par nuit");
            double budgetParRepas = getValue(editTextBudgetParRepas, "Budget par repas");
            double budgetVisites = getValue(editTextBudgetVisites, "Budget visites");

            int nbJours = getNbJours();

            double budgetTotal = budgetTransport + ((nbJours - 1) * budgetParNuit)
                    + (nbJours * 2 * budgetParRepas) + (budgetVisites * nbJours);

            // Si l'utilisateur ne souhaite pas inclure le deuxième repas du dernier jour
            // on réduit le budget total de un repas
            if (!checkBoxInclure2RepasDernierJour.isChecked()) {
                budgetTotal -= budgetParRepas;
            }
            textViewEstimation.setText(String.valueOf(budgetTotal));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private int getNbJours() {
        int nbJours = 0;
        int checkedRadioButtonId = radioGroupNbJours.getCheckedRadioButtonId();

        if (checkedRadioButtonId == RADIO_BUTTON_2_JOURS) {
            nbJours = 2;
        } else if (checkedRadioButtonId == RADIO_BUTTON_3_JOURS) {
            nbJours = 3;
        } else if (checkedRadioButtonId == RADIO_BUTTON_4_JOURS) {
            nbJours = 4;
        } else if (checkedRadioButtonId == RADIO_BUTTON_5_JOURS) {
            nbJours = 5;
        }

        return nbJours;
    }
}