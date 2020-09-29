package de.badresden.zasa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import de.badresden.zasa.Answer;
import de.badresden.zasa.R;
import de.badresden.zasa.Stauanlage;
import de.badresden.zasa.StauanlageHolder;

import static de.badresden.zasa.functions.HelpFunctions.decideRadioAnswer;
import static de.badresden.zasa.functions.HelpFunctions.loadAnswerInRadioGroup;

//Autor: Georg

/**
 * Activity in der die Fragen der Kategorie Tragfähigkeit beanwortet werden
 */
public class QuestionnaireTragfaehigkeitActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Fragebn_Tragfaehigkeit";
    //private StauanlageViewModel stauanlageViewModel;
    //relevante GUI Elemente
    private RadioGroup inputStatischeBerechnungLiegtVor;


    private RadioButton inputStatischeBerechnungLiegtVor_JA;
    private RadioButton inputStatischeBerechnungLiegtVor_Nein;
    private RadioButton inputStatischeBerechnungLiegtVor_UNBEKANNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_tragfaehigkeit);
        setTitle("Tragfähigkeit");

        //setzten der GUI Elemente
        inputStatischeBerechnungLiegtVor = findViewById(R.id.radio_statische_berechnung_liegt_vor);

        setRadioButtons();
        if(StauanlageHolder.getStauanlage() != null){
            loadStauanlageInUI(StauanlageHolder.getStauanlage());
        }
    }

    private void loadStauanlageInUI(Stauanlage stauanlage) {
        loadAnswerInRadioGroup(stauanlage.statischeBerechnungLiegtVor,
                inputStatischeBerechnungLiegtVor_JA,
                inputStatischeBerechnungLiegtVor_Nein,
                inputStatischeBerechnungLiegtVor_UNBEKANNT);
    }

    private void setRadioButtons() {
        inputStatischeBerechnungLiegtVor_JA          = findViewById(R.id.opt_yes_statische_berechnung_liegt_vor);
        inputStatischeBerechnungLiegtVor_Nein        = findViewById(R.id.opt_no_statische_berechnung_liegt_vor);
        inputStatischeBerechnungLiegtVor_UNBEKANNT   = findViewById(R.id.opt_unknown_statische_berechnung_liegt_vor);
    }

    /**
     *Button "Weiter"
     * --> auslesen und zwischenspeichern der Daten
     * + wechseln zu Activity QuestionnaireGebrauchstauglichkeitActivtiy
     */
    public void openQuestionnaireGebrauchstauglichkeit(View view) {


        Answer statischeBerechnungLiegtVor = decideRadioAnswer(inputStatischeBerechnungLiegtVor.getCheckedRadioButtonId(), R.id.opt_yes_statische_berechnung_liegt_vor,
                R.id.opt_unknown_statische_berechnung_liegt_vor, R.id.opt_no_statische_berechnung_liegt_vor);

        StauanlageHolder.getStauanlage().updateTragfaehigkeit(statischeBerechnungLiegtVor);

        Intent openQuestionnaireGebrauchstauglichkeitIntent = new Intent(this, QuestionnaireGebrauchstauglichkeitActivtiy.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(openQuestionnaireGebrauchstauglichkeitIntent);
    }
}
