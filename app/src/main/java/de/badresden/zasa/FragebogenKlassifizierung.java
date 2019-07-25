package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class FragebogenKlassifizierung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragebogen_klassifizierung);
        setTitle("Klassifizierung");

        Intent intent = getIntent();
        Toast.makeText(this, intent.getStringExtra(FragebogenAllgemein.NAME_DER_ANLAGE), Toast.LENGTH_LONG).show();
    }
}
