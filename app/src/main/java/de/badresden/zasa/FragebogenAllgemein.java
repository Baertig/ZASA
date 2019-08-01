package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class FragebogenAllgemein extends AppCompatActivity {

    public static final String NAME_DER_ANLAGE = "de.badresden.zasa.extra.name";
    private static final String LOG_TAG = FragebogenAllgemein.class.getSimpleName();

    // relevante GUI-Elemente:
    private EditText inputNameDerAnlage;
    private EditText inputGeoLage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragebogen_allgemein);
        setTitle("Allgemein");

        inputNameDerAnlage = findViewById(R.id.antw_name_der_anlage);
        inputGeoLage = findViewById(R.id.antw_lage);

        Log.d(LOG_TAG, "Loading input data...");
        // TODO for-loop über EditText Array?
        if (DataHandler.answerStorage.containsKey("name_der_anlage")) {
            inputNameDerAnlage.setText(DataHandler.answerStorage.get("name_der_anlage").toString());
        }
        if (DataHandler.answerStorage.containsKey("name_der_anlage")) {
            inputNameDerAnlage.setText(DataHandler.answerStorage.get("name_der_anlage").toString());
        }
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
        Intent oeffneFragebogenKlassifizierungIntent = new Intent(this, FragebogenKlassifizierung.class);
        Log.d(LOG_TAG, "Continue Button on page " + this.getLocalClassName() + "clicked.");
        //oeffneFragebogenKlassifizierungIntent.putExtra(); // Optional parameters
        startActivity(oeffneFragebogenKlassifizierungIntent);
    }
}