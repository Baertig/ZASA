package de.badresden.zasa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;

import de.badresden.zasa.Answer;
import de.badresden.zasa.R;
import de.badresden.zasa.Stauanlage;
import de.badresden.zasa.StauanlageHolder;
import de.badresden.zasa.StauanlageViewModel;

import static de.badresden.zasa.HelpFunctions.decideRadioAnswer;
import static de.badresden.zasa.HelpFunctions.loadAnswerInRadioGroup;
import static de.badresden.zasa.HelpFunctions.safeParseStringToDouble;
import static de.badresden.zasa.HelpFunctions.doubleToString;

//Autor: Georg

/**
 * Activity in der die Fragen der Kategorie Allgemein beantwortet werden
 */

@SuppressWarnings("unused")
public class QuestionnaireAllgemeinActivity extends AppCompatActivity {

	private static final String LOG_TAG = QuestionnaireAllgemeinActivity.class.getSimpleName();

	private StauanlageViewModel mStauanlageViewModel;
	private Date currentDate;//Bearbeitungsdatum
	// relevante GUI-Element
	private TextInputEditText inputNameDerAnlage;
	private TextInputEditText inputGeoLage;
	private TextInputEditText inputGewaesser;
	private TextInputEditText inputEigentuemer;
	private TextInputEditText inputArtDesAbsperrbauwerkes;
	private TextInputEditText inputHoehe;
	private TextInputEditText inputStauinhalt;
	private TextInputEditText inputBHQ1;
	private TextInputEditText inputBHQ2;
	private EditText inputBHQ2Abschaetzung;
	private RadioGroup inputBetriebsvorschriftNormalbetrieb;
	private RadioGroup inputBetriebsvorschriftHochwasserfall;

