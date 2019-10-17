package de.badresden.zasa.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private OnClickListener mListener;

    StauanlageSimplyfiedListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StauanlageSimplyfiedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //item Layout wird geladen
        View itemView = mInflater.inflate(R.layout.finished_questionaires_recyclerview_item, parent, false);
        return new StauanlageSimplyfiedViewHolder(itemView,mListener);
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

    public interface OnClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onExportClick(int position);
        void onEditClick(int position);
    }
    public void setOnItemClickListener(OnClickListener listener){
        mListener = listener;
    }

    class StauanlageSimplyfiedViewHolder extends RecyclerView.ViewHolder {
        private final TextView StauanlageSimpyfiedItemTextView;
        private ImageView deleteImage;
        private ImageView exportImage;
        private ImageView editImage;
        /**
         * wird für jedes Item der RecyclerView aufgerufen
         * @param itemView ist finished_questionaires_recyclerview_item.xml -> wird "inflatet"
         */
        private StauanlageSimplyfiedViewHolder(@NonNull View itemView, final OnClickListener listener) {
            super(itemView);
            StauanlageSimpyfiedItemTextView = itemView.findViewById(R.id.questionaireNameTestView);
            deleteImage = itemView.findViewById(R.id.deleteButton);
            exportImage = itemView.findViewById(R.id.exportButton);
            editImage = itemView.findViewById(R.id.editButton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition(); //index mit dem auf die Liste zugegriffe werden kann
                        if(position != RecyclerView.NO_POSITION ){
                            listener.onItemClick(position); //jetzt kann die Position in der Recyclerview Activity abgefragt werden
                        }
                    }
                }
            });

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition(); //index mit dem auf die Liste zugegriffe werden kann
                        if(position != RecyclerView.NO_POSITION ){
                            listener.onDeleteClick(position); //jetzt kann die Position in der Recyclerview Activity abgefragt werden
                        }
                    }
                }
            });

            exportImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition(); //index mit dem auf die Liste zugegriffe werden kann
                        if(position != RecyclerView.NO_POSITION ){
                            listener.onExportClick(position); //jetzt kann die Position in der Recyclerview Activity abgefragt werden
                        }
                    }
                }
            });

            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition(); //index mit dem auf die Liste zugegriffe werden kann
                        if(position != RecyclerView.NO_POSITION ){
                            listener.onEditClick(position); //jetzt kann die Position in der Recyclerview Activity abgefragt werden
                        }
                    }
                }
            });
        }
    }


}
