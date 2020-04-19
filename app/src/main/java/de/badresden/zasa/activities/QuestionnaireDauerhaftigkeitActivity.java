package de.badresden.zasa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import de.badresden.zasa.Answer;
import de.badresden.zasa.R;
import de.badresden.zasa.Stauanlage;
import de.badresden.zasa.StauanlageHolder;
import de.badresden.zasa.StauanlageViewModel;

import static de.badresden.zasa.functions.HelpFunctions.decideRadioAnswer;
import static de.badresden.zasa.functions.HelpFunctions.loadAnswerInRadioGroup;


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
    private RadioGroup inputSetzungenErd;
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

    //RadioButtons
    private RadioButton inputUferveraenderungen_JA;
    private RadioButton inputUferveraenderungen_NEIN;
    private RadioButton inputUferveraenderungen_UNBEKANNT;

    private RadioButton inputRutschungen_JA;
    private RadioButton inputRutschungen_NEIN;
    private RadioButton inputRutschungen_UNBEKANNT;

    private RadioButton inputRisseErd_JA;
    private RadioButton inputRisseErd_NEIN;
    private RadioButton inputRisseErd_UNBEKANNT;

    private RadioButton inputSetzungenErd_JA;
    private RadioButton inputSetzungenErd_NEIN;
    private RadioButton inputSetzungenErd_UNBEKANNT;

    private RadioButton inputHebungenErd_JA;
    private RadioButton inputHebungenErd_NEIN;
    private RadioButton inputHebungenErd_UNBEKANNT;

    private RadioButton inputVerschiebungenErd_JA;
    private RadioButton inputVerschiebungenErd_NEIN;
    private RadioButton inputVerschiebungenErd_UNBEKANNT;

    private RadioButton inputWasseraustritteErd_JA;
    private RadioButton inputWasseraustritteErd_NEIN;
    private RadioButton inputWasseraustritteErd_UNBEKANNT;

    private RadioButton inputMaterialaustragErd_JA;
    private RadioButton inputMaterialaustragErd_NEIN;
    private RadioButton inputMaterialaustragErd_UNBEKANNT;

    private RadioButton inputErosion_JA;
    private RadioButton inputErosion_NEIN;
    private RadioButton inputErosion_UNBEKANNT;

    private RadioButton inputFehlstellenGrasnarbe_JA;
    private RadioButton inputFehlstellenGrasnarbe_NEIN;
    private RadioButton inputFehlstellenGrasnarbe_UNBEKANNT;

    private RadioButton inputGehoelzbewuchs_JA;
    private RadioButton inputGehoelzbewuchs_NEIN;
    private RadioButton inputGehoelzbewuchs_UNBEKANNT;

    private RadioButton inputVisuelleEinschraenkung_JA;
    private RadioButton inputVisuelleEinschraenkung_NEIN;
    private RadioButton inputVisuelleEinschraenkung_UNBEKANNT;

    private RadioButton inputGrabendeTiere_JA;
    private RadioButton inputGrabendeTiere_NEIN;
    private RadioButton inputGrabendeTiere_UNBEKANNT;

    private RadioButton inputRisseMassiv_JA;
    private RadioButton inputRisseMassiv_NEIN;
    private RadioButton inputRisseMassiv_UNBEKANNT;

    private RadioButton inputSetzungenMassiv_JA;
    private RadioButton inputSetzungenMassiv_NEIN;
    private RadioButton inputSetzungenMassiv_UNBEKANNT;

    private RadioButton inputVerschiebungenMassiv_JA;
    private RadioButton inputVerschiebungenMassiv_NEIN;
    private RadioButton inputVerschiebungenMassiv_UNBEKANNT;

    private RadioButton inputKippungen_JA;
    private RadioButton inputKippungen_NEIN;
    private RadioButton inputKippungen_UNBEKANNT;

    private RadioButton inputAbplatzungen_JA;
    private RadioButton inputAbplatzungen_NEIN;
    private RadioButton inputAbplatzungen_UNBEKANNT;

    private RadioButton inputAuswaschungen_JA;
    private RadioButton inputAuswaschungen_NEIN;
    private RadioButton inputAuswaschungen_UNBEKANNT;

    private RadioButton inputWasseraustritteMassiv_JA;
    private RadioButton inputWasseraustritteMassiv_NEIN;
    private RadioButton inputWasseraustritteMassiv_UNBEKANNT;

    private RadioButton inputFehlstellenMauerwerk_JA;
    private RadioButton inputFehlstellenMauerwerk_NEIN;
    private RadioButton inputFehlstellenMauerwerk_UNBEKANNT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_dauerhaftigkeit);
        setTitle("Dauerhaftigkeit");
        SetGuiElements();
        setRadioButtons();
        stauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
        if(StauanlageHolder.getStauanlage() != null){
            loadStauanlageInUI(StauanlageHolder.getStauanlage());
        }
    }

    private void loadStauanlageInUI(Stauanlage stauanlage) {
        loadAnswerInRadioGroup(stauanlage.uferveraenderungenDesStausees,
                inputUferveraenderungen_JA,inputUferveraenderungen_NEIN,inputUferveraenderungen_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.rutschungen,
                inputRutschungen_JA,inputRutschungen_NEIN,inputRutschungen_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.risseErdbau,
                inputRisseErd_JA,inputRisseErd_NEIN,inputRisseErd_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.sichtbareSetzungenErdbaubau,
                inputSetzungenErd_JA,inputSetzungenErd_NEIN,inputSetzungenErd_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.sichtbareHebungen,
                inputHebungenErd_JA,inputHebungenErd_NEIN,inputHebungenErd_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.sichtbareHorizontalverschiebungenErdbau,
                inputVerschiebungenErd_JA,inputVerschiebungenErd_NEIN,inputVerschiebungenErd_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.luftseitigeWasseraustritteErdbau,
                inputWasseraustritteErd_JA,inputWasseraustritteErd_NEIN,inputWasseraustritteErd_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.materialaustragDurchSickerwasser,
                inputMaterialaustragErd_JA,inputMaterialaustragErd_NEIN,inputMaterialaustragErd_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.erosionDerWasserseitigenSchutzschicht,
                inputErosion_JA,inputErosion_NEIN,inputErosion_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.fehlstellenInDerGrasnarbeAufDemDamm,
                inputFehlstellenGrasnarbe_JA,inputFehlstellenGrasnarbe_NEIN,inputFehlstellenGrasnarbe_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.gehoelzbewuchsOhneZusatzquerschnitt,
                inputGehoelzbewuchs_JA,inputGehoelzbewuchs_NEIN,inputGehoelzbewuchs_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.einschraenkungVisuelleInspektionsmoeglichkeitenBewuchsLuftseitigenBoeschung,
                inputVisuelleEinschraenkung_JA,inputVisuelleEinschraenkung_NEIN,inputVisuelleEinschraenkung_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.grabendeTiere,
                inputGrabendeTiere_JA,inputGrabendeTiere_NEIN,inputGrabendeTiere_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.risseMassivbau,
                inputRisseMassiv_JA,inputRisseMassiv_NEIN,inputRisseMassiv_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.sichtbareSetzungenMassivbau,
                inputSetzungenMassiv_JA,inputSetzungenMassiv_NEIN,inputSetzungenMassiv_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.sichtbareHorizontalverschiebungenMassivbau,
                inputVerschiebungenMassiv_JA,inputVerschiebungenMassiv_NEIN,inputVerschiebungenMassiv_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.neigungsaenderungenOderKippungen,
                inputKippungen_JA,inputKippungen_NEIN,inputKippungen_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.abplatzungen,
                inputAbplatzungen_JA,inputAbplatzungen_NEIN,inputAbplatzungen_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.auswaschungenOderAusbluehungen,
                inputAuswaschungen_JA,inputAuswaschungen_NEIN,inputAuswaschungen_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.luftseitigeWasseraustritteMassivbau,
                inputWasseraustritteMassiv_JA,inputWasseraustritteMassiv_NEIN,inputWasseraustritteMassiv_UNBEKANNT);
        loadAnswerInRadioGroup(stauanlage.fehlstellenImMauerwerk,
                inputFehlstellenMauerwerk_JA,inputFehlstellenMauerwerk_NEIN,inputFehlstellenMauerwerk_UNBEKANNT);
    }

    private void setRadioButtons() {
        inputUferveraenderungen_JA = findViewById(R.id.opt_yes_uferveraenderungen);
        inputUferveraenderungen_NEIN = findViewById(R.id.opt_no_uferveraenderungen);
        inputUferveraenderungen_UNBEKANNT = findViewById(R.id.opt_unknown_uferveraenderungen);

        inputRutschungen_JA = findViewById(R.id.opt_yes_rutschungen);
        inputRutschungen_NEIN = findViewById(R.id.opt_no_rutschungen);
        inputRutschungen_UNBEKANNT = findViewById(R.id.opt_unknown_rutschungen);

        inputRisseErd_JA = findViewById(R.id.opt_yes_risse_erd);
        inputRisseErd_NEIN = findViewById(R.id.opt_no_risse_erd);
        inputRisseErd_UNBEKANNT = findViewById(R.id.opt_unknown_risse_erd);

        inputSetzungenErd_JA = findViewById(R.id.opt_yes_setzungen);
        inputSetzungenErd_NEIN = findViewById(R.id.opt_no_setzungen);
        inputSetzungenErd_UNBEKANNT = findViewById(R.id.opt_unknown_setzungen);

        inputHebungenErd_JA = findViewById(R.id.opt_yes_hebungen_erd);
        inputHebungenErd_NEIN = findViewById(R.id.opt_no_hebungen_erd);
        inputHebungenErd_UNBEKANNT = findViewById(R.id.opt_unknown_hebungen_erd);

        inputVerschiebungenErd_JA = findViewById(R.id.opt_yes_verschiebungen_erd);
        inputVerschiebungenErd_NEIN = findViewById(R.id.opt_no_verschiebungen_erd);
        inputVerschiebungenErd_UNBEKANNT = findViewById(R.id.opt_unknown_verschiebungen_erd);

        inputWasseraustritteErd_JA = findViewById(R.id.opt_yes_wasseraustritte_erd);
        inputWasseraustritteErd_NEIN = findViewById(R.id.opt_no_wasseraustritte_erd);
        inputWasseraustritteErd_UNBEKANNT = findViewById(R.id.opt_unknown_wasseraustritte_erd);

        inputMaterialaustragErd_JA = findViewById(R.id.opt_yes_materialaustritt_erd);
        inputMaterialaustragErd_NEIN = findViewById(R.id.opt_no_materialaustritt_erd);
        inputMaterialaustragErd_UNBEKANNT = findViewById(R.id.opt_unknown_materialaustritt_erd);

        inputErosion_JA = findViewById(R.id.opt_yes_erosion);
        inputErosion_NEIN = findViewById(R.id.opt_no_erosion);
        inputErosion_UNBEKANNT = findViewById(R.id.opt_unknown_erosion);

        inputFehlstellenGrasnarbe_JA = findViewById(R.id.opt_yes_fehlstellen_grasnarbe);
        inputFehlstellenGrasnarbe_NEIN = findViewById(R.id.opt_no_fehlstellen_grasnarbe);
        inputFehlstellenGrasnarbe_UNBEKANNT = findViewById(R.id.opt_unknown_fehlstellen_grasnarbe);

        inputGehoelzbewuchs_JA = findViewById(R.id.opt_yes_gehoelzbewuchs);
        inputGehoelzbewuchs_NEIN = findViewById(R.id.opt_no_gehoelzbewuchs);
        inputGehoelzbewuchs_UNBEKANNT = findViewById(R.id.opt_unknown_gehoelzbewuchs);

        inputVisuelleEinschraenkung_JA = findViewById(R.id.opt_yes_visuelle_einschraenkung);
        inputVisuelleEinschraenkung_NEIN = findViewById(R.id.opt_no_visuelle_einschraenkung);
        inputVisuelleEinschraenkung_UNBEKANNT = findViewById(R.id.opt_unknown_visuelle_einschraenkung);

        inputGrabendeTiere_JA = findViewById(R.id.opt_yes_grabende_tiere);
        inputGrabendeTiere_NEIN = findViewById(R.id.opt_no_grabende_tiere);
        inputGrabendeTiere_UNBEKANNT = findViewById(R.id.opt_unknown_grabende_tiere);

        inputRisseMassiv_JA = findViewById(R.id.opt_yes_risse_massiv);
        inputRisseMassiv_NEIN = findViewById(R.id.opt_no_risse_massiv);
        inputRisseMassiv_UNBEKANNT= findViewById(R.id.opt_unknown_risse_massiv);

        inputSetzungenMassiv_JA = findViewById(R.id.opt_yes_setzungen_massiv);
        inputSetzungenMassiv_NEIN = findViewById(R.id.opt_no_setzungen_massiv);
        inputSetzungenMassiv_UNBEKANNT = findViewById(R.id.opt_unknown_setzungen_massiv);

        inputVerschiebungenMassiv_JA = findViewById(R.id.opt_yes_verschiebungen_massiv);
        inputVerschiebungenMassiv_NEIN = findViewById(R.id.opt_no_verschiebungen_massiv);
        inputVerschiebungenMassiv_UNBEKANNT = findViewById(R.id.opt_unknown_verschiebungen_massiv);

        inputKippungen_JA = findViewById(R.id.opt_yes_kippungen);
        inputKippungen_NEIN = findViewById(R.id.opt_no_kippungen);
        inputKippungen_UNBEKANNT = findViewById(R.id.opt_unknown_kippungen);

        inputAbplatzungen_JA = findViewById(R.id.opt_yes_abplatzungen);
        inputAbplatzungen_NEIN = findViewById(R.id.opt_no_abplatzungen);
        inputAbplatzungen_UNBEKANNT = findViewById(R.id.opt_unknown_abplatzungen);

        inputAuswaschungen_JA = findViewById(R.id.opt_yes_auswaschungen);
        inputAuswaschungen_NEIN = findViewById(R.id.opt_no_auswaschungen);
        inputAuswaschungen_UNBEKANNT = findViewById(R.id.opt_unknown_auswaschungen);

        inputWasseraustritteMassiv_JA = findViewById(R.id.opt_yes_wasseraustritte_massiv);
        inputWasseraustritteMassiv_NEIN = findViewById(R.id.opt_no_wasseraustritte_massiv);
        inputWasseraustritteMassiv_UNBEKANNT = findViewById(R.id.opt_unknown_wasseraustritte_massiv);

        inputFehlstellenMauerwerk_JA = findViewById(R.id.opt_yes_fehlstellen_mauerwerk);
        inputFehlstellenMauerwerk_NEIN = findViewById(R.id.opt_no_fehlstellen_mauerwerk);
        inputFehlstellenMauerwerk_UNBEKANNT = findViewById(R.id.opt_unknown_fehlstellen_mauerwerk);
    }

    private void SetGuiElements() {
        inputUferveraenderungen = findViewById(R.id.radio_uferveraenderungen);
        inputRutschungen = findViewById(R.id.radio_rutschungen);
        inputRisseErd = findViewById(R.id.radio_risse_erd);
        inputSetzungenErd = findViewById(R.id.radio_setzungen); //TODO setzungen_erd ? weil auch setzungen_massiv
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
    }

    /**
     * Button "Fertig"
     * --> Werte aus der GUI auslesen und speichern
     * --> die zwischengespeicherten Daten in die Datenbank einfügen
     * --> Wechseln zu Activity MainActivity
     */
    public void submitData(View view) {
        Answer uferveraenderungen = decideRadioAnswer(inputUferveraenderungen.getCheckedRadioButtonId(),R.id.opt_yes_uferveraenderungen,
                R.id.opt_unknown_uferveraenderungen,R.id.opt_no_uferveraenderungen);
        Answer rutschungen = decideRadioAnswer(inputRutschungen.getCheckedRadioButtonId(),R.id.opt_yes_rutschungen,
                R.id.opt_unknown_rutschungen,R.id.opt_no_rutschungen);
        Answer risseErd = decideRadioAnswer(inputRisseErd.getCheckedRadioButtonId(),R.id.opt_yes_risse_erd,
                R.id.opt_unknown_risse_erd,R.id.opt_no_risse_erd);
        Answer setzungen = decideRadioAnswer(inputSetzungenErd.getCheckedRadioButtonId(),R.id.opt_yes_setzungen,
                R.id.opt_unknown_setzungen,R.id.opt_no_setzungen);
        Answer hebungenErd = decideRadioAnswer(inputHebungenErd.getCheckedRadioButtonId(),R.id.opt_yes_hebungen_erd,
                R.id.opt_unknown_hebungen_erd,R.id.opt_no_hebungen_erd);
        Answer verschiebungenErd = decideRadioAnswer(inputVerschiebungenErd.getCheckedRadioButtonId(),R.id.opt_yes_verschiebungen_erd,
                R.id.opt_unknown_verschiebungen_erd,R.id.opt_no_verschiebungen_erd);
        Answer wasseraustritteErd = decideRadioAnswer(inputWasseraustritteErd.getCheckedRadioButtonId(),R.id.opt_yes_wasseraustritte_erd,
                R.id.opt_unknown_wasseraustritte_erd,R.id.opt_no_wasseraustritte_erd);
        Answer materialaustragErd = decideRadioAnswer(inputMaterialaustragErd.getCheckedRadioButtonId(),R.id.opt_yes_materialaustritt_erd,
                R.id.opt_unknown_materialaustritt_erd,R.id.opt_no_materialaustritt_erd);
        Answer erosion = decideRadioAnswer(inputErosion.getCheckedRadioButtonId(),R.id.opt_yes_erosion,
                R.id.opt_unknown_erosion,R.id.opt_no_erosion);
        Answer fehlstellenGrasnarbe = decideRadioAnswer(inputFehlstellenGrasnarbe.getCheckedRadioButtonId(),R.id.opt_yes_fehlstellen_grasnarbe,
                R.id.opt_unknown_fehlstellen_grasnarbe,R.id.opt_no_fehlstellen_grasnarbe);
        Answer gehoelzbewuchs = decideRadioAnswer(inputGehoelzbewuchs.getCheckedRadioButtonId(),R.id.opt_yes_gehoelzbewuchs,
                R.id.opt_unknown_gehoelzbewuchs,R.id.opt_no_gehoelzbewuchs);
        Answer visuelleEinschraenkungen = decideRadioAnswer(inputVisuelleEinschraenkung.getCheckedRadioButtonId(),R.id.opt_yes_visuelle_einschraenkung,
                R.id.opt_unknown_visuelle_einschraenkung,R.id.opt_no_visuelle_einschraenkung);
        Answer grabendeTiere = decideRadioAnswer(inputGrabendeTiere.getCheckedRadioButtonId(),R.id.opt_yes_grabende_tiere,
                R.id.opt_unknown_grabende_tiere,R.id.opt_no_grabende_tiere);
        Answer risseMassiv = decideRadioAnswer(inputRisseMassiv.getCheckedRadioButtonId(),R.id.opt_yes_risse_massiv,
                R.id.opt_unknown_risse_massiv,R.id.opt_no_risse_massiv);
        Answer setzungenMassiv = decideRadioAnswer(inputSetzungenMassiv.getCheckedRadioButtonId(),R.id.opt_yes_setzungen_massiv,
                R.id.opt_unknown_setzungen_massiv,R.id.opt_no_setzungen_massiv);
        Answer verschiebungenMassiv = decideRadioAnswer(inputVerschiebungenMassiv.getCheckedRadioButtonId(),R.id.opt_yes_verschiebungen_massiv,
                R.id.opt_unknown_verschiebungen_massiv,R.id.opt_no_verschiebungen_massiv);
        Answer kippungen = decideRadioAnswer(inputKippungen.getCheckedRadioButtonId(),R.id.opt_yes_kippungen,
                R.id.opt_unknown_kippungen,R.id.opt_no_kippungen);
        Answer abplatzungen = decideRadioAnswer(inputAbplatzungen.getCheckedRadioButtonId(),R.id.opt_yes_abplatzungen,
                R.id.opt_unknown_abplatzungen,R.id.opt_no_abplatzungen);
        Answer auswaschungen = decideRadioAnswer(inputAuswaschungen.getCheckedRadioButtonId(),R.id.opt_yes_auswaschungen,
                R.id.opt_unknown_auswaschungen,R.id.opt_no_auswaschungen);
        Answer wasseraustritteMassiv = decideRadioAnswer(inputWasseraustritteMassiv.getCheckedRadioButtonId(),R.id.opt_yes_wasseraustritte_massiv,
                R.id.opt_unknown_wasseraustritte_massiv,R.id.opt_no_wasseraustritte_massiv);
        Answer fehlstellenMauerwerk = decideRadioAnswer(inputFehlstellenMauerwerk.getCheckedRadioButtonId(),R.id.opt_yes_fehlstellen_mauerwerk,
                R.id.opt_unknown_fehlstellen_mauerwerk,R.id.opt_no_fehlstellen_mauerwerk);


        StauanlageHolder.getStauanlage().updateDauerhaftigkeit(
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


        if (StauanlageHolder.getStauanlage() != null) {
            if(StauanlageHolder.isStauanlageLoadedFromDB ){
                stauanlageViewModel.update(StauanlageHolder.getStauanlage()); //SQL Befehl update triggern
            }else{
                stauanlageViewModel.insert(StauanlageHolder.getStauanlage()); //SQL Befehl INSERT triggern
            }
        }else{
            Toast errorMsg = Toast.makeText(this,"konnte nicht gespeichert werden",Toast.LENGTH_LONG);
            errorMsg.show();
        }


        Intent goBackToFinishedQuestionnaires = new Intent(this, FinishedQuestionnairesActivity.class);
        Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
        startActivity(goBackToFinishedQuestionnaires);
    }
}
