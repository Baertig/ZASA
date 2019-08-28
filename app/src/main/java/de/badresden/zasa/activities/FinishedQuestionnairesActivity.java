package de.badresden.zasa.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import de.badresden.zasa.R;
import de.badresden.zasa.StauanlageSimplyfied;
import de.badresden.zasa.StauanlageViewModel;

//Autor: Georg
/**
 * Activity in der die bereits bearbeiteten Fragebögen dargestellt werden
 */
@SuppressWarnings("FieldCanBeLocal")
public class FinishedQuestionnairesActivity extends AppCompatActivity {
    private StauanlageViewModel mStauanlageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_questionaires); //Layout datei wird geladen
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final StauanlageSimplyfiedListAdapter adapter = new StauanlageSimplyfiedListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
        //Observer gesetzt, sobald es eine Änderung gibt aktualisiert der Adapter seine Daten, somit ändern sich die
        //angezeigten Daten in der Recyclerview
        mStauanlageViewModel.getAllStauanlagenSimplyfied().observe(this,
                new Observer<List<StauanlageSimplyfied>>() {
                    @Override
                    public void onChanged(@Nullable final List<StauanlageSimplyfied> stauanlageSimplyfiedList) {
                        adapter.setStauanlageSimplyfiedList(stauanlageSimplyfiedList);
                    }
                });
    }

    /**
     * Button Bearbeiten (Stift Icon)
     * --> Momentan mit keiner Logik verknüpft
     * @param view
     */
    public void editClick(View view) {
        Toast toast = Toast.makeText(this, "Edit Button hat noch keine Funktion", Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Button Löschen (Mülltonne Icon)
     * --> Momentan mit keiner Logik verknüpft
     * @param view
     */
    public void deleteClick(View view) {
        Toast toast = Toast.makeText(this, "Delete Button hat noch keine Funktion", Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Button Hochladen (Feil nach oben Icon)
     * --> Momentan mit keiner Logik verknüpft
     * @param view
     */
    public void exportClick(View view) {
        Toast toast = Toast.makeText(this, "Export Button hat noch keine Funktion", Toast.LENGTH_LONG);
        toast.show();
    }
}
