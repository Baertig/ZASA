package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import java.util.Date;

public class QuestionnaireTragfaehigkeit extends AppCompatActivity {

    private static final String LOG_TAG = "Fragebn_Tragfaehigkeit";
    private Date datumUndUhrzeitLetzteBearbeitung;
    private String nameDerAnlage;
    private ViewModel mStauanlageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_tragfaehigkeit);
        setTitle("Tragfähigkeit");
        mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
    }

    public void openQuestionnaireGebrauchstauglichkeit(View view) {
        Intent openQuestionnaireGebrauchstauglichkeitIntent = new Intent(this, QuestionnaireGebrauchstauglichkeit.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(openQuestionnaireGebrauchstauglichkeitIntent);
    }
}
