package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.IntentService;
import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.util.Calendar;
import java.util.Date;

public class QuestionnaireAllgemein extends AppCompatActivity {

    private static final String LOG_TAG = QuestionnaireAllgemein.class.getSimpleName();

    private StauanlageViewModel mStauanlageViewModel;
    //bilden zusammen den Schlüssel für die Datenbank
    //sie werden also benötigt in der nächsten Activity das Stauanlagen Objekt zu laden
    private Date currentDate;
    private String nameDerAnlage;
    // relevante GUI-Elemente:
    private EditText inputNameDerAnlage;
    private EditText inputGeoLage;
    private EditText inputGewaesser;
    private EditText inputEigentuemer;
    private EditText inputArtDesAbsperrbauwerkes;
    private EditText inputHoehe;
    private EditText inputStauinhalt;
    private EditText inputbhq1;
    private EditText inputbhq2;
    private EditText inputbhq2Abschaetzung;
    private RadioGroup inputBetriebsvorschriftNormalbetrieb;
    private RadioGroup inputBetriebsvorschriftHochwasserfall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_allgemein);
        setTitle("Allgemein");

        inputNameDerAnlage = findViewById(R.id.answer_name_der_anlage);
        inputGeoLage = findViewById(R.id.answer_lage);
        inputGewaesser = findViewById(R.id.answer_gewaesser);
        inputEigentuemer = findViewById(R.id.answer_eigentuemer);
        inputArtDesAbsperrbauwerkes = findViewById(R.id.answer_art_des_absperrbauwerkes);
        inputHoehe = findViewById(R.id.answer_hoehe);
        inputStauinhalt = findViewById(R.id.answer_stauinhalt);
        inputbhq1 = findViewById(R.id.answer_bhq1);
        inputbhq2 = findViewById(R.id.answer_bhq2);
        inputbhq2Abschaetzung = findViewById(R.id.answer_bhq2_abschaetzung);
        inputBetriebsvorschriftNormalbetrieb = findViewById(R.id.radio_betriebsvorschrift_normalbetrieb);
        inputBetriebsvorschriftHochwasserfall = findViewById(R.id.radio_betriebsvorschtift_hochwasserfall);

        //setzen des ViewModels
        mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
        //das Bearbeitungsdatum setzten
        currentDate = Calendar.getInstance().getTime();
        //falls ein man aus vorheriger Activity kommt
        if (StauanlageViewModel.stauanlage != null){
            loadStauanlageInUI();
        }

    }


    private void loadStauanlageInUI() {
        inputNameDerAnlage.setText(StauanlageViewModel.stauanlage.nameDerAnlage);
        inputGeoLage.setText(StauanlageViewModel.stauanlage.geographischeLage);
        inputGewaesser.setText(StauanlageViewModel.stauanlage.gestautesGewaesser);
        inputEigentuemer.setText(StauanlageViewModel.stauanlage.eigentuemerBetreiber);
        inputArtDesAbsperrbauwerkes.setText(StauanlageViewModel.stauanlage.artDesAbsperrauwerkes);
        inputHoehe.setText(StauanlageViewModel.stauanlage.hoeheAbsperrwerkUeberGruendung);
        inputStauinhalt.setText(StauanlageViewModel.stauanlage.stauinhaltInCbm);
        inputbhq1.setText(StauanlageViewModel.stauanlage.bhq1InCbmProSekunde);
        inputbhq2.setText(StauanlageViewModel.stauanlage.bhq2InCbmProSekunde);
        //inputBetriebsvorschriftNormalbetrieb; FIXME: Methode um Answer wieder in RadioButton zu konvertieren
        //inputBetriebsvorschriftHochwasserfall;

    }

    @Override
    protected void onPause() {
        super.onPause();
        //löschen der bisher gespeichert Daten (beim pressen des zurueckButtons (FIXME eigtl. geht das vllt auch besser -> nicht in OnPause()  ))
        StauanlageViewModel.stauanlage = null; //FIXME is ganz großer quatsch  --> Bei Configuration Change werden Werte geloescht ... muss geaendert werden !!!
    }

    //TODO Welche Events werden getriggered beim drehen des Handys --> da müssen die Daten genauso wie beim weiter Button gesichert werden

    public void openQuestionnaireKlassifizierung(View view) {
        // Werte aus der GUI an Stauanlagen-Objekt an ViewModelKlasse übergeben.
        Answer BetriebsvorschriftNormalfall = mStauanlageViewModel.decideRadioAnswer(inputBetriebsvorschriftNormalbetrieb.getCheckedRadioButtonId(), R.id.opt_yes_betriebsvorschrift_normalbetrieb, R.id.opt_unknown_betriebsvorschrift_normalbetrieb,
                R.id.opt_no_betriebsvorschrift_normalbetrieb);
        Answer BetriebsvorschriftHochwasserfall = mStauanlageViewModel.decideRadioAnswer(inputBetriebsvorschriftHochwasserfall.getCheckedRadioButtonId(), R.id.opt_yes_betriebsvorschrift_hochwasserfall, R.id.opt_unknown_betriebsvorschrift_hochwasserfall,
                R.id.opt_no_betriebsvorschrift_hochwasserfall);


        mStauanlageViewModel.updateStauanlage(
                inputNameDerAnlage.getText().toString(),
                inputGeoLage.getText().toString(),
                inputGewaesser.getText().toString(),
                inputEigentuemer.getText().toString(),
                inputArtDesAbsperrbauwerkes.getText().toString(),
                Integer.valueOf(inputHoehe.getText().toString()), //TODO was passiert wenn Feld leer ist
                Integer.valueOf(inputStauinhalt.getText().toString()),
                Integer.valueOf(inputbhq1.getText().toString()),
                Integer.valueOf(inputbhq2.getText().toString()),
                BetriebsvorschriftNormalfall,
                BetriebsvorschriftHochwasserfall,
                currentDate);
        //mStauanlageViewModel.insert();
        nameDerAnlage = inputNameDerAnlage.getText().toString();
        //nächste Activity oeffnen
        Intent openQuestionnaireKlassifizierungIntent = new Intent(this, QuestionnaireTragfaehigkeit.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        //openQuestionnaireKlassifizierungIntent.putExtra(); // Optional parameters
        startActivity(openQuestionnaireKlassifizierungIntent);
    }
}