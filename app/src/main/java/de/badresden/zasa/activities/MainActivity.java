package de.badresden.zasa.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import de.badresden.zasa.R;
import de.badresden.zasa.StauanlageSimplyfied;
import de.badresden.zasa.StauanlageViewModel;

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
        StauanlageViewModel.stauanlage = null; // sicherstellen das keine Daten im Zwischenspeicher vorhanden sind
    }

    public void openQuestionnaireAllgemein(View view) {
        Intent openQuestionnaireAllgemeinIntent = new Intent(this, QuestionnaireAllgemeinActivity.class);
        openQuestionnaireAllgemeinIntent.putExtra("key", "testValue"); // Optional parameters
        startActivity(openQuestionnaireAllgemeinIntent);
    }

	public void openFinishedQuestionairesAcitivity(View view) {
        Intent openActivityFinishedQuestionaires =
                new Intent(this, FinishedQuestionairesActivity.class);
        startActivity(openActivityFinishedQuestionaires);
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(this, "Funktion ist ausgeschaltet, führt sonst zu einem Error beim zurück Drücken" +
                " nach Speichern eines Fragebogens ",Toast.LENGTH_LONG );
        toast.show();
    }
}
