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
import de.badresden.zasa.StauanlageHolder;
import de.badresden.zasa.StauanlageSimplyfied;
import de.badresden.zasa.StauanlageViewModel;

/**
 * Activity für Startbildschirm der App
 */
public class MainActivity extends AppCompatActivity {

    StauanlageViewModel mStauanlageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setzen des ViewModels
        mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
        //Hier wird ein Observer gesetzt der selbst keine Logik ausführt wenn er die Nachricht der LiveData bekommt, dass
        // sich etwas geändert hat. Ist ein Bugfix für den wir bis jetzt keine bessere Lösung finden konnten
        // vorher gingen Änderungen verloren,die gemacht wurden bevor der Observer in FinishedQuestionnairesActivity auf die LiveData gesetzt wurde
        // jetzt wird einer von Anfang an gesetzt
        mStauanlageViewModel.getAllStauanlagenSimplyfied().observe(this, new Observer<List<StauanlageSimplyfied>>() {
            @Override
            public void onChanged(List<StauanlageSimplyfied> stauanlageSimplyfieds) {
            }
        });
        //Sicherstellen das dass statische Feld der StauanlageViewModel null´ist, sich also keine Daten im Zwischenspeicher befinden
        //mStauanlageViewModel.clear();
        StauanlageHolder.clear();
    }
    //Autor: Felix

    /**
     * Button "Neuer Fragebogen"
     * Leitet weiter zu Activity QuestionnaireAllgemeinActivity
     * Erzeugt ein neues leeres Stauanlagen Objekt
     */
    public void openQuestionnaireAllgemein(View view) {
        StauanlageHolder.createStauanlage();
        Intent openQuestionnaireAllgemeinIntent = new Intent(this, QuestionnaireAllgemeinActivity.class);
        startActivity(openQuestionnaireAllgemeinIntent);
    }
    //Autor: Georg

    /**
     * Button "Ausgefüllte Fragebögen"
     * --> Leitet weiter zu Activity FinishedQuestionnairesActivity
     */
    public void openFinishedQuestionairesAcitivity(View view) {
        Intent openActivityFinishedQuestionaires = new Intent(this, FinishedQuestionnairesActivity.class);
        startActivity(openActivityFinishedQuestionaires);
    }

    /**
     * wenn der Zurück Button des Handys (unten Rechts) gedrückt wird, passiert nichts. Außgenommen einer Meldung die eben das erklärt.
     * Es kam sonst zu einer NullPointerException in einem bestimmten Anwendungsfall.
     */
    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(this, "Funktion ist ausgeschaltet, führt sonst zu einem Error beim zurück Drücken" +
                " nach Speichern eines Fragebogens ", Toast.LENGTH_LONG);
        toast.show();
    }
}
