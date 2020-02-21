package de.badresden.zasa.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import de.badresden.zasa.R;

public class deleteStauanlageDialogFragment extends DialogFragment {
	/**
	 * postion of the Stauanlage that was selected
	 */
	private int position;
	/**
	 * the activity that creates an instance of this dialog must implement this Interface
	 * in order to receive event callbacks
	 * The DialogFragment is passed in case the host needs to query it (isn't nessassary for my usecase)
	 */
	public interface deleteStauanlageListener{
		public void onDialogDeleteClick(DialogFragment dialog, int position);
		public void onDialogCancelClick(DialogFragment dialog, int position);
	}

	public deleteStauanlageDialogFragment(int position) {
		super();
		this.position = position;
	}

	private deleteStauanlageListener listener;
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		//Verify the host implements the interface
		try {
			//Instantiate the deleteStauanlageListener so Event Callbacks can be send to the host
			listener = (deleteStauanlageListener) context;
		}catch (ClassCastException e){
			//The Class doesn't implement the interface throw exeception
			throw new ClassCastException(getActivity().toString() +
					"must implement deleteStauanlageListener");
		}
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		//Build the dialog and set up the Buttons
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(getString(R.string.delete_stauanlage_message))
		.setPositiveButton(R.string.delete_stauanlage_delete, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				listener.onDialogDeleteClick(deleteStauanlageDialogFragment.this, deleteStauanlageDialogFragment.this.position);
			}
		})
		.setNegativeButton(getString(R.string.delete_Stauanlage_cancel), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				listener.onDialogCancelClick(deleteStauanlageDialogFragment.this, deleteStauanlageDialogFragment.this.position);
			}
		});
		return builder.create();
	}
}
