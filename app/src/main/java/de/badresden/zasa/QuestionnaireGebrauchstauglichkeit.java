package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class QuestionnaireGebrauchstauglichkeit extends AppCompatActivity {

    private static final String LOG_TAG = "F_Gebrauchstauglichkeit";
    private EditText inputQHWE1;
    private EditText inputQHWE2;
    private EditText inputFreibordUK;
    private EditText inputFreibordDammkrone;
    private RadioGroup inputNachweisDvwk;
    private EditText inputBetriebsauffaelligkeiten;
    private RadioGroup inputQuerschnittsreduktionHWE;
    private RadioGroup inputBeschaedigungenWasserwege;
    private RadioGroup inputBeschaedigungenTosbeckenHWE;
    private RadioGroup inputFreiUndFunktionstuechtig;
    private RadioGroup inputQuerschnittsreduktionGA;
    private RadioGroup inputBeschaedigungenGA;
    private RadioGroup inputBeschaedigungenTosbeckenGA;
    private RadioGroup inputSchwergaengigkeitVerschluss;
    private RadioGroup inputMesseinrichtungenFunktionsfaehig;
    private StauanlageViewModel stauanlageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_gebrauchstauglichkeit);
        setTitle("Gebrauchstauglichkeit");
        inputQHWE1 = findViewById(R.id.answer_qhwe1);
        inputQHWE2 = findViewById(R.id.answer_qhwe2);
        inputFreibordUK = findViewById(R.id.answer_freibord_uk); //TODO welche Namen fuer die Variablen sollen verwendet werden, vllt gleich Abkuerzugen von Felix in Stauanlagen uebernehmen ?
        inputFreibordDammkrone = findViewById(R.id.answer_freibord_dammkrone);
        inputNachweisDvwk = findViewById(R.id.radio_nachweis_dvwk);
        inputBetriebsauffaelligkeiten = findViewById(R.id.answer_betriebsauffaelligkeiten);
        inputQuerschnittsreduktionHWE = findViewById(R.id.radio_querschnittsreduktion_hwe);
        inputBeschaedigungenWasserwege = findViewById(R.id.radio_beschaedigungen_wasserwege);
        inputBeschaedigungenTosbeckenHWE = findViewById(R.id.radio_beschaedigungen_tosbecken_hwe);
        inputFreiUndFunktionstuechtig = findViewById(R.id.radio_frei_und_funktionstuechtig);
        inputQuerschnittsreduktionGA = findViewById(R.id.radio_querschnittsreduktion_ga);
        inputBeschaedigungenGA = findViewById(R.id.radio_beschaedigungen_ga);
        inputBeschaedigungenTosbeckenGA = findViewById(R.id.radio_beschaedigungen_tosbecken_ga);
        inputSchwergaengigkeitVerschluss = findViewById(R.id.radio_schwergaengigkeit_verschluss);
        inputMesseinrichtungenFunktionsfaehig = findViewById(R.id.radio_messeinrichtungen_funktionsfaehig);
        stauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
    }

    public void openQuestionnaireDauerhaftigkeit(View view) {
        Answer qHWWEVonBHW1GoesserGleichBHQ1 = stauanlageViewModel.decideQHEW1GreaterEqualBHQ1(Integer.valueOf(inputQHWE1.getText().toString()));
        Answer qHWEVonBHW2GroesserGleichBHQ2 = stauanlageViewModel.decideQHEW2GreaterEqualBHQ2(Integer.valueOf(inputQHWE2.getText().toString()));
        // Answer FreibordUK
        Answer nachweisDvwk = stauanlageViewModel.decideRadioAnswer(inputNachweisDvwk.getCheckedRadioButtonId(), R.id.opt_yes_nachweis_dvwk,
                R.id.opt_unknown_nachweis_dvwk, R.id.opt_no_nachweis_dvwk);
        Answer querschnittsreduktionHWE = stauanlageViewModel.decideRadioAnswer(inputQuerschnittsreduktionHWE.getCheckedRadioButtonId(),R.id.opt_yes_querschnittsreduktion_hwe,
                R.id.opt_unknown_querschnittsreduktion_hwe, R.id.opt_no_querschnittsreduktion_hwe);
        Answer beschaedigungenWasserwege = stauanlageViewModel.decideRadioAnswer(inputBeschaedigungenWasserwege.getCheckedRadioButtonId(),R.id.opt_yes_beschaedigungen_wasserwege,
                R.id.opt_unknown_beschaedigungen_wasserwege, R.id.opt_no_beschaedigungen_wasserwege);
        Answer beschaedigungenTosbeckenHWE = stauanlageViewModel.decideRadioAnswer(inputBeschaedigungenTosbeckenHWE.getCheckedRadioButtonId(),R.id.opt_yes_beschaedigungen_tosbecken_hwe,
                R.id.opt_unknown_beschaedigungen_tosbecken_hwe, R.id.opt_no_beschaedigungen_tosbecken_hwe);
        Answer freiUndFunktionstuechtig = stauanlageViewModel.decideRadioAnswer(inputFreiUndFunktionstuechtig.getCheckedRadioButtonId(),R.id.opt_yes_frei_und_funktionstuechtig,
                R.id.opt_unknown_frei_und_funktionstuechtig, R.id.opt_no_frei_und_funktionstuechtig);
        Answer querschnittsreduktionGA = stauanlageViewModel.decideRadioAnswer(inputQuerschnittsreduktionGA.getCheckedRadioButtonId(),R.id.opt_yes_querschnittsreduktion_ga,
                R.id.opt_unknown_querschnittsreduktion_ga, R.id.opt_no_querschnittsreduktion_ga);
        Answer beschaedigungenGA = stauanlageViewModel.decideRadioAnswer(inputBeschaedigungenGA.getCheckedRadioButtonId(),R.id.opt_yes_beschaedigungen_ga,
                R.id.opt_unknown_beschaedigungen_ga, R.id.opt_no_beschaedigungen_ga);
        Answer beschaedigungenTosbeckenGA = stauanlageViewModel.decideRadioAnswer(inputBeschaedigungenTosbeckenGA.getCheckedRadioButtonId(),R.id.opt_yes_beschaedigungen_tosbecken_ga,
                R.id.opt_unknown_beschaedigungen_tosbecken_ga, R.id.opt_no_beschaedigungen_tosbecken_ga);
        Answer schwergaengigkeiteVerchluss = stauanlageViewModel.decideRadioAnswer(inputSchwergaengigkeitVerschluss.getCheckedRadioButtonId(),R.id.opt_yes_schwergaengigkeit_verschluss,
                R.id.opt_unknown_schwergaengigkeit_verschluss, R.id.opt_no_schwergaengigkeit_verschluss);
        Answer messeinrichtungenfunktionsfaehig = stauanlageViewModel.decideRadioAnswer(inputMesseinrichtungenFunktionsfaehig.getCheckedRadioButtonId(), R.id.opt_yes_messeinrichtungen_funktionsfaehig,
                R.id.opt_unknown_messeinrichtungen_funktionsfaehig,R.id.opt_no_messeinrichtungen_funktionsfaehig);

        stauanlageViewModel.uptdateGebrauchstauglichkeit(
                qHWWEVonBHW1GoesserGleichBHQ1,
                qHWEVonBHW2GroesserGleichBHQ2,
                Answer.UNBEKANNT, //TODO siehe unten
                Answer.UNBEKANNT,//TODO ganz dickes TODO muss noch Logik implementieren, aber vorher halt verstehen !!!!!
                nachweisDvwk,
                inputBetriebsauffaelligkeiten.getText().toString(),
                querschnittsreduktionHWE,
                beschaedigungenWasserwege,
                beschaedigungenTosbeckenHWE,
                freiUndFunktionstuechtig,
                querschnittsreduktionGA,
                beschaedigungenGA,
                beschaedigungenTosbeckenGA,
                schwergaengigkeiteVerchluss,
                messeinrichtungenfunktionsfaehig
        );


        Intent openQuestionnaireDauerhaftigkeitIntent = new Intent(this, QuestionnaireDauerhaftigkeit.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(openQuestionnaireDauerhaftigkeitIntent);
    }


}
