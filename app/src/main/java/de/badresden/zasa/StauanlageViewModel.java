package de.badresden.zasa;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;

import de.badresden.zasa.activities.QuestionnaireAllgemeinActivity;

//Autor: Georg

/**
 * ViewModel ist der Controller der Activties. Es stellt für sie die Datenbereit und verarbeitet sie entsprechend der Interaktionen des Nutzers
 */
@SuppressWarnings("unused")
public class StauanlageViewModel extends AndroidViewModel {

	/**
	 * Die Statische Variable ist der Zwischenspeicher.
	 * In ihr werden die Antworten aus den einzelnen Fragebägen zwischen gespeichert
	 * Ist der Nutzer mit Bearbeiten fertig wird das Objekt in die Datenbank geschrieben, dannach  wird die Variable in der OnCreate Methode
	 * der MainActivity null gesetzt
	 */
	public static Stauanlage stauanlage = null; // Wir haben uns für eine statische Variable entschieden, damit jede Instanz des ViewModel zugriff auf sie hat
	//(Sollte kein Problem darstellen da die Momentant Nutzerführung (siehe Diagramm)
	// nur das Laden eines Fragebogens (Bearbeiten,Erstellen oder Hochladen) zu gleicher Zeit vorsieht
	public static Boolean stauanlageIsLoadedFromDB = Boolean.FALSE;
	private StauanlageRepository mRepository;
	private LiveData<List<StauanlageSimplyfied>> mAllStauanlagenSimplyfied; //Liste mit "vereinfachten" Stauanlagen Objekten: reicht um sie in der RecyclerView darzustellen ...
	//hält nicht soviele Daten unütz im Speicher

	public StauanlageViewModel(@NonNull Application application) {
		super(application);
		mRepository = new StauanlageRepository(application);
		mAllStauanlagenSimplyfied = mRepository.getAllStauanlagenSimplyfied();
	}

	public LiveData<List<StauanlageSimplyfied>> getAllStauanlagenSimplyfied() {
		return mAllStauanlagenSimplyfied;
	}

	// das private Feld wird gespeichert
	public void insert() {
		mRepository.insert(stauanlage);
		//mAllStauanlagenSimplyfied = mRepository.getAllStauanlagenSimplyfied();
	}

	public void loadStauanlageWith(Activity currentActivity,int primaryKey) {
        new LoadStauanlageFromDBAsyncTask(mRepository,currentActivity).execute(primaryKey);
	}

	//soll später benutzt werden um eine geladen Stauanlage zu bearbeiten und in der Datenbank zu aktualisieren
	//wird momentan nicht benutzt
	public void update() {
		mRepository.update(StauanlageViewModel.stauanlage);
	}

	//soll später benutzt werden um die Möglichkeit zu bieten alle gespeicherten Stauanlagen zu löschen
	//wird momentan noch nicht benutzt
	public void deleteAll() {
		mRepository.deleteAll();
	}

	public void createStauanlage() {
		stauanlage = new Stauanlage();
		StauanlageViewModel.stauanlageIsLoadedFromDB = Boolean.FALSE;
	}

	public void updateAllgemein(String nameDerAnlage, String geographischeLage, String gestautesGewaesser, String eigentuemerBetreiber,
								String artDesAbsperrauwerkes, Double hoeheAbsperrwerkUeberGruendung, Double stauinhaltInCbm, Double bhq1InCbmProSekunde, Double bhq2InCbmProSekunde,
								Answer betriebsvorschriftNormalfallLiegtVor, Answer betriebsvorschriftHochwasserLiegtVor, Date datumUndUhrzeitLetzteBearbeitung) {

		stauanlage.updateAllgemein(nameDerAnlage, geographischeLage, gestautesGewaesser, eigentuemerBetreiber,
				artDesAbsperrauwerkes, hoeheAbsperrwerkUeberGruendung, stauinhaltInCbm, bhq1InCbmProSekunde,
				bhq2InCbmProSekunde, betriebsvorschriftNormalfallLiegtVor, betriebsvorschriftHochwasserLiegtVor, datumUndUhrzeitLetzteBearbeitung);
	}

	public void updateTragfaehigkeit(Answer wasserseitigZuLuftseitigKleinerEinszuDrei, Answer statischeBerechnungLiegtVor) {

		stauanlage.updateTragfaehigkeit(wasserseitigZuLuftseitigKleinerEinszuDrei, statischeBerechnungLiegtVor);
	}

