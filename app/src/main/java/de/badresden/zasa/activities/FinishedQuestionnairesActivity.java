package de.badresden.zasa.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import de.badresden.zasa.Fragments.deleteStauanlageDialogFragment;
import de.badresden.zasa.R;
import de.badresden.zasa.Stauanlage;
import de.badresden.zasa.StauanlageHolder;
import de.badresden.zasa.StauanlageSimplyfied;
import de.badresden.zasa.StauanlageViewModel;

//Autor: Georg
/**
 * Activity in der die bereits bearbeiteten Fragebögen dargestellt werden
 */

public class FinishedQuestionnairesActivity extends AppCompatActivity implements deleteStauanlageDialogFragment.deleteStauanlageListener {
    private final String TAG = FinishedQuestionnairesActivity.class.getSimpleName();
    private StauanlageViewModel mStauanlageViewModel;
    private RecyclerView recyclerView;
    private StauanlageSimplyfiedListAdapter adapter ; //war mal final...weiß nicht warum ??
    private Activity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_questionaires); //Layout datei wird geladen
        adapter = new StauanlageSimplyfiedListAdapter(this);
        buildRecyclerView();
        ItemTouchHelper helper = makeHelper();
        helper.attachToRecyclerView(recyclerView);
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

    private ItemTouchHelper makeHelper() {
        return new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    private final ColorDrawable background = new ColorDrawable(Color.RED);
                    private Drawable icon = ContextCompat.getDrawable(adapter.getContext(),
                            R.drawable.ic_delete_white_24dp);;
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        DialogFragment deleteDialog = new deleteStauanlageDialogFragment(position);
                        deleteDialog.show(getSupportFragmentManager(), "DeleteDialogFragment");
                    }

                    //Animation für roten Hintergrund und icon
                    @Override
                    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                            int actionState, boolean isCurrentlyActive) {
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        View itemView = viewHolder.itemView;
                        int backgroundCornerOffset = 20;
                        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                        int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                        int iconBottom = iconTop + icon.getIntrinsicHeight();

                        if (dX > 0) { // Swiping to the right
                            int iconLeft = ((int) dX) - iconMargin - icon.getIntrinsicWidth();
                            int iconRight = ((int) dX) - iconMargin;
                            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                            background.setBounds(itemView.getLeft(), itemView.getTop(),
                                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
                                    itemView.getBottom());

                        } else if (dX < 0) { // Swiping to the left
                            int iconLeft = ((int) dX) + iconMargin + itemView.getRight();
                            int iconRight = ((int) dX) + iconMargin + icon.getIntrinsicWidth() + itemView.getRight();
                            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
                        } else { // view is unSwiped
                            background.setBounds(0, 0, 0, 0);
                        }
                        background.draw(c);
                        icon.draw(c);
                    }
                }
        );
    }

    /**
     * enthält die Logik um die RecyclerView zusammenzudönern
     */
    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
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
            public void onPrintClick(int position) {
                //TODO Tutorial fürs Erzeugen einer PDF
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


    @Override
    public void onDialogDeleteClick(DialogFragment dialog, int position) {
        StauanlageSimplyfied swipedStauanlageSimple = adapter.getStauanlageSimplyfiedAtPosition(position);
        mStauanlageViewModel.deleteStauanlage(swipedStauanlageSimple);
    }

    @Override
    public void onDialogCancelClick(DialogFragment dialog, int position) {
        Toast cancelMessage = Toast.makeText(currentActivity,"Vorgang abgebrochen", Toast.LENGTH_LONG);
        cancelMessage.show();
        adapter.notifyDataSetChanged();
    }
}
