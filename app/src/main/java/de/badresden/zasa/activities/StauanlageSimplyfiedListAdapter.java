package de.badresden.zasa.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Date;
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
    private final Context context;

    StauanlageSimplyfiedListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
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
            holder.StauanlageSimpyfiedItemTextView.setText(current.namederAnlage);
            String date = DateFormat.getDateInstance(DateFormat.DEFAULT).format(current.datumUndUhrzeitLetzteBearbeitung);
            holder.dateTextView.setText(date);
        } else {
            //Falls noch keine Daten zum Darstellen vorhanden sind
            holder.StauanlageSimpyfiedItemTextView.setText(R.string.keine_daten_zum_darstellen);
        }
    }

    void setStauanlageSimplyfiedList(List<StauanlageSimplyfied> stauanlageSimplyfiedList) {
        this.stauanlageSimplyfiedList = stauanlageSimplyfiedList;
        notifyDataSetChanged(); // aktualisiert die View
    }

    public StauanlageSimplyfied getStauanlageSimplyfiedAtPosition(int position){
        return this.stauanlageSimplyfiedList.get(position);
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
        void onPrintClick(int position);
        void onExportClick(int position);
        void onEditClick(int position);
    }
    public void setOnItemClickListener(OnClickListener listener){
        mListener = listener;
    }

    public Context getContext(){
        return this.context;
    }

    class StauanlageSimplyfiedViewHolder extends RecyclerView.ViewHolder {
        private final TextView StauanlageSimpyfiedItemTextView;
        private final TextView dateTextView;
        private ImageView printImage;
        private ImageView exportImage;
        private ImageView editImage;
        /**
         * wird für jedes Item der RecyclerView aufgerufen
         * @param itemView ist finished_questionaires_recyclerview_item.xml -> wird "inflatet"
         */
        private StauanlageSimplyfiedViewHolder(@NonNull View itemView, final OnClickListener listener) {
            super(itemView);
            StauanlageSimpyfiedItemTextView = itemView.findViewById(R.id.questionaireNameTestView);
            printImage = itemView.findViewById(R.id.printButton);
            exportImage = itemView.findViewById(R.id.exportButton);
            editImage = itemView.findViewById(R.id.editButton);
            dateTextView = itemView.findViewById(R.id.date_text_view);

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

            printImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition(); //index mit dem auf die Liste zugegriffe werden kann
                        if(position != RecyclerView.NO_POSITION ){
                            listener.onPrintClick(position); //jetzt kann die Position in der Recyclerview Activity abgefragt werden
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
