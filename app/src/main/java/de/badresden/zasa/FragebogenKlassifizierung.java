package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FragebogenKlassifizierung extends AppCompatActivity {

    private static final String LOG_TAG = "Fragebn_Klassifizierung";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragebogen_klassifizierung);
        setTitle("Klassifizierung (Erdbauwerke)");

        // TODO Gespeicherte Inputs laden
    }

    @Override
    protected void onPause() {
        super.onPause();
        // TODO Inputs speichern

    }

    public void oeffneFragebogenTragfaehigkeit(View view) {
        Intent oeffneFragebogenTragaehigkeitIntent = new Intent(this, FragebogenTragfaehigkeit.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(oeffneFragebogenTragaehigkeitIntent);
    }

}
