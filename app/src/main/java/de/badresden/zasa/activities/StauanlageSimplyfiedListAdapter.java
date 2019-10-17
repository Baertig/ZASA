package de.badresden.zasa.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.badresden.zasa.R;
import de.badresden.zasa.StauanlageSimplyfied;

//Autor: Georg

/**
 * Adapter Klasse für die Recyclerview (definiert wie die unterelemente(Views) der Recyclerview erzeugt werden)
 */
public class StauanlageSimplyfiedListAdapter extends RecyclerView.Adapter<StauanlageSimplyfiedListAdapter.StauanlageSimplyfiedViewHolder> {

    private final LayoutInflater mInflater;
    private List<StauanlageSimplyfied> stauanlageSimplyfiedList; //gecachte Kopie

    StauanlageSimplyfiedListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StauanlageSimplyfiedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //item Layout wird geladen
        View itemView = mInflater.inflate(R.layout.finished_questionaires_recyclerview_item, parent, false);
        return new StauanlageSimplyfiedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StauanlageSimplyfiedViewHolder holder, int position) {
        if (stauanlageSimplyfiedList != null) {
            StauanlageSimplyfied current = stauanlageSimplyfiedList.get(position);
            //Name der Anlage wir als Text in Item geschrieben
            holder.StauanlageSimpyfiedItemTextView.setText(current.namederAnlage);
        } else {
            //Falls noch keine Daten zum Darstellen vorhanden sind
            holder.StauanlageSimpyfiedItemTextView.setText(R.string.keine_daten_zum_darstellen);
        }
    }

    void setStauanlageSimplyfiedList(List<StauanlageSimplyfied> stauanlageSimplyfiedList) {
        this.stauanlageSimplyfiedList = stauanlageSimplyfiedList;
        notifyDataSetChanged(); // aktualisiert die View
    }

    // getItemCount() wird häufig aufgerufen und wenn es das erstemal aufgerufen wird
    // stauanlageSimplyfiedList wurde noch nicht aktualisiert(initial ist es null, das kann nicht zurückgegeben werden).
    @Override
    public int getItemCount() {
        if (stauanlageSimplyfiedList != null)
            return stauanlageSimplyfiedList.size();
        else return 0;
    }

    class StauanlageSimplyfiedViewHolder extends RecyclerView.ViewHolder {
        private final TextView StauanlageSimpyfiedItemTextView;

        private StauanlageSimplyfiedViewHolder(@NonNull View itemView) {
            super(itemView);
            StauanlageSimpyfiedItemTextView = itemView.findViewById(R.id.questionaireNameTestView);
        }
    }
}
