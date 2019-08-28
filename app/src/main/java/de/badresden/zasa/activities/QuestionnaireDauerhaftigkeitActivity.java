package de.badresden.zasa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import de.badresden.zasa.Answer;
import de.badresden.zasa.R;
import de.badresden.zasa.StauanlageViewModel;

//Autor: Georg

/**
 * Es Werden die Fragen der Kategorie Dauerhaftigkeit beantwortet
 */
public class QuestionnaireDauerhaftigkeitActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Fragebn_Dauerhaftigkeit";
    //relevante GUI Elemente
    private StauanlageViewModel stauanlageViewModel;
    private RadioGroup inputUferveraenderungen;
    private RadioGroup inputRutschungen;
    private RadioGroup inputRisseErd;
    private RadioGroup inputSetzungen;
    private RadioGroup inputHebungenErd;
    private RadioGroup inputVerschiebungenErd;
    private RadioGroup inputWasseraustritteErd;
    private RadioGroup inputMaterialaustragErd;
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

    //Autor: Felix
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_dauerhaftigkeit);
        setTitle("Dauerhaftigkeit");
        //Autor: Georg
        //setzten der GUI Elemente
        inputUferveraenderungen = findViewById(R.id.radio_uferveraenderungen);
        inputRutschungen = findViewById(R.id.radio_rutschungen);
        inputRisseErd = findViewById(R.id.radio_risse_erd);
        inputSetzungen = findViewById(R.id.radio_setzungen); //TODO setzungen_erd ? weil auch setzungen_massiv
        inputHebungenErd = findViewById(R.id.radio_hebungen_erd);
        inputVerschiebungenErd = findViewById(R.id.radio_verschiebungen_erd);
        inputWasseraustritteErd = findViewById(R.id.radio_wasseraustritte_erd);
        inputMaterialaustragErd = findViewById(R.id.radio_materialaustritt_erd);
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

    /**
     * Button "Fertig"
     * --> Werte aus der GUI auslesen und speichern
     * --> die zwischengespeicherten Daten in die Datenbank einfügen
     */
    public void submitData(View view) {
        //Au
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
        Answer verschiebungenErd = stauanlageViewModel.decideRadioAnswer(inputVerschiebungenErd.getCheckedRadioButtonId(),R.id.opt_yes_verschiebungen_erd,
                R.id.opt_unknown_verschiebungen_erd,R.id.opt_no_verschiebungen_erd);
        Answer wasseraustritteErd = stauanlageViewModel.decideRadioAnswer(inputWasseraustritteErd.getCheckedRadioButtonId(),R.id.opt_yes_wasseraustritte_erd,
                R.id.opt_unknown_wasseraustritte_erd,R.id.opt_no_wasseraustritte_erd);
        Answer materialaustragErd = stauanlageViewModel.decideRadioAnswer(inputMaterialaustragErd.getCheckedRadioButtonId(),R.id.opt_yes_materialaustritt_erd,
                R.id.opt_unknown_materialaustritt_erd,R.id.opt_no_materialaustritt_erd);
        Answer erosion = stauanlageViewModel.decideRadioAnswer(inputErosion.getCheckedRadioButtonId(),R.id.opt_yes_erosion,
                R.id.opt_unknown_erosion,R.id.opt_no_erosion);
        Answer fehlstellenGrasnarbe = stauanlageViewModel.decideRadioAnswer(inputFehlstellenGrasnarbe.getCheckedRadioButtonId(),R.id.opt_yes_fehlstellen_grasnarbe,
                R.id.opt_unknown_fehlstellen_grasnarbe,R.id.opt_no_fehlstellen_grasnarbe);
        Answer gehoelzbewuchs = stauanlageViewModel.decideRadioAnswer(inputGehoelzbewuchs.getCheckedRadioButtonId(),R.id.opt_yes_gehoelzbewuchs,
                R.id.opt_unknown_gehoelzbewuchs,R.id.opt_no_gehoelzbewuchs);
        Answer visuelleEinschraenkungen = stauanlageViewModel.decideRadioAnswer(inputVisuelleEinschraenkung.getCheckedRadioButtonId(),R.id.opt_yes_visuelle_einschraenkung,
                R.id.opt_unknown_visuelle_einschraenkung,R.id.opt_no_visuelle_einschraenkung);
        Answer grabendeTiere = stauanlageViewModel.decideRadioAnswer(inputGrabendeTiere.getCheckedRadioButtonId(),R.id.opt_yes_grabende_tiere,
                R.id.opt_unknown_grabende_tiere,R.id.opt_no_grabende_tiere);
        Answer risseMassiv = stauanlageViewModel.decideRadioAnswer(inputRisseMassiv.getCheckedRadioButtonId(),R.id.opt_yes_risse_massiv,
                R.id.opt_unknown_risse_massiv,R.id.opt_no_risse_massiv);
        Answer setzungenMassiv = stauanlageViewModel.decideRadioAnswer(inputSetzungenMassiv.getCheckedRadioButtonId(),R.id.opt_yes_setzungen_massiv,
                R.id.opt_unknown_setzungen_massiv,R.id.opt_no_setzungen_massiv);
        Answer verschiebungenMassiv = stauanlageViewModel.decideRadioAnswer(inputVerschiebungenMassiv.getCheckedRadioButtonId(),R.id.opt_yes_verschiebungen_massiv,
                R.id.opt_unknown_verschiebungen_massiv,R.id.opt_no_verschiebungen_massiv);
        Answer kippungen = stauanlageViewModel.decideRadioAnswer(inputKippungen.getCheckedRadioButtonId(),R.id.opt_yes_kippungen,
                R.id.opt_unknown_kippungen,R.id.opt_no_kippungen);
        Answer abplatzungen = stauanlageViewModel.decideRadioAnswer(inputAbplatzungen.getCheckedRadioButtonId(),R.id.opt_yes_abplatzungen,
                R.id.opt_unknown_abplatzungen,R.id.opt_no_abplatzungen);
        Answer auswaschungen = stauanlageViewModel.decideRadioAnswer(inputAuswaschungen.getCheckedRadioButtonId(),R.id.opt_yes_auswaschungen,
                R.id.opt_unknown_auswaschungen,R.id.opt_no_auswaschungen);
        Answer wasseraustritteMassiv = stauanlageViewModel.decideRadioAnswer(inputWasseraustritteMassiv.getCheckedRadioButtonId(),R.id.opt_yes_wasseraustritte_massiv,
                R.id.opt_unknown_wasseraustritte_massiv,R.id.opt_no_wasseraustritte_massiv);
        Answer fehlstellenMauerwerk = stauanlageViewModel.decideRadioAnswer(inputFehlstellenMauerwerk.getCheckedRadioButtonId(),R.id.opt_yes_fehlstellen_mauerwerk,
                R.id.opt_unknown_fehlstellen_mauerwerk,R.id.opt_no_fehlstellen_mauerwerk);


        stauanlageViewModel.updateDauerhaftigkeit(
                uferveraenderungen,
                rutschungen,
                risseErd,
                setzungen,
                hebungenErd,
                verschiebungenErd,
                wasseraustritteErd,
                materialaustragErd,
                erosion,
                fehlstellenGrasnarbe,
                gehoelzbewuchs,
                visuelleEinschraenkungen,
                grabendeTiere,
                risseMassiv,
                setzungenMassiv,
                verschiebungenMassiv,
                kippungen,
                abplatzungen,
                auswaschungen,
                wasseraustritteMassiv,
                fehlstellenMauerwerk
        );
        stauanlageViewModel.insert(); //SQL Befehl INSERT triggern
        //Autor: Felix
        Intent goBackToMainPageIntent = new Intent(this, MainActivity.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(goBackToMainPageIntent);
    }
}
