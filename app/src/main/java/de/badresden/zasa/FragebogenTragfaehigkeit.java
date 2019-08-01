package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FragebogenTragfaehigkeit extends AppCompatActivity {

    private static final String LOG_TAG = "Fragebn_Tragfaehigkeit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragebogen_tragfaehigkeit);
        setTitle("Tragfähigkeit");
    }
}
