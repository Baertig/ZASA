package de.badresden.zasa.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import de.badresden.zasa.R;
import de.badresden.zasa.StauanlageHolder;
import de.badresden.zasa.StauanlageSimplyfied;
import de.badresden.zasa.StauanlageViewModel;

//Autor: Georg
/**
 * Activity in der die bereits bearbeiteten Fragebögen dargestellt werden
 */
@SuppressWarnings("FieldCanBeLocal")
public class FinishedQuestionnairesActivity extends AppCompatActivity {
    private final String TAG = FinishedQuestionnairesActivity.class.getSimpleName();
    private StauanlageViewModel mStauanlageViewModel;
    private RecyclerView recyclerView;
    private StauanlageSimplyfiedListAdapter adapter ; //war mal final...weiß nicht warum ??
    private Activity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_questionaires); //Layout datei wird geladen
        buildRecyclerView();
        currentActivity = this;
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
        if(StauanlageHolder.getStauanlage() != null){
            Log.d(TAG, "onCreate: stauanlage in ViewModel-class was not empty, but should be");
            StauanlageHolder.clear(); //set stauanlage null
        }
    }

    /**
     * enthält die Logik um die RecyclerView zusammenzudönern
     */
    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new StauanlageSimplyfiedListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener(new StauanlageSimplyfiedListAdapter.OnClickListener() {
            @Override
            //TODO vllt mal nutzen um mehr Informationen zu Datensatz darzustellen und gegen NullPointerException absichern
            public void onItemClick(int position) {
                StauanlageSimplyfied testStauanlage = mStauanlageViewModel.getAllStauanlagenSimplyfied().getValue().get(position);
                Log.d(TAG, "onItemClick: Name" + testStauanlage.namederAnlage );

            }

            @Override
            public void onDeleteClick(int position) {
                //TODO Tutorial fürs Löschen von Datensätzen zu ende machen
            }

            @Override
            public void onExportClick(int position) {
                //irgendwann später implementieren
            }

            @Override
            public void onEditClick(int position) {
                StauanlageSimplyfied currentStauanlage = mStauanlageViewModel.getAllStauanlagenSimplyfied().getValue().get(position);
                mStauanlageViewModel.loadStauanlageWith(currentActivity, currentStauanlage.primaryKey);
            }
        });
    }


}
