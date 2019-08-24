package de.badresden.zasa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import de.badresden.zasa.Answer;
import de.badresden.zasa.R;
import de.badresden.zasa.StauanlageViewModel;

public class QuestionnaireTragfaehigkeitActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Fragebn_Tragfaehigkeit";
    private StauanlageViewModel stauanlageViewModel;
    private RadioGroup inputBoeschungsneigungVerhaeltnis;
    private RadioGroup inputStatischeBerechnungLiegtVor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_tragfaehigkeit);
        setTitle("Tragfähigkeit");

        inputBoeschungsneigungVerhaeltnis = findViewById(R.id.radio_boeschungsneigung_verhaeltnis);
        inputStatischeBerechnungLiegtVor = findViewById(R.id.radio_statische_berechnung_liegt_vor);

        stauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
    }

    public void openQuestionnaireGebrauchstauglichkeit(View view) {

        Answer boeschungsneigungVerhaeltnis = stauanlageViewModel.decideRadioAnswer(inputBoeschungsneigungVerhaeltnis.getCheckedRadioButtonId(),R.id.opt_yes_boeschungsneigung_verhaeltnis,
                R.id.opt_unknown_boeschungsneigung_verhaeltnis,R.id.opt_no_boeschungsneigung_verhaeltnis);
        Answer statischeBerechnungLiegtVor = stauanlageViewModel.decideRadioAnswer(inputStatischeBerechnungLiegtVor.getCheckedRadioButtonId(),R.id.opt_yes_statische_berechnung_liegt_vor,
                R.id.opt_unknown_statische_berechnung_liegt_vor,R.id.opt_no_statische_berechnung_liegt_vor);

        stauanlageViewModel.updateTragfaehigkeit(boeschungsneigungVerhaeltnis,statischeBerechnungLiegtVor);

        Intent openQuestionnaireGebrauchstauglichkeitIntent = new Intent(this, QuestionnaireGebrauchstauglichkeitActivtiy.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(openQuestionnaireGebrauchstauglichkeitIntent);
    }
}
