package de.badresden.zasa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import de.badresden.zasa.Answer;
import de.badresden.zasa.R;
import de.badresden.zasa.StauanlageViewModel;

//Autor: Georg

/**
 * Activity in der die Fragen der Kategorie Allgemein beantwortet werden
 */

@SuppressWarnings("unused")
public class QuestionnaireAllgemeinActivity extends AppCompatActivity {

    private static final String LOG_TAG = QuestionnaireAllgemeinActivity.class.getSimpleName();

    private StauanlageViewModel mStauanlageViewModel;
    private Date currentDate;//Bearbeitungsdatum
    // relevante GUI-Elemente:
    private EditText inputNameDerAnlage;
    private EditText inputGeoLage;
    private EditText inputGewaesser;
    private EditText inputEigentuemer;
    private EditText inputArtDesAbsperrbauwerkes;
    private EditText inputHoehe;
    private EditText inputStauinhalt;
    private EditText inputBHQ1;
    private EditText inputBHQ2;
    private EditText inputBHQ2Abschaetzung; // siehe Beschreibunt in der GUI (wird noch nicht ausgewertet )
    private RadioGroup inputBetriebsvorschriftNormalbetrieb;
    private RadioGroup inputBetriebsvorschriftHochwasserfall;

    //Autor: Felix
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_allgemein);
        setTitle("Allgemein");
        //Autor: Georg
        //setzen der relevanten Gui Elemente
        inputNameDerAnlage = findViewById(R.id.answer_name_der_anlage);
        inputGeoLage = findViewById(R.id.answer_lage);
        inputGewaesser = findViewById(R.id.answer_gewaesser);
        inputEigentuemer = findViewById(R.id.answer_eigentuemer);
        inputArtDesAbsperrbauwerkes = findViewById(R.id.answer_art_des_absperrbauwerkes);
        inputHoehe = findViewById(R.id.answer_hoehe);
        inputStauinhalt = findViewById(R.id.answer_stauinhalt);
        inputBHQ1 = findViewById(R.id.answer_bhq1);
        inputBHQ2 = findViewById(R.id.answer_bhq2);
        inputBHQ2Abschaetzung = findViewById(R.id.answer_bhq2_abschaetzung);
        inputBetriebsvorschriftNormalbetrieb = findViewById(R.id.radio_betriebsvorschrift_normalbetrieb);
        inputBetriebsvorschriftHochwasserfall = findViewById(R.id.radio_betriebsvorschtift_hochwasserfall);
        inputBHQ2Abschaetzung = findViewById(R.id.answer_bhq2_abschaetzung);
        //setzen des ViewModels
        mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
        //das Bearbeitungsdatum setzten
        currentDate = Calendar.getInstance().getTime();
        //wenn man aus einer vorherigen Activity kommt sollen die Daten der letzen Bearbeitung geladen werden
        //kommt man von der MainActivity ist die variable null, da sie dort in der OnCreate Methode null gesetzt wird
        if (StauanlageViewModel.stauanlage != null) {
            loadStauanlageInUI();
        }
    }


    private void loadStauanlageInUI() {
        inputNameDerAnlage.setText(StauanlageViewModel.stauanlage.nameDerAnlage);
        inputGeoLage.setText(StauanlageViewModel.stauanlage.geographischeLage);
        inputGewaesser.setText(StauanlageViewModel.stauanlage.gestautesGewaesser);
        inputEigentuemer.setText(StauanlageViewModel.stauanlage.eigentuemerBetreiber);
        inputArtDesAbsperrbauwerkes.setText(StauanlageViewModel.stauanlage.artDesAbsperrauwerkes);
        inputHoehe.setText(String.valueOf(StauanlageViewModel.stauanlage.hoeheAbsperrwerkUeberGruendung));
        inputStauinhalt.setText(String.valueOf(StauanlageViewModel.stauanlage.stauinhaltInCbm));
        inputBHQ1.setText(String.valueOf(StauanlageViewModel.stauanlage.bHQ1InCbmProSekunde));
        inputBHQ2.setText(String.valueOf(StauanlageViewModel.stauanlage.bHQ2InCbmProSekunde));
        //Wir haben noch keine Methode implementiert um Daten vom Typ Answer in die Activity zu laden,
        //also die Radiobuttons vorzubelegen
        //inputBetriebsvorschriftNormalbetrieb;
        //inputBetriebsvorschriftHochwasserfall;

    }

    /**
     * Button "Weiter"
     * --> auslesen und zwischenspeichern der Daten
     * --> Wechseln zu Activity QuestionnaireTragfaehigkeitActivity
     */
    public void openQuestionnaireKlassifizierung(View view) {
        // Werte aus der GUI an Stauanlagen-Objekt an ViewModelKlasse übergeben.
        Answer BetriebsvorschriftNormalfall = mStauanlageViewModel.decideRadioAnswer(inputBetriebsvorschriftNormalbetrieb.getCheckedRadioButtonId(), R.id.opt_yes_betriebsvorschrift_normalbetrieb, R.id.opt_unknown_betriebsvorschrift_normalbetrieb,
                R.id.opt_no_betriebsvorschrift_normalbetrieb);
        Answer BetriebsvorschriftHochwasserfall = mStauanlageViewModel.decideRadioAnswer(inputBetriebsvorschriftHochwasserfall.getCheckedRadioButtonId(), R.id.opt_yes_betriebsvorschrift_hochwasserfall, R.id.opt_unknown_betriebsvorschrift_hochwasserfall,
                R.id.opt_no_betriebsvorschrift_hochwasserfall);
        //es wird auf nicht zulässige Werte im Feld geprüft dementsprechend wird die Variable gesetzt
        Double hoehe = (inputHoehe.getText().toString().equals("") || inputHoehe.getText().toString().equals(".")) ? Double.NaN : Double.valueOf(inputHoehe.getText().toString());
        Double Stauinhalt = (inputStauinhalt.getText().toString().equals("") || inputStauinhalt.getText().toString().equals(".")) ? Double.NaN : Double.valueOf(inputStauinhalt.getText().toString());
        Double bHQ1 = (inputBHQ1.getText().toString().equals("") || inputBHQ1.getText().toString().equals(".")) ? Double.NaN : Double.valueOf(inputStauinhalt.getText().toString());
        Double bHQ2 = (inputBHQ2.getText().toString().equals("") || inputBHQ2.getText().toString().equals(".")) ? Double.NaN : Double.valueOf(inputStauinhalt.getText().toString());

        mStauanlageViewModel.createStauanlage(); //
        mStauanlageViewModel.updateAllgemein(
                inputNameDerAnlage.getText().toString(),
                inputGeoLage.getText().toString(),
                inputGewaesser.getText().toString(),
                inputEigentuemer.getText().toString(),
                inputArtDesAbsperrbauwerkes.getText().toString(),
                hoehe,
                Stauinhalt,
                bHQ1,
                bHQ2,
                BetriebsvorschriftNormalfall,
                BetriebsvorschriftHochwasserfall,
                currentDate);
        //Autor: Felix
        //nächste Activity oeffnen
        Intent openQuestionnaireKlassifizierungIntent = new Intent(this, QuestionnaireTragfaehigkeitActivity.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(openQuestionnaireKlassifizierungIntent);
    }

    /**
     * Button "Standort"
     * --> noch keine Funktion hinterlegt
     * (Soll später GPS - Koordinaten des Handys liefern)
     */
    public void chooseLocationByGps(View view) { //btn_location
        Toast toast = Toast.makeText(this, "Button ist noch nicht mit Funktionn hinterlegt", Toast.LENGTH_LONG);
        toast.show();
    }
}