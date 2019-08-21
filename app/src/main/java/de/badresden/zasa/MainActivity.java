package de.badresden.zasa;
//TODO Felix und Georg: Java-Klassen mit Packages neu organisieren (Package für Activities z.B.)
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    StauanlageViewModel mStauanlageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setzen des ViewModels
        mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
        //Observer der dafür sorgt das die LiveData weiß, dass sie beobachtet wird
        mStauanlageViewModel.getAllStauanlagenSimplyfied().observe(this, new Observer<List<StauanlageSimplyfied>>() {
            @Override
            public void onChanged(List<StauanlageSimplyfied> stauanlageSimplyfieds) {
            }
        });
    }

    public void openQuestionnaireAllgemein(View view) {
        Intent openQuestionnaireAllgemeinIntent = new Intent(this, QuestionnaireAllgemein.class);
        openQuestionnaireAllgemeinIntent.putExtra("key", "testValue"); // Optional parameters
        startActivity(openQuestionnaireAllgemeinIntent);
    }

	public void openFinishedQuestionairesAcitivity(View view) {
        Intent openActivityFinishedQuestionaires =
                new Intent(this,FinishedQuestionaires.class);
        startActivity(openActivityFinishedQuestionaires);
    }
}
