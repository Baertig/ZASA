package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

public class QuestionnaireDauerhaftigkeit extends AppCompatActivity {

    private static final String LOG_TAG = "Fragebn_Dauerhaftigkeit";
    private StauanlageViewModel stauanlageViewModel;
    private RadioGroup inputUferveraenderungen;
    private RadioGroup inputRutschungen;
    private RadioGroup inputRisseErd;
    private RadioGroup inputSetzungen;
    private RadioGroup inputHebungenErd;
    private RadioGroup inputVerschiebungenErd;
    private RadioGroup inputWasseraustritteErd;
    private RadioGroup inputMaterialaustrittErd;
    private RadioGroup inputErosion;
    private RadioGroup inputFehlstellenGrasnarbe;
    private RadioGroup inputGehoelzbewuchs;
    private RadioGroup inputVisuelleEinschraenkung;
    private RadioGroup inputGrabendeTiere;
    private RadioGroup inputRisseMassiv;
    private RadioGroup inputSetzungenMassiv;
    private RadioGroup inputVerschiebungenMassiv;
    private RadioGroup inputKippungen;
    private RadioGroup inputAbplatzungen;
    private RadioGroup inputAuswaschungen;
    private RadioGroup inputWasseraustritteMassiv;
    private RadioGroup inputFehlstellenMauerwerk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_dauerhaftigkeit);
        setTitle("Dauerhaftigkeit");

        inputUferveraenderungen = findViewById(R.id.radio_uferveraenderungen);
        inputRutschungen = findViewById(R.id.radio_rutschungen);
        inputRisseErd = findViewById(R.id.radio_risse_erd);
        inputSetzungen = findViewById(R.id.radio_setzungen); //TODO setzungen_erd ? weil auch setzungen_massiv
        inputHebungenErd = findViewById(R.id.radio_hebungen_erd);
        inputVerschiebungenErd = findViewById(R.id.radio_verschiebungen_erd);
        inputWasseraustritteErd = findViewById(R.id.radio_wasseraustritte_erd);
        inputMaterialaustrittErd = findViewById(R.id.radio_materialaustritt_erd);
        inputErosion = findViewById(R.id.radio_erosion);
        inputFehlstellenGrasnarbe = findViewById(R.id.radio_fehlstellen_grasnarbe);
        inputGehoelzbewuchs = findViewById(R.id.radio_gehoelzbewuchs);
        inputVisuelleEinschraenkung = findViewById(R.id.radio_visuelle_einschraenkung);
        inputGrabendeTiere = findViewById(R.id.radio_grabende_tiere);
        inputRisseMassiv = findViewById(R.id.radio_risse_massiv);
        inputSetzungenMassiv = findViewById(R.id.radio_setzungen_massiv);
        inputVerschiebungenMassiv = findViewById(R.id.radio_verschiebungen_massiv);
        inputKippungen = findViewById(R.id.radio_kippungen);
        inputAbplatzungen = findViewById(R.id.radio_abplatzungen);
        inputAuswaschungen = findViewById(R.id.radio_auswaschungen);
        inputWasseraustritteMassiv = findViewById(R.id.radio_wasseraustritte_massiv);
        inputFehlstellenMauerwerk = findViewById(R.id.radio_fehlstellen_mauerwerk);

        stauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);

    }

    public void submitData(View view) {
        Answer uferveraenderungen = stauanlageViewModel.decideRadioAnswer(inputUferveraenderungen.getCheckedRadioButtonId(),R.id.opt_yes_uferveraenderungen,
                R.id.opt_unknown_uferveraenderungen,R.id.opt_no_uferveraenderungen);
        Answer rutschungen = stauanlageViewModel.decideRadioAnswer(inputRutschungen.getCheckedRadioButtonId(),R.id.opt_yes_rutschungen,
                R.id.opt_unknown_rutschungen,R.id.opt_no_rutschungen);
        Answer risseErd = stauanlageViewModel.decideRadioAnswer(inputRisseErd.getCheckedRadioButtonId(),R.id.opt_yes_risse_erd,
                R.id.opt_unknown_risse_erd,R.id.opt_no_risse_erd);
        Answer setzungen = stauanlageViewModel.decideRadioAnswer(inputSetzungen.getCheckedRadioButtonId(),R.id.opt_yes_setzungen,
                R.id.opt_unknown_setzungen,R.id.opt_no_setzungen);
        Answer hebungenErd = stauanlageViewModel.decideRadioAnswer(inputHebungenErd.getCheckedRadioButtonId(),R.id.opt_yes_hebungen_erd,
                R.id.opt_unknown_hebungen_erd,R.id.opt_no_hebungen_erd);


        /*stauanlageViewModel.updateDauerhaftigkeit(
                uferveraenderungen,
                rutschungen,
                risseErd,
                setzungen,
                hebungenErd,
        ); */

        Intent goBackToMainPageIntent = new Intent(this, MainActivity.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(goBackToMainPageIntent);
    }
}
