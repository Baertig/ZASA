package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FragebogenDauerhaftigkeit extends AppCompatActivity {

    private static final String LOG_TAG = "Fragebn_Dauerhaftigkeit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragebogen_dauerhaftigkeit);
        setTitle("Dauerhaftigkeit");
    }


    public void oeffneFragebogenGebrauchstauglichkeit(View view) {
        Intent oeffneFragebogenGebrauchstauglichkeitIntent = new Intent(this, FragebogenGebrauchstauglichkeit.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(oeffneFragebogenGebrauchstauglichkeitIntent);
    }
}
