package de.badresden.zasa;
//TODO Felix und Georg: Java-Klassen mit Packages neu organisieren (Package für Activities z.B.)
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void oeffneFragebogenAllgemein(View view) {
        Intent oeffneFragebogenAllgemeinIntent = new Intent(this, FragebogenAllgemein.class);
        oeffneFragebogenAllgemeinIntent.putExtra("key", "testValue"); // Optional parameters
        startActivity(oeffneFragebogenAllgemeinIntent);
    }

	public void openFinishedQuestionairesAcitivity(View view) {
        Intent openActivityFinishedQuestionaires =
                new Intent(this,FinishedQuestionaires.class);
        startActivity(openActivityFinishedQuestionaires);
    }
}
