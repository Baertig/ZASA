package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.IntentService;
import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

public class FragebogenAllgemein extends AppCompatActivity {

    private static final String LOG_TAG = FragebogenAllgemein.class.getSimpleName();

    private StauanlageViewModel mStauanlageViewModel;

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
        setContentView(R.layout.fragebogen_allgemein);
        setTitle("Allgemein");

        inputNameDerAnlage = findViewById(R.id.antw_name_der_anlage);
        inputGeoLage = findViewById(R.id.antw_lage);
        inputGewaesser = findViewById(R.id.antw_gewaesser);
        inputEigentuemer = findViewById(R.id.antw_eigentuemer);
        inputArtDesAbsperrbauwerkes = findViewById(R.id.antw_art_des_absperrbauwerkes);
        inputHoehe = findViewById(R.id.antw_hoehe);
        inputStauinhalt = findViewById(R.id.antw_stauinhalt);
        inputbhq1 = findViewById(R.id.antw_bhq1);
        inputbhq2 = findViewById(R.id.antw_bhq2);
        inputbhq2Abschaetzung = findViewById(R.id.antw_bhq2_abschaetzung);
        inputBetriebsvorschriftNormalbetrieb = findViewById(R.id.radio_betriebsvorschrift_normalbetrieb);
        inputBetriebsvorschriftHochwasserfall = findViewById(R.id.radio_betriebsvorschtift_hochwasserfall);


        Log.d(LOG_TAG, "Loading input data...");
        // TODO for-loop über EditText Array?
        if (DataHandler.answerStorage.containsKey("name_der_anlage")) {
            inputNameDerAnlage.setText(DataHandler.answerStorage.get("name_der_anlage").toString());
        }
        if (DataHandler.answerStorage.containsKey("name_der_anlage")) {
            inputNameDerAnlage.setText(DataHandler.answerStorage.get("name_der_anlage").toString());
        }
        //setzen des ViewModels
        mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "Saving input data...");
        // TODO Loop
        if (inputNameDerAnlage == null) {
            throw new RuntimeException("Couldn't save input data: EditText not found");
        }
        DataHandler.answerStorage.put("name_der_anlage", inputNameDerAnlage.getText().toString());
    }


    public void oeffneFragebogenKlassifizierung(View view) {
        // Werte aus der GUI an Stauanlagen-Objekt im View Model übergeben.
        Answer BetriebsvorschriftNormalfall = mStauanlageViewModel.decideRadioAnswer(inputBetriebsvorschriftNormalbetrieb.getCheckedRadioButtonId(),
                R.id.opt_ja_betriebsvorschrift_normalbetrieb,
                R.id.opt_unknown_betriebsvorschrift_normalbetrieb,
                R.id.opt_nein_betriebsvorschrift_normalbetrieb);
        Answer BetriebsvorschriftHochwasserfall = mStauanlageViewModel.decideRadioAnswer(inputBetriebsvorschriftHochwasserfall.getCheckedRadioButtonId(),
                R.id.opt_ja_betriebsvorschrift_hochwasserfall,
                R.id.opt_unknown_betriebsvorschrift_hochwasserfall,
                R.id.opt_nein_betriebsvorschrift_hochwasserfall);


        mStauanlageViewModel.updateStauanlage(
                inputNameDerAnlage.getText().toString(),
                inputGeoLage.getText().toString(),
                inputGewaesser.getText().toString(),
                inputEigentuemer.getText().toString(),
                inputArtDesAbsperrbauwerkes.getText().toString(),
                Integer.valueOf(inputHoehe.getText().toString()),
                Integer.valueOf(inputStauinhalt.getText().toString()),
                Integer.valueOf(inputbhq1.getText().toString()),
                Integer.valueOf(inputbhq2.getText().toString()),
                BetriebsvorschriftNormalfall,
                BetriebsvorschriftHochwasserfall);

        mStauanlageViewModel.insert();//FIXME Georg: nur fuers testen!
        //nächste Activity oeffnen
        Intent oeffneFragebogenKlassifizierungIntent = new Intent(this, FragebogenKlassifizierung.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        //oeffneFragebogenKlassifizierungIntent.putExtra(); // Optional parameters
        startActivity(oeffneFragebogenKlassifizierungIntent);
    }
}