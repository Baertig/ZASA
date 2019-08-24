package de.badresden.zasa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import de.badresden.zasa.R;

public class QuestionnaireKlassifizierungActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Fragebn_Klassifizierung";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_klassifizierung);
        setTitle("Klassifizierung (Erdbauwerke)");

        // TODO Gespeicherte Inputs laden
    }


    public void openQuestionnaireTragfaehigkeit(View view) {
        Intent openQuestionnaireTragaehigkeitIntent = new Intent(this, QuestionnaireTragfaehigkeitActivity.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(openQuestionnaireTragaehigkeitIntent);
    }

}
