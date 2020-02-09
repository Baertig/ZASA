package de.badresden.zasa;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SymbolTable;
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

	public void loadStauanlageWith(Activity currentActivity,int primaryKey) {
        new LoadStauanlageFromDBAsyncTask(mRepository,currentActivity).execute(primaryKey);
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
			StauanlageHolder.setStauanlage(stauanlage);
			StauanlageHolder.isStauanlageLoadedFromDB = Boolean.TRUE;
			Intent openQuestionnaireAllgemeinActivity = new Intent(mActivity.get(), QuestionnaireAllgemeinActivity.class);
			mActivity.get().startActivity(openQuestionnaireAllgemeinActivity);
		}
	}
}
