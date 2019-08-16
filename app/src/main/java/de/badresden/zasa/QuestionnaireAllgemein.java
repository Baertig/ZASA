package de.badresden.zasa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionnaireAllgemein extends AppCompatActivity {

    private static final String LOG_TAG = QuestionnaireAllgemein.class.getSimpleName();

    // relevante GUI-Elemente:
    private EditText inputNameDerAnlage;
    private EditText inputGeoLage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_allgemein);
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


    public void openQuestionnaireKlassifizierung(View view) {
        Intent openQuestionnaireKlassifizierungIntent = new Intent(this, QuestionnaireKlassifizierung.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        //openQuestionnaireKlassifizierungIntent.putExtra(); // Optional parameters
        startActivity(openQuestionnaireKlassifizierungIntent);
    }

    public void chooseLocationByGps(View view) {

    }

}