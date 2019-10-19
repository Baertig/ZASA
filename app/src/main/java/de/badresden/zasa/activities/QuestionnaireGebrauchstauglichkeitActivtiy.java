package de.badresden.zasa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import de.badresden.zasa.Answer;
import de.badresden.zasa.R;
import de.badresden.zasa.Stauanlage;
import de.badresden.zasa.StauanlageViewModel;

import static de.badresden.zasa.HelpFunctions.decideRadioAnswer;
import static de.badresden.zasa.HelpFunctions.decideQHEW1GreaterEqualBHQ1;
import static de.badresden.zasa.HelpFunctions.decideQHEW2GreaterEqualBHQ2;
import static de.badresden.zasa.HelpFunctions.safeParseStringToDouble;

//Autor: Georg

/**
 * Activity in der die Fragen der Kategorie Gebrauchstauglichkeit beanwortet werden
 */
@SuppressWarnings("unused")
public class QuestionnaireGebrauchstauglichkeitActivtiy extends AppCompatActivity {

    private static final String LOG_TAG = "F_Gebrauchstauglichkeit";
    //relevante GUI Elemente
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

    //Autor: Felix
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_gebrauchstauglichkeit);
        setTitle("Gebrauchstauglichkeit");
        //Autor: Georg
        //setzen der GUI Elemente
        inputQHWE1 = findViewById(R.id.answer_qhwe1);
        inputQHWE2 = findViewById(R.id.answer_qhwe2);
        inputFreibordUK = findViewById(R.id.answer_freibord_uk);
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

    /**
     * Button "Weiter"
     * --> auslesen und zwischenspeichern der Daten
     *  +  wechseln zu Activity QuestionnaireDauerhaftigkeitActivity
     */
    public void openQuestionnaireDauerhaftigkeit(View view) {
        //prüfen auf nicht zulässige Werte, daraufhin wird der entsprechende Wert gespeichert
        Double qHWE1 = safeParseStringToDouble(inputQHWE1.getText().toString());
        Double qHWE2 = safeParseStringToDouble(inputQHWE2.getText().toString());

        Answer qHWWEVonBHW1GoesserGleichBHQ1 = decideQHEW1GreaterEqualBHQ1(qHWE1, StauanlageViewModel.stauanlage);
        Answer qHWEVonBHW2GroesserGleichBHQ2 = decideQHEW2GreaterEqualBHQ2(qHWE2, StauanlageViewModel.stauanlage);

        // Answer FreibordUK
        // Answer FreibordDammkrone --> Logik um die Werte der Varibeln aus den eingegebenen Zahlen zu bestimmen wurde noch nicht implementiert
        Answer nachweisDvwk = decideRadioAnswer(inputNachweisDvwk.getCheckedRadioButtonId(), R.id.opt_yes_nachweis_dvwk,
                R.id.opt_unknown_nachweis_dvwk, R.id.opt_no_nachweis_dvwk);
        Answer querschnittsreduktionHWE = decideRadioAnswer(inputQuerschnittsreduktionHWE.getCheckedRadioButtonId(), R.id.opt_yes_querschnittsreduktion_hwe,
                R.id.opt_unknown_querschnittsreduktion_hwe, R.id.opt_no_querschnittsreduktion_hwe);
        Answer beschaedigungenWasserwege = decideRadioAnswer(inputBeschaedigungenWasserwege.getCheckedRadioButtonId(), R.id.opt_yes_beschaedigungen_wasserwege,
                R.id.opt_unknown_beschaedigungen_wasserwege, R.id.opt_no_beschaedigungen_wasserwege);
        Answer beschaedigungenTosbeckenHWE = decideRadioAnswer(inputBeschaedigungenTosbeckenHWE.getCheckedRadioButtonId(), R.id.opt_yes_beschaedigungen_tosbecken_hwe,
                R.id.opt_unknown_beschaedigungen_tosbecken_hwe, R.id.opt_no_beschaedigungen_tosbecken_hwe);
        Answer freiUndFunktionstuechtig = decideRadioAnswer(inputFreiUndFunktionstuechtig.getCheckedRadioButtonId(), R.id.opt_yes_frei_und_funktionstuechtig,
                R.id.opt_unknown_frei_und_funktionstuechtig, R.id.opt_no_frei_und_funktionstuechtig);
        Answer querschnittsreduktionGA = decideRadioAnswer(inputQuerschnittsreduktionGA.getCheckedRadioButtonId(), R.id.opt_yes_querschnittsreduktion_ga,
                R.id.opt_unknown_querschnittsreduktion_ga, R.id.opt_no_querschnittsreduktion_ga);
        Answer beschaedigungenGA = decideRadioAnswer(inputBeschaedigungenGA.getCheckedRadioButtonId(), R.id.opt_yes_beschaedigungen_ga,
                R.id.opt_unknown_beschaedigungen_ga, R.id.opt_no_beschaedigungen_ga);
        Answer beschaedigungenTosbeckenGA = decideRadioAnswer(inputBeschaedigungenTosbeckenGA.getCheckedRadioButtonId(), R.id.opt_yes_beschaedigungen_tosbecken_ga,
                R.id.opt_unknown_beschaedigungen_tosbecken_ga, R.id.opt_no_beschaedigungen_tosbecken_ga);
        Answer schwergaengigkeiteVerchluss = decideRadioAnswer(inputSchwergaengigkeitVerschluss.getCheckedRadioButtonId(), R.id.opt_yes_schwergaengigkeit_verschluss,
                R.id.opt_unknown_schwergaengigkeit_verschluss, R.id.opt_no_schwergaengigkeit_verschluss);
        Answer messeinrichtungenfunktionsfaehig = decideRadioAnswer(inputMesseinrichtungenFunktionsfaehig.getCheckedRadioButtonId(), R.id.opt_yes_messeinrichtungen_funktionsfaehig,
                R.id.opt_unknown_messeinrichtungen_funktionsfaehig, R.id.opt_no_messeinrichtungen_funktionsfaehig);

        stauanlageViewModel.uptdateGebrauchstauglichkeit(
                qHWWEVonBHW1GoesserGleichBHQ1,
                qHWEVonBHW2GroesserGleichBHQ2,
                Answer.UNBEKANNT, //FreibordUK
                Answer.UNBEKANNT,//FreibordDammkrone --> siehe oben (erstmal wird nur UNBEKANNT übergeben)
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
        //Autor: Felix
        Intent openQuestionnaireDauerhaftigkeitIntent = new Intent(this, QuestionnaireDauerhaftigkeitActivity.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(openQuestionnaireDauerhaftigkeitIntent);
    }


}
