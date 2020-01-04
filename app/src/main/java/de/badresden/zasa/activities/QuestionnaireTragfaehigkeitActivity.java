package de.badresden.zasa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import de.badresden.zasa.Answer;
import de.badresden.zasa.R;
import de.badresden.zasa.Stauanlage;
import de.badresden.zasa.StauanlageViewModel;

import static de.badresden.zasa.HelpFunctions.decideRadioAnswer;
import static de.badresden.zasa.HelpFunctions.loadAnswerInRadioGroup;

//Autor: Georg

/**
 * Activity in der die Fragen der Kategorie Tragfähigkeit beanwortet werden
 */
public class QuestionnaireTragfaehigkeitActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Fragebn_Tragfaehigkeit";
    private StauanlageViewModel stauanlageViewModel;
    //relevante GUI Elemente
    private RadioGroup inputBoeschungsneigungVerhaeltnis;
    private RadioGroup inputStatischeBerechnungLiegtVor;

    //RadioButtons
    private RadioButton inputBoeschungsneigungVerhaeltnis_JA;
    private RadioButton inputBoeschungsneigungVerhaeltnis_NEIN;
    private RadioButton inputBoeschungsneigungsVerhaeltnis_UNBEKANNT;
    private RadioButton inputStatischeBerechnungLiegtVor_JA;
    private RadioButton inputStatischeBerechnungLiegtVor_NEIN;
    private RadioButton inputStatischeBerechnungLiegtVor_UNBEKANNT;

    //Autor: Felix
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_tragfaehigkeit);
        setTitle("Tragfähigkeit");

        //Autor: Georg
        //setzten der GUI Elemente
        inputBoeschungsneigungVerhaeltnis = findViewById(R.id.radio_boeschungsneigung_verhaeltnis);
        inputStatischeBerechnungLiegtVor = findViewById(R.id.radio_statische_berechnung_liegt_vor);
        setRadioButtons();

        stauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);

        if(StauanlageViewModel.stauanlage != null){
            loadStauanlageinUI(StauanlageViewModel.stauanlage);
        }else{
            Log.e(LOG_TAG, "onCreate: Stauanlage Objekt in StauanlageViewModel ist nicht initialisiert");
        }
    }

    private void setRadioButtons() {
        inputBoeschungsneigungVerhaeltnis_JA = findViewById(R.id.opt_yes_boeschungsneigung_verhaeltnis);
        inputBoeschungsneigungVerhaeltnis_NEIN = findViewById(R.id.opt_no_boeschungsneigung_verhaeltnis);
        inputBoeschungsneigungsVerhaeltnis_UNBEKANNT = findViewById(R.id.opt_unknown_boeschungsneigung_verhaeltnis);
        inputStatischeBerechnungLiegtVor_JA = findViewById(R.id.opt_yes_statische_berechnung_liegt_vor);
        inputStatischeBerechnungLiegtVor_NEIN = findViewById(R.id.opt_no_statische_berechnung_liegt_vor);
        inputStatischeBerechnungLiegtVor_UNBEKANNT = findViewById(R.id.opt_unknown_statische_berechnung_liegt_vor);
    }

    private void loadStauanlageinUI(Stauanlage stauanlage) {
        loadAnswerInRadioGroup(stauanlage.wasserseitigZuLuftseitigKleinerEinszuDrei,
                inputBoeschungsneigungVerhaeltnis_JA,
                inputBoeschungsneigungVerhaeltnis_NEIN,
                inputBoeschungsneigungsVerhaeltnis_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.statischeBerechnungLiegtVor,
                inputStatischeBerechnungLiegtVor_JA,
                inputStatischeBerechnungLiegtVor_NEIN,
                inputStatischeBerechnungLiegtVor_UNBEKANNT);
    }

    /**
     *Button "Weiter"
     * --> auslesen und zwischenspeichern der Daten
     * + wechseln zu Activity QuestionnaireGebrauchstauglichkeitActivtiy
     */
    public void openQuestionnaireGebrauchstauglichkeit(View view) {

        Answer boeschungsneigungVerhaeltnis = decideRadioAnswer(inputBoeschungsneigungVerhaeltnis.getCheckedRadioButtonId(), R.id.opt_yes_boeschungsneigung_verhaeltnis,
                R.id.opt_unknown_boeschungsneigung_verhaeltnis, R.id.opt_no_boeschungsneigung_verhaeltnis);
        Answer statischeBerechnungLiegtVor = decideRadioAnswer(inputStatischeBerechnungLiegtVor.getCheckedRadioButtonId(), R.id.opt_yes_statische_berechnung_liegt_vor,
                R.id.opt_unknown_statische_berechnung_liegt_vor, R.id.opt_no_statische_berechnung_liegt_vor);

        stauanlageViewModel.updateTragfaehigkeit(boeschungsneigungVerhaeltnis, statischeBerechnungLiegtVor);

        Intent openQuestionnaireGebrauchstauglichkeitIntent = new Intent(this, QuestionnaireGebrauchstauglichkeitActivtiy.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(openQuestionnaireGebrauchstauglichkeitIntent);
    }
}
