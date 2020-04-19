package de.badresden.zasa.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;

import de.badresden.zasa.Answer;
import de.badresden.zasa.Fragments.gpsDisabledDialog;
import de.badresden.zasa.R;
import de.badresden.zasa.Stauanlage;
import de.badresden.zasa.StauanlageHolder;
import de.badresden.zasa.StauanlageViewModel;

import static de.badresden.zasa.functions.HelpFunctions.decideRadioAnswer;
import static de.badresden.zasa.functions.HelpFunctions.loadAnswerInRadioGroup;
import static de.badresden.zasa.functions.HelpFunctions.safeParseStringToDouble;
import static de.badresden.zasa.functions.HelpFunctions.doubleToString;

//Autor: Georg

/**
 * Activity in der die Fragen der Kategorie Allgemein beantwortet werden
 */

@SuppressWarnings("unused")
public class QuestionnaireAllgemeinActivity extends AppCompatActivity {

	private static final String LOG_TAG = QuestionnaireAllgemeinActivity.class.getSimpleName();
	private static final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 69;

	private LocationManager locationManager;
	private LocationProvider gpsProvider;
	private FusedLocationProviderClient locationProviderClient;
	private View.OnClickListener onGpsIconClickListener;
	private AppCompatActivity activity;

	private StauanlageViewModel mStauanlageViewModel;
	private Date currentDate;//Bearbeitungsdatum
	// relevante GUI-Element
	private TextInputLayout layoutNameDerAnlage;
	private TextInputEditText inputNameDerAnlage;
	private TextInputLayout layoutLongitude;
	private TextInputEditText inputLongitude;
	private TextInputLayout layoutLatidtude;
	private TextInputEditText inputLatitude;
	private TextInputEditText inputPlz;
	private TextInputEditText inputOrt;
	private TextInputEditText inputStrasseNr;
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
		activity = this;
		onGpsIconClickListener = createOnGpsIconClickListener();
		configureGuiElements();
		SetRadioButtons();
		//setzen des ViewModels
		mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
		//das Bearbeitungsdatum setzten
		currentDate = Calendar.getInstance().getTime();
		//It shouldn't be null ... but who knows ?
		if (StauanlageHolder.getStauanlage() != null) {
			loadStauanlageInUI(StauanlageHolder.getStauanlage());
		} else {
			//TODO ERROR MSG
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if(requestCode == REQUEST_PERMISSION_ACCESS_FINE_LOCATION){
			// If request is cancelled, the result arrays are empty.
			if (grantResults.length > 0
					&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				// permission was granted, yay
				//passing view just because it is needed ...
				onGpsIconClickListener.onClick(layoutLatidtude);
			} else {
				// permission denied, boo
				Toast.makeText(activity, "Erlaubnis wird benötigt, um die GPS-Koordinaten auszulesen",
						Toast.LENGTH_LONG).show();
			}
			return;
		}
	}

