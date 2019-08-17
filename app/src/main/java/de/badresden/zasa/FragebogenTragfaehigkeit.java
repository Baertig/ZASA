package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import java.util.Date;

public class FragebogenTragfaehigkeit extends AppCompatActivity {

    private static final String LOG_TAG = "Fragebn_Tragfaehigkeit";
    private Date datumUndUhrzeitLetzteBearbeitung;
    private String nameDerAnlage;
    private ViewModel mStauanlageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragebogen_tragfaehigkeit);
        setTitle("Tragfähigkeit");
        mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
    }

    public void oeffneFragebogenGebrauchstauglichkeit(View view) {
        Intent oeffneFragebogenGebrauchstauglichkeitIntent = new Intent(this, FragebogenGebrauchstauglichkeit.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(oeffneFragebogenGebrauchstauglichkeitIntent);
    }
}
