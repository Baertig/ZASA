package de.badresden.zasa.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import de.badresden.zasa.R;

public class gpsDisabledDialog extends DialogFragment {
	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("GPS ist momentan deaktiviert")
				.setPositiveButton("Aktivieren", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//GPS Settings öffnen ...
						// TODO how to return from there to my App ????
						Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						startActivity(settingsIntent);
					}
				})
				.setNegativeButton(getString(R.string.delete_Stauanlage_cancel), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//I don't know ... just close the damm Dialog
					}
				});
		return builder.create();
	}
}
