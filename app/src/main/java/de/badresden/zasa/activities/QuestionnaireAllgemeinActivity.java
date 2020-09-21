package de.badresden.zasa.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.badresden.zasa.Answer;
import de.badresden.zasa.Hoehenbezugssysteme;
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
	private static final int REQUEST_PERMISSION_ACCESS_LOCATION = 69;
	private static final int REQUEST_CHECK_SETTINGS = 420;
	private static final ArrayList<String> HOEHENBEZUGSSYSTEME = new ArrayList<String>(){
		{
			add(String.valueOf(Hoehenbezugssysteme.HN));
			add(String.valueOf(Hoehenbezugssysteme.NHN92));
			add(String.valueOf(Hoehenbezugssysteme.NHN2016));
			add(String.valueOf(Hoehenbezugssysteme.NHN2016));
		}
	};

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
	private TextInputEditText inputEinzugsgebiet;
	private TextInputEditText inputEigentuemer;
	private TextInputEditText inputArtDesAbsperrbauwerkes;
	private TextInputEditText inputHoeheGruendung;
	private Spinner inputHoehenbezugssystemHoheGruendung;
	private TextInputEditText inputHoeheGelaende;
	private Spinner inputHoehenbezugssystemHoheGelaende;
	private TextInputEditText inputHoeheOberkanteKrone;
	private Spinner inputHoehenbezugssystemOberkanteKrone;
	private TextInputEditText inputTiefsterPunktGelaende;
	private Spinner inputHoehenbezugssystemTiefsterPunktGelaende;
	private TextInputEditText inputKronenbreite;
	private CheckBox inputBermeVorhanden;
	private TextInputLayout layoutBermeHoehe;
	private TextInputEditText inputBermeHoehe;
	private TextInputLayout layoutBermeBreite;
	private TextInputEditText inputBermeBreite;
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

	//Checkbox variables
	private Boolean bermeVorhanden;
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
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_CHECK_SETTINGS && resultCode == Activity.RESULT_OK){
			//Settings are satisfied ... i hope
			// ... passing view because it is needed
			onGpsIconClickListener.onClick(layoutLatidtude);
		}else if(requestCode == REQUEST_CHECK_SETTINGS){
			//Required Settings were not applied
			Toast.makeText(activity, "ohne die Benötigten Einstellungen können die GPS-Daten nicht ausgelesen werden",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		activity = this;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if(requestCode == REQUEST_PERMISSION_ACCESS_LOCATION){
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
		inputEinzugsgebiet.setText(doubleToString(stauanlage.einzugsgebietDerAnlageInsqkm));
		inputEigentuemer.setText(stauanlage.eigentuemerBetreiber);
		inputArtDesAbsperrbauwerkes.setText(stauanlage.artDesAbsperrauwerkes);
		inputHoeheGruendung.setText(doubleToString(stauanlage.hoeheAbsperrwerkUeberGruendung));
		inputHoehenbezugssystemHoheGruendung.setSelection(HOEHENBEZUGSSYSTEME.indexOf(
				stauanlage.hoeheAbsperrwerkUeberGruendungHoehenbezugssystem.toString()));
		inputHoeheGelaende.setText(doubleToString(stauanlage.hoeheAbsperrwerkUeberGelaende));
		inputHoehenbezugssystemHoheGelaende.setSelection(HOEHENBEZUGSSYSTEME.indexOf(
				stauanlage.hoeheAbsperrwerkUeberGelaendeHoehenbezugssystem.toString()));
		inputHoeheOberkanteKrone.setText(doubleToString(stauanlage.hoeheAbsperrwerkOberkanteKrone));
		inputHoehenbezugssystemOberkanteKrone.setSelection(HOEHENBEZUGSSYSTEME.indexOf(
				stauanlage.hoeheAbsperrwerkOberkanteKroneHoehenbezugssystem.toString()));
		inputTiefsterPunktGelaende.setText(doubleToString(stauanlage.hoeheTiefsterPunktImGelaendeLuftseite));
		inputHoehenbezugssystemTiefsterPunktGelaende.setSelection(HOEHENBEZUGSSYSTEME.indexOf(
				stauanlage.hoeheTiefsterPunktImGelaendeLuftseiteHoehenbezugssystem.toString()));
		inputKronenbreite.setText(doubleToString(stauanlage.kronenbreiteInm));
		inputBermeVorhanden.setChecked(stauanlage.bermeVorhanden);
		setVisibilityOfBermeBreiteAndHoeheFields(stauanlage.bermeVorhanden);
		if (stauanlage.bermeVorhanden) {
			inputBermeHoehe.setText(doubleToString(stauanlage.bermeHoeheInm));
			inputBermeBreite.setText(doubleToString(stauanlage.bermeBreiteInm));
		}
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
		Double einzugsgebiet = safeParseStringToDouble(inputEinzugsgebiet.getText().toString());
		Double hoeheGruendung = safeParseStringToDouble(inputHoeheGruendung.getText().toString());
		Double hoeheGelaende = safeParseStringToDouble(inputHoeheGelaende.getText().toString());
		Double hoeheOberkanteKrone = safeParseStringToDouble(inputHoeheOberkanteKrone.getText().toString());
		Double hoeheTiefsterPunkt = safeParseStringToDouble(inputTiefsterPunktGelaende.getText().toString());
		Double Stauinhalt = safeParseStringToDouble(inputStauinhalt.getText().toString());
		Double bHQ1 = safeParseStringToDouble(inputBHQ1.getText().toString());
		Double bHQ2 = safeParseStringToDouble(inputBHQ2.getText().toString());
		Double kronenbreite =  safeParseStringToDouble(inputKronenbreite.getText().toString());
		Double bermenHoehe = safeParseStringToDouble(inputBermeHoehe.getText().toString());
		Double bermenBreite = safeParseStringToDouble(inputBermeBreite.getText().toString());
		Hoehenbezugssysteme hoehenbezugssystemeHoheGruendung = Hoehenbezugssysteme.valueOf(
				inputHoehenbezugssystemHoheGruendung.getSelectedItem().toString());
		Hoehenbezugssysteme hoehenbezugssystemHoeheGelaende = Hoehenbezugssysteme.valueOf(
				inputHoehenbezugssystemHoheGruendung.getSelectedItem().toString());
		Hoehenbezugssysteme hoehenbezugssystemHoeheOberkanteKrone = Hoehenbezugssysteme.valueOf(
				inputHoehenbezugssystemOberkanteKrone.getSelectedItem().toString());
		Hoehenbezugssysteme hoehenbezugssystemHoeheTiefsterPunkt = Hoehenbezugssysteme.valueOf(
				inputHoehenbezugssystemTiefsterPunktGelaende.getSelectedItem().toString());
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
				einzugsgebiet,
				inputEigentuemer.getText().toString(),
				inputArtDesAbsperrbauwerkes.getText().toString(),
				hoeheGruendung,
				hoehenbezugssystemeHoheGruendung,
				hoeheGelaende,
				hoehenbezugssystemHoeheGelaende,
				hoeheOberkanteKrone,
				hoehenbezugssystemHoeheOberkanteKrone,
				hoeheTiefsterPunkt,
				hoehenbezugssystemHoeheTiefsterPunkt,
				kronenbreite,
				this.inputBermeVorhanden.isChecked(),
				bermenHoehe,
				bermenBreite,
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
		inputEinzugsgebiet = findViewById(R.id.answer_einzugsgebiet);
		inputEigentuemer = findViewById(R.id.answer_eigentuemer);
		inputArtDesAbsperrbauwerkes = findViewById(R.id.answer_art_des_absperrbauwerkes);
		inputHoeheGruendung = findViewById(R.id.answer_hoehe_ueber_gruendung);
		inputHoehenbezugssystemHoheGruendung = findViewById(R.id.bezugssystem_hoehe_ueber_gruendung);
		inputHoeheGelaende = findViewById(R.id.answer_hoehe_ueber_gelaende);
		inputHoehenbezugssystemHoheGelaende = findViewById(R.id.bezugssystem_hoehe_ueber_gelaende);
		inputHoeheOberkanteKrone = findViewById(R.id.answer_hoehe_oberkante_krone);
		inputHoehenbezugssystemOberkanteKrone = findViewById(R.id.bezugssystem_hoehe_oberkante_krone);
		inputTiefsterPunktGelaende = findViewById(R.id.answer_hoehe_tiefster_punkt);
		inputHoehenbezugssystemTiefsterPunktGelaende = findViewById(R.id.bezugssystem_hoehe_tiefster_punkt);
		inputKronenbreite = findViewById(R.id.answer_kronenbreite);
		inputBermeVorhanden = findViewById(R.id.checkbox_berme_vorhanden);
		layoutBermeHoehe = findViewById(R.id.layout_berme_hoehe);
		inputBermeHoehe = findViewById(R.id.answer_berme_hoehe);
		layoutBermeBreite = findViewById(R.id.layout_berme_breite);
		inputBermeBreite = findViewById(R.id.answer_berme_breite);
		inputStauinhalt = findViewById(R.id.answer_stauinhalt);
		inputBHQ1 = findViewById(R.id.answer_bhq1);
		inputBHQ2 = findViewById(R.id.answer_bhq2);
		inputBetriebsvorschriftNormalbetrieb = findViewById(R.id.radio_betriebsvorschrift_normalbetrieb);
		inputBetriebsvorschriftHochwasserfall = findViewById(R.id.radio_betriebsvorschtift_hochwasserfall);
		//TODO set List with possible answers for hohenbezugssystem ...
		//
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_item, HOEHENBEZUGSSYSTEME);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		inputHoehenbezugssystemHoheGruendung.setAdapter(adapter);
		inputHoehenbezugssystemHoheGelaende.setAdapter(adapter);
		inputHoehenbezugssystemOberkanteKrone.setAdapter(adapter);
		inputHoehenbezugssystemTiefsterPunktGelaende.setAdapter(adapter);

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
	 */
	private View.OnClickListener createOnGpsIconClickListener() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Check Permissions
				if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
						!= PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(activity,
							new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
							REQUEST_PERMISSION_ACCESS_LOCATION);
					return;
				}
				if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
						!= PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(activity,
							new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
							REQUEST_PERMISSION_ACCESS_LOCATION);
					return;
				}

				final LocationRequest locationRequest = LocationRequest.create();
				locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); //Request GPS - Data
				LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
						.addLocationRequest(locationRequest);
				SettingsClient client = LocationServices.getSettingsClient(activity);
				client.checkLocationSettings(builder.build()).addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
					@Override
					public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
						locationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
						//persmission is checked already at begin of this method
						locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
							@Override
							public void onSuccess(Location location) {
								if (location != null) {
									inputLongitude.setText(doubleToString(location.getLongitude()));
									inputLatitude.setText(doubleToString(location.getLatitude()));
								} else {
									Toast.makeText(activity, "GPS Koordinaten konnten nicht ermittelt werden",
											Toast.LENGTH_LONG).show();
								}
							}
						});
					}
				}).addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						if (e instanceof ResolvableApiException) {
							// Location settings are not satisfied, but this can be fixed
							// by showing the user a dialog.
							try {
								// Show the dialog by calling startResolutionForResult(),
								// and check the result in onActivityResult().
								ResolvableApiException resolvable = (ResolvableApiException) e;
								resolvable.startResolutionForResult(QuestionnaireAllgemeinActivity.this,
										REQUEST_CHECK_SETTINGS);
							} catch (IntentSender.SendIntentException sendEx) {
								// Ignore the error.
							}
						}
					}
				});

			}
		};
	}

	public void onCheckboxClicked(View view) {

		boolean checked = ((CheckBox) view).isChecked();
		//Check which checkbox was clicked
		switch (view.getId()){
			case R.id.checkbox_berme_vorhanden:
				// handle bermen check box clicked an handle visiblility of the elements
				this.setVisibilityOfBermeBreiteAndHoeheFields(checked);
				if(!checked){
					//if it isn't checked the fields should be empty 
					this.inputBermeHoehe.setText("");
					this.inputBermeBreite.setText("");
				}
				break;
		}
	}
	private void setVisibilityOfBermeBreiteAndHoeheFields(Boolean bermeVorhanden){
		if(bermeVorhanden){
			this.layoutBermeHoehe.setVisibility(View.VISIBLE);
			this.layoutBermeBreite.setVisibility(View.VISIBLE);
		}else{
			this.layoutBermeHoehe.setVisibility(View.GONE);
			this.layoutBermeBreite.setVisibility(View.GONE);
		}
	}
}