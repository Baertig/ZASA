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

        Log.d(LOG_TAG, "Loading input data...");
        if (DataHandler.answerStorage.containsKey("name_der_anlage")) {
            inputNameDerAnlage.setText(DataHandler.answerStorage.get("name_der_anlage").toString());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "Saving input data...");
        if (inputNameDerAnlage == null) {
            throw new RuntimeException("Couldn't save input data: Input text is null");
        }
        DataHandler.answerStorage.put("name_der_anlage", inputNameDerAnlage.getText().toString());
    }


    public void oeffneFragebogenKlassifizierung(View view) {
        Intent oeffneFragebogenKlassifizierungIntent = new Intent(this, FragebogenKlassifizierung.class);
        Log.d(LOG_TAG, "Continue Button on page " + this.getLocalClassName() + "clicked.");
        String antwNameDerAnlage = inputNameDerAnlage.getText().toString();
        oeffneFragebogenKlassifizierungIntent.putExtra(NAME_DER_ANLAGE, antwNameDerAnlage); // Optional parameters
        startActivity(oeffneFragebogenKlassifizierungIntent);
    }
}