package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class QuestionnaireGebrauchstauglichkeit extends AppCompatActivity {

    private static final String LOG_TAG = "F_Gebrauchstauglichkeit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_gebrauchstauglichkeit);
        setTitle("Gebrauchstauglichkeit");
    }

    public void openQuestionnaireDauerhaftigkeit(View view) {
        Intent openQuestionnaireDauerhaftigkeitIntent = new Intent(this, QuestionnaireDauerhaftigkeit.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(openQuestionnaireDauerhaftigkeitIntent);
    }

}