	private void loadStauanlageInUI(Stauanlage stauanlage) {
		if (stauanlage.nameDerAnlage != null) { //So the TextWatcher isn't called right at the beginning
			inputNameDerAnlage.setText(stauanlage.nameDerAnlage);
		}
		inputLongitude.setText(doubleToString(stauanlage.longitude));
		inputLatitude.setText(doubleToString(stauanlage.latitude));
		inputPlz.setText(stauanlage.plz);
		inputOrt.setText(stauanlage.ort);
		inputStrasseNr.setText(stauanlage.strasseNr);
		inputGewaesser.setText(stauanlage.gestautesGewaesser);
		inputEigentuemer.setText(stauanlage.eigentuemerBetreiber);
		inputArtDesAbsperrbauwerkes.setText(stauanlage.artDesAbsperrauwerkes);
		inputHoehe.setText(doubleToString(stauanlage.hoeheAbsperrwerkUeberGruendung));
		inputStauinhalt.setText(doubleToString(stauanlage.stauinhaltInCbm));
		inputBHQ1.setText(doubleToString(stauanlage.bHQ1InCbmProSekunde));
		inputBHQ2.setText(doubleToString(stauanlage.bHQ2InCbmProSekunde));
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
		if (inputNameDerAnlage.getText().toString().length() == 0) {
			layoutNameDerAnlage.setError(getString(R.string.name_der_anlage_error_text));
			inputNameDerAnlage.requestFocus();

			return;
		}
		// Werte aus der GUI an Stauanlagen-Objekt übergeben.
		Answer BetriebsvorschriftNormalfall = decideRadioAnswer(inputBetriebsvorschriftNormalbetrieb.getCheckedRadioButtonId(), R.id.opt_yes_betriebsvorschrift_normalbetrieb, R.id.opt_unknown_betriebsvorschrift_normalbetrieb,
				R.id.opt_no_betriebsvorschrift_normalbetrieb);
		Answer BetriebsvorschriftHochwasserfall = decideRadioAnswer(inputBetriebsvorschriftHochwasserfall.getCheckedRadioButtonId(), R.id.opt_yes_betriebsvorschrift_hochwasserfall, R.id.opt_unknown_betriebsvorschrift_hochwasserfall,
				R.id.opt_no_betriebsvorschrift_hochwasserfall);
		//es wird auf nicht zulässige Werte im Feld geprüft dementsprechend wird die Variable gesetzt
		Double longitude = safeParseStringToDouble(inputLongitude.getText().toString());
		Double latitude = safeParseStringToDouble(inputLatitude.getText().toString());
		Double hoehe = safeParseStringToDouble(inputHoehe.getText().toString());
		Double Stauinhalt = safeParseStringToDouble(inputStauinhalt.getText().toString());
		Double bHQ1 = safeParseStringToDouble(inputBHQ1.getText().toString());
		Double bHQ2 = safeParseStringToDouble(inputBHQ2.getText().toString());
		if (StauanlageHolder.getStauanlage() == null) {
			Log.d(LOG_TAG, "openQuestionnaireTragfaehigkeit: Fatal Error there was no Stauanlage Object");
			return;
		}

		StauanlageHolder.getStauanlage().updateAllgemein(
				inputNameDerAnlage.getText().toString(),
				longitude,
				latitude,
				inputPlz.getText().toString(),
				inputOrt.getText().toString(),
				inputStrasseNr.getText().toString(),
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




	private void configureGuiElements() {
		layoutNameDerAnlage = findViewById(R.id.layout_name_der_anlage);
		inputNameDerAnlage = findViewById(R.id.answer_name_der_anlage);
		inputNameDerAnlage.addTextChangedListener(createTextWatcherForEmptyText());
		layoutLongitude = findViewById(R.id.layout_longitude);
		layoutLongitude.setEndIconOnClickListener(onGpsIconClickListener);
		inputLongitude = findViewById(R.id.answer_longitude);
		layoutLatidtude = findViewById(R.id.layout_latitude);
		layoutLatidtude.setEndIconOnClickListener(onGpsIconClickListener);
		inputLatitude = findViewById(R.id.answer_latitude);
		inputPlz = findViewById(R.id.answer_plz);
		inputOrt = findViewById(R.id.answer_stadt);
		inputStrasseNr = findViewById(R.id.answer_strasseNr);
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

	private void SetRadioButtons() {
		BetriebsvorschriftNormalbetrieb_JA = findViewById(R.id.opt_yes_betriebsvorschrift_normalbetrieb);
		BetriebsvorschriftNormalbetrieb_NEIN = findViewById(R.id.opt_no_betriebsvorschrift_normalbetrieb);
		BetriebsvorschriftNormalbetrieb_UNBEKANNT = findViewById(R.id.opt_unknown_betriebsvorschrift_normalbetrieb);

		BetriebsvorschriftHochwasserfall_JA = findViewById(R.id.opt_yes_betriebsvorschrift_hochwasserfall);
		BetriebsvorschriftHochwasserfall_NEIN = findViewById(R.id.opt_no_betriebsvorschrift_hochwasserfall);
		BetriebsvorschriftHochwasserfall_UNBEKANNT = findViewById(R.id.opt_unknown_betriebsvorschrift_hochwasserfall);
	}

	/**
	 *
	 * @return TextWatcher der die Anzeige eines Errors Triggerd falls nichts im Input Feld steht
	 */
	private TextWatcher createTextWatcherForEmptyText() {
		return new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() == 0) {
					layoutNameDerAnlage.setError(getString(R.string.name_der_anlage_error_text));
				} else {
					if (layoutNameDerAnlage.getError() != null) {
						layoutNameDerAnlage.setError(null);
					}
				}
			}
		};
	}

	/**
	 * Button "Standort"
	 * --> noch keine Funktion hinterlegt
	 * (Soll später GPS - Koordinaten des Handys liefern)
	 */
	private View.OnClickListener createOnGpsIconClickListener() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO activate Play Services or something... it only works when i have opend Google Maps
				//TODO Request High Accuracy with LocationSettings
				//TODO delete locationManager and locationProvider
				locationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
				locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
				final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
				if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
						!= PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(activity,
							new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
							REQUEST_PERMISSION_ACCESS_FINE_LOCATION);
					return;
				}
				if (gpsEnabled) {
					locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
						@Override
						public void onSuccess(Location location) {
							if (location != null) {
								inputLongitude.setText(doubleToString(location.getLongitude()));
								inputLatitude.setText(doubleToString(location.getLatitude()));
							}else{
								Toast.makeText(activity,"GPS Koordinaten konnten nicht ermittelt werden",
										Toast.LENGTH_LONG).show();
							}
						}
					});
				}else{
					DialogFragment gpsDisabledDialog = new gpsDisabledDialog();
					gpsDisabledDialog.show(getSupportFragmentManager(),"gpsDisabledDialog");
				}
			}
		};
	}
}