package de.badresden.zasa;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//Autor: Georg

/**
 * ViewModel ist der Controller der Activties. Es stellt für sie die Datenbereit und verarbeitet sie entsprechend der Interaktionen des Nutzers
 */
@SuppressWarnings("unused")
public class StauanlageViewModel extends AndroidViewModel {


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
	public void insert(Stauanlage stauanlage) {
		mRepository.insert(stauanlage);
		//mAllStauanlagenSimplyfied = mRepository.getAllStauanlagenSimplyfied();
	}

	/**
	 *
	 * @param currentActivity is used to start an intend to the Allgemein-Page
	 * @param primaryKey Primary Key of Stauanlage Object
	 */
	public void loadStauanlageForEdit(Activity currentActivity, int primaryKey) {
        mRepository.loadStauanlageForEdit(currentActivity, primaryKey);
	}

	/**
	 *
	 * @param currentActivity is used to Start an Activity, where the users chooses a destination for the file. It triggers startActivityForResult()
	 * @param primaryKey Primary Key of Stauanlage Object
	 */
	public void loadStauanlageForPrint(Activity currentActivity, int primaryKey){
		mRepository.loadStauanlageForPrint(currentActivity,primaryKey);
	}

	//soll später benutzt werden um eine geladen Stauanlage zu bearbeiten und in der Datenbank zu aktualisieren
	//wird momentan nicht benutzt
	public void update(Stauanlage stauanlage) {
		mRepository.update(stauanlage);
	}

	//soll später benutzt werden um die Möglichkeit zu bieten alle gespeicherten Stauanlagen zu löschen
	//wird momentan noch nicht benutzt
	public void deleteAll() {
		mRepository.deleteAll();
	}

	public void deleteStauanlage(Stauanlage stauanlage){
		mRepository.deleteStauanlage(stauanlage);
	}
	public void deleteStauanlage(StauanlageSimplyfied stauanlageSimple){
		mRepository.deleteStauanlage(stauanlageSimple);
	}

}