	//RadioButtons
	private RadioButton BetriebsvorschriftNormalbetrieb_JA;
	private RadioButton BetriebsvorschriftNormalbetrieb_NEIN;
	private RadioButton BetriebsvorschriftNormalbetrieb_UNBEKANNT;
	private RadioButton BetriebsvorschriftHochwasserfall_JA;
	private RadioButton BetriebsvorschriftHochwasserfall_NEIN;
	private RadioButton BetriebsvorschriftHochwasserfall_UNBEKANNT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionnaire_allgemein);
		setTitle("Allgemein");
		SetGuiElements();
		SetRadioButtons();
		//setzen des ViewModels
		mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
		//das Bearbeitungsdatum setzten
		currentDate = Calendar.getInstance().getTime();
		if(StauanlageHolder.getStauanlage() != null){
			loadStauanlageInUI(StauanlageHolder.getStauanlage());
		}
	}


	private void loadStauanlageInUI(Stauanlage stauanlage) {
		inputNameDerAnlage.setText(stauanlage.nameDerAnlage);
		inputGeoLage.setText(stauanlage.geographischeLage);
		inputGewaesser.setText(stauanlage.gestautesGewaesser);
		inputEigentuemer.setText(stauanlage.eigentuemerBetreiber);
		inputArtDesAbsperrbauwerkes.setText(stauanlage.artDesAbsperrauwerkes);
		inputHoehe.setText(doubleToString(stauanlage.hoeheAbsperrwerkUeberGruendung));
		inputStauinhalt.setText(doubleToString(stauanlage.stauinhaltInCbm));
		inputBHQ1.setText(doubleToString(stauanlage.bHQ1InCbmProSekunde));
		inputBHQ2.setText(doubleToString(stauanlage.bHQ2InCbmProSekunde));
		//TODO festlegen was passiert wenn in Double Feld null drinsteht
		loadAnswerInRadioGroup(stauanlage.betriebsvorschriftNormalfallLiegtVor,
				BetriebsvorschriftNormalbetrieb_JA,
				BetriebsvorschriftNormalbetrieb_NEIN,
				BetriebsvorschriftNormalbetrieb_UNBEKANNT);
		loadAnswerInRadioGroup(stauanlage.betriebsvorschriftHochwasserLiegtVor,
				BetriebsvorschriftHochwasserfall_JA,
				BetriebsvorschriftHochwasserfall_NEIN,
				BetriebsvorschriftHochwasserfall_UNBEKANNT);
	}

	/**
	 * Button "Weiter"
	 * --> auslesen und zwischenspeichern der Daten
	 * --> Wechseln zu Activity QuestionnaireTragfaehigkeitActivity
	 */
	public void openQuestionnaireTragfaehigkeit(View view) {
		// Werte aus der GUI an Stauanlagen-Objekt an ViewModelKlasse übergeben.
		Answer BetriebsvorschriftNormalfall = decideRadioAnswer(inputBetriebsvorschriftNormalbetrieb.getCheckedRadioButtonId(), R.id.opt_yes_betriebsvorschrift_normalbetrieb, R.id.opt_unknown_betriebsvorschrift_normalbetrieb,
				R.id.opt_no_betriebsvorschrift_normalbetrieb);
		Answer BetriebsvorschriftHochwasserfall = decideRadioAnswer(inputBetriebsvorschriftHochwasserfall.getCheckedRadioButtonId(), R.id.opt_yes_betriebsvorschrift_hochwasserfall, R.id.opt_unknown_betriebsvorschrift_hochwasserfall,
				R.id.opt_no_betriebsvorschrift_hochwasserfall);
		//es wird auf nicht zulässige Werte im Feld geprüft dementsprechend wird die Variable gesetzt
		Double hoehe = safeParseStringToDouble(inputHoehe.getText().toString());
		Double Stauinhalt = safeParseStringToDouble(inputStauinhalt.getText().toString());
		Double bHQ1 = safeParseStringToDouble(inputBHQ1.getText().toString());
		Double bHQ2 = safeParseStringToDouble(inputBHQ2.getText().toString());
		//TODO Was soll passieren, wenn Eingabe in das Nummern Feld nicht gedeutet werden kann
		if (StauanlageHolder.getStauanlage() == null ) {
			Log.d(LOG_TAG, "openQuestionnaireTragfaehigkeit: Fatal Error there was no Stauanlage Object");
			return;
		}

		StauanlageHolder.updateAllgemein(
				inputNameDerAnlage.getText().toString(),
				inputGeoLage.getText().toString(),
				inputGewaesser.getText().toString(),
				inputEigentuemer.getText().toString(),
				inputArtDesAbsperrbauwerkes.getText().toString(),
				hoehe,
				Stauinhalt,
				bHQ1,
				bHQ2,
				BetriebsvorschriftNormalfall,
				BetriebsvorschriftHochwasserfall,
				currentDate
		);
		//nächste Activity oeffnen
		Intent openQuestionnaireTragfaehigkeitIntent = new Intent(this, QuestionnaireTragfaehigkeitActivity.class);
		Log.d(LOG_TAG, "Continue Button on page " + LOG_TAG + "clicked.");
		startActivity(openQuestionnaireTragfaehigkeitIntent);
	}

	/**
	 * Button "Standort"
	 * --> noch keine Funktion hinterlegt
	 * (Soll später GPS - Koordinaten des Handys liefern)
	 */
	public void chooseLocationByGps(View view) { //btn_location
		Toast toast = Toast.makeText(this, "Button ist noch nicht mit Funktionn hinterlegt", Toast.LENGTH_LONG);
		toast.show();
	}

	private void SetGuiElements() {
		inputNameDerAnlage = findViewById(R.id.answer_name_der_anlage);
		inputGeoLage = findViewById(R.id.answer_lage);
		inputGewaesser = findViewById(R.id.answer_gewaesser);
		inputEigentuemer = findViewById(R.id.answer_eigentuemer);
		inputArtDesAbsperrbauwerkes = findViewById(R.id.answer_art_des_absperrbauwerkes);
		inputHoehe = findViewById(R.id.answer_hoehe);
		inputStauinhalt = findViewById(R.id.answer_stauinhalt);
		inputBHQ1 = findViewById(R.id.answer_bhq1);
		inputBHQ2 = findViewById(R.id.answer_bhq2);
		inputBetriebsvorschriftNormalbetrieb = findViewById(R.id.radio_betriebsvorschrift_normalbetrieb);
		inputBetriebsvorschriftHochwasserfall = findViewById(R.id.radio_betriebsvorschtift_hochwasserfall);
		//
		// inputBHQ2Abschaetzung = findViewById(R.id.answer_bhq2_abschaetzung);
	}

	private void SetRadioButtons(){
		BetriebsvorschriftNormalbetrieb_JA = findViewById(R.id.opt_yes_betriebsvorschrift_normalbetrieb);
		BetriebsvorschriftNormalbetrieb_NEIN = findViewById(R.id.opt_no_betriebsvorschrift_normalbetrieb);
		BetriebsvorschriftNormalbetrieb_UNBEKANNT = findViewById(R.id.opt_unknown_betriebsvorschrift_normalbetrieb);

		BetriebsvorschriftHochwasserfall_JA = findViewById(R.id.opt_yes_betriebsvorschrift_hochwasserfall);
		BetriebsvorschriftHochwasserfall_NEIN = findViewById(R.id.opt_no_betriebsvorschrift_hochwasserfall);
		BetriebsvorschriftHochwasserfall_UNBEKANNT = findViewById(R.id.opt_unknown_betriebsvorschrift_hochwasserfall);
	}
}