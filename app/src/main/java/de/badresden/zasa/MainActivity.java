package de.badresden.zasa;
//TODO Felix und Georg: Java-Klassen mit Packages neu organisieren (Package für Activities z.B.)
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    StauanlageViewModel mStauanlageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setzen des ViewModels
        mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
    }

    public void oeffneFragebogenAllgemein(View view) {
        mStauanlageViewModel.createStauanlage();
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
