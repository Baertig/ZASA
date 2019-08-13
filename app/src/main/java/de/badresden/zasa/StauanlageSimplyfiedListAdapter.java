package de.badresden.zasa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StauanlageSimplyfiedListAdapter extends
		RecyclerView.Adapter<StauanlageSimplyfiedListAdapter.StauanlageSimplyfiedViewHolder> {

	private final LayoutInflater mInflater;
	private List<StauanlageSimplyfied> stauanlageSimplyfiedList; //Cached Copy

	StauanlageSimplyfiedListAdapter(Context context) {
		this.mInflater = LayoutInflater.from(context);
	}

	@NonNull
	@Override
	public StauanlageSimplyfiedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = mInflater.inflate(R.layout.finished_questionaires_recyclerview_item,parent,false);
		return new StauanlageSimplyfiedViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull StauanlageSimplyfiedViewHolder holder, int position) {
		if(stauanlageSimplyfiedList != null){
			StauanlageSimplyfied current = stauanlageSimplyfiedList.get(position);
			holder.StauanlageSimpyfiedItemView.setText(current.namederAnlage);
		}else{
			//Falls die Daten noch nicht bereit sind
			holder.StauanlageSimpyfiedItemView.setText("Keine Daten zum darstellen");
		}
	}
	//TODO: weiß nicht ob wie diese Methode wirklich brauchen
	void setStauanlageSimplyfiedList (List<StauanlageSimplyfied> stauanlageSimplyfiedList){
		this.stauanlageSimplyfiedList = stauanlageSimplyfiedList;
		notifyDataSetChanged();
	}

	// getItemCount() is called many times, and when it is first called,
	// stauanlageSimplyfiedList has not been updated (means initially, it's null, and we can't return null).
	@Override
	public int getItemCount() {
		if (stauanlageSimplyfiedList != null)
			return stauanlageSimplyfiedList.size();
		else return 0;
	}

	class StauanlageSimplyfiedViewHolder extends RecyclerView.ViewHolder {
		private final TextView StauanlageSimpyfiedItemView;

		private StauanlageSimplyfiedViewHolder(@NonNull View itemView) {
			super(itemView);
			StauanlageSimpyfiedItemView = itemView.findViewById(R.id.questionaireNameTestView);
		}
	}
}