	public void uptdateGebrauchstauglichkeit(Answer qHWWEVonBHW1GoesserGleichBHQ1, Answer qHWEVonBHW2GroesserGleichBHQ2, Answer freiboardZurUKVonBrueckenOderStegenGroesserGleichHalbMeter,
											 Answer freibordZurDammkronePauschal, Answer nachweisNachDVWKMbl246MitImBF2Um15ProzentReduzierterWindgeschwindigkeit, String bisherigeBetriebsauffaelligkeiten,
											 Answer querschnittsreduktionDerWasserwege, Answer fehlstellenOderBeschaedigungenAnWasserwegen, Answer fehlstellenOderBeschaedigungenImTosbeckenHWE, Answer treibgutsperreUndGrobrechenUndPalisadenrechenFreiUndFunktionstuechtig,
											 Answer querschnittsreduktionImGA, Answer fehlstellenOderBeschaedigungenOderUndichtigkeitenGA, Answer fehlstellenOderBeschaedigungenImTosbeckenGA,
											 Answer schwergaengigkeitOderBlockierenDesVerschlusses, Answer messeinrichtungFunktionsfaehig) {

		stauanlage.updateGebrauchstauglichkeit(qHWWEVonBHW1GoesserGleichBHQ1, qHWEVonBHW2GroesserGleichBHQ2, freiboardZurUKVonBrueckenOderStegenGroesserGleichHalbMeter,
				freibordZurDammkronePauschal, nachweisNachDVWKMbl246MitImBF2Um15ProzentReduzierterWindgeschwindigkeit, bisherigeBetriebsauffaelligkeiten,
				querschnittsreduktionDerWasserwege, fehlstellenOderBeschaedigungenAnWasserwegen, fehlstellenOderBeschaedigungenImTosbeckenHWE, treibgutsperreUndGrobrechenUndPalisadenrechenFreiUndFunktionstuechtig,
				querschnittsreduktionImGA, fehlstellenOderBeschaedigungenOderUndichtigkeitenGA, fehlstellenOderBeschaedigungenImTosbeckenGA,
				schwergaengigkeitOderBlockierenDesVerschlusses, messeinrichtungFunktionsfaehig);
	}

	public void updateDauerhaftigkeit(Answer uferveraenderungenDesStausees, Answer rutschungen, Answer risseErdbau, Answer sichtbareSetzungenErdbaubau, Answer sichtbareHebungen, Answer sichtbareHorizontalverschiebungenErdbau,
									  Answer luftseitigeWasseraustritteErdbau, Answer materialaustragDurchSickerwasser, Answer erosionDerWasserseitigenSchutzschicht, Answer fehlstellenInDerGrasnarbeAufDemDamm,
									  Answer gehoelzbewuchsOhneZusatzquerschnitt, Answer einschraenkungVisuelleInspektionsmoeglichkeitenBewuchsLuftseitigenBoeschung, Answer grabendeTiere, Answer risseMassivbau,
									  Answer sichtbareSetzungenMassivbau, Answer sichtbareHorizontalverschiebungenMassivbau, Answer neigungsaenderungenOderKippungen, Answer abplatzungen, Answer auswaschungenOderAusbluehungen,
									  Answer luftseitigeWasseraustritteMassivbau, Answer fehlstellenImMauerwerk) {
		stauanlage.updateDauerhaftigkeit(uferveraenderungenDesStausees, rutschungen, risseErdbau, sichtbareSetzungenErdbaubau, sichtbareHebungen, sichtbareHorizontalverschiebungenErdbau,
				luftseitigeWasseraustritteErdbau, materialaustragDurchSickerwasser, erosionDerWasserseitigenSchutzschicht, fehlstellenInDerGrasnarbeAufDemDamm,
				gehoelzbewuchsOhneZusatzquerschnitt, einschraenkungVisuelleInspektionsmoeglichkeitenBewuchsLuftseitigenBoeschung, grabendeTiere, risseMassivbau,
				sichtbareSetzungenMassivbau, sichtbareHorizontalverschiebungenMassivbau, neigungsaenderungenOderKippungen, abplatzungen, auswaschungenOderAusbluehungen,
				luftseitigeWasseraustritteMassivbau, fehlstellenImMauerwerk);
	}

	/**
	 * leert den Zwischenspeicher
	 */
	public void clear() {
		stauanlage = null;
		StauanlageViewModel.stauanlageIsLoadedFromDB = null;
	}

	/**
	 * Lädt eine Stauanlage aus der Datenbank und öffnet anschließend die AllgemeinActivity
	 */
	private static class LoadStauanlageFromDBAsyncTask extends AsyncTask<Integer,Void,Stauanlage> {
		private StauanlageRepository mAsyncTaskRepository;
		private WeakReference<Activity> mActivity;

		public LoadStauanlageFromDBAsyncTask(StauanlageRepository repository, Activity activity) {
			super();
			mAsyncTaskRepository = repository;
			mActivity = new WeakReference<>(activity);
		}

		@Override
		protected Stauanlage doInBackground(Integer... integers) {
			int primaryKey = integers[0];
			return mAsyncTaskRepository.getStauanlagewith(primaryKey); //Database Querry Execution
		}

		@Override
		protected void onPostExecute(Stauanlage stauanlage) {
			super.onPostExecute(stauanlage);
			StauanlageViewModel.stauanlage = stauanlage;
			StauanlageHolder.setStauanlage(stauanlage);
			StauanlageViewModel.stauanlageIsLoadedFromDB = Boolean.TRUE;
			StauanlageHolder.isStauanlageLoadedFromDB = Boolean.TRUE;
			Intent openQuestionnaireAllgemeinActivity = new Intent(mActivity.get(), QuestionnaireAllgemeinActivity.class);
			mActivity.get().startActivity(openQuestionnaireAllgemeinActivity);
		}
	}
}
