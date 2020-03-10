package de.badresden.zasa;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.lang.ref.WeakReference;
import java.util.List;

import de.badresden.zasa.activities.QuestionnaireAllgemeinActivity;

import static de.badresden.zasa.PrintStauanlagePDFFunctions.CREATE_FILE;
import static de.badresden.zasa.PrintStauanlagePDFFunctions.createOpenFileIntent;

//Autor: Georg

/**
 * Das Repository regelt die Datenbank Interaktion der App
 * Dafür werden die SQL - Befehle des StauanlagenDaos in einem anderen neu erzeugten Thread(AsyncTask) ausgeführt
 */
@SuppressWarnings("WeakerAccess")
public class StauanlageRepository {
    private StauanlageDao mStauanlageDao;
    private LiveData<List<StauanlageSimplyfied>> mAllStauanlagenSimplyfied;

    public StauanlageRepository(Application application) {
        StauanlageRoomDatabase db = StauanlageRoomDatabase.getDatabase(application);
        mStauanlageDao = db.stauanlageDao();
        mAllStauanlagenSimplyfied = mStauanlageDao.getAllStauanlagenSimplyfied();
    }

    public LiveData<List<StauanlageSimplyfied>> getAllStauanlagenSimplyfied() {
        return mAllStauanlagenSimplyfied;
    }
    //TODO Async Task muss in Repository gestartet werden und nicht im ViewModel
    public void loadStauanlageForEdit(Activity currentActivity, int primaryKey) {
        new LoadStauanlageFromDBForEditAsyncTask(mStauanlageDao,currentActivity).execute(primaryKey);
    }


    public void insert(Stauanlage stauanlage) {
        new insertAsyncTask(mStauanlageDao).execute(stauanlage);
    }

    public void update(Stauanlage stauanlage) {
        new updateAsyncTask(mStauanlageDao).execute(stauanlage);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mStauanlageDao).execute();
    }

    public void deleteStauanlage(Stauanlage stauanlage){
        new deleteStauanlageAsyncTask(mStauanlageDao).execute(stauanlage.primaryKey);
    }
    public void deleteStauanlage(StauanlageSimplyfied stauanlageSimple){
        new deleteStauanlageAsyncTask(mStauanlageDao).execute(stauanlageSimple.primaryKey);
    }

    public void loadStauanlageForPrint(Activity activity, int primaryKey){
        new LoadStauanlageForPrintAsyncTask(mStauanlageDao,activity).execute(primaryKey);
    }

    //AsyncTask um ein Stauanlagen Objekt(Antworten eines Fragebogens) in der Datenbank zu speichern
    private static class insertAsyncTask extends AsyncTask<Stauanlage, Void, Void> {
        private StauanlageDao mAsyncTaskDao;

        insertAsyncTask(StauanlageDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Stauanlage... stauanlagen) {
            mAsyncTaskDao.insert(stauanlagen[0]);
            return null;
        }
    }

    //AsyncTask um eine Stauanlage in der Datenbank zu updaten
    //Soll ausgeführt werden wenn der Nutzer eine Stauanlage bearbeitet
    // --> Die Funktion zum Bearbeiten ist momentan noch nicht vollständig implementiert
    private static class updateAsyncTask extends AsyncTask<Stauanlage, Void, Void> {
        private StauanlageDao mAsyncTaskDao;

        updateAsyncTask(StauanlageDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Stauanlage... stauanlagen) {
            mAsyncTaskDao.update(stauanlagen[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private StauanlageDao mAsyncTaskDao;

        deleteAllAsyncTask(StauanlageDao dao) {
            this.mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteStauanlageAsyncTask extends AsyncTask<Integer, Void, Void> {
        private StauanlageDao mAsyncTaskDao;

        deleteStauanlageAsyncTask(StauanlageDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Integer... primaryKeys) {
            mAsyncTaskDao.delete(primaryKeys[0]);
            return null;
        }
    }

    //TODO LoadStauanlageAsyncTask anlgegen von dem die anderen ableiten und nur was anderes in onPostExecute ausführen
    /**
     * Lädt eine Stauanlage aus der Datenbank und öffnet anschließend die AllgemeinActivity
     */
    private static class LoadStauanlageFromDBForEditAsyncTask extends AsyncTask<Integer,Void,Stauanlage> {
        private StauanlageDao mAsyncTaskDao;
        private WeakReference<Activity> mActivity;

        public LoadStauanlageFromDBForEditAsyncTask(StauanlageDao dao, Activity activity) {
            super();
            mAsyncTaskDao = dao;
            mActivity = new WeakReference<>(activity);
        }

        @Override
        protected Stauanlage doInBackground(Integer... integers) {
            int primaryKey = integers[0];
            return mAsyncTaskDao.selectStauanlageWith(primaryKey); //Database Querry Execution
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

    private static class LoadStauanlageForPrintAsyncTask extends AsyncTask<Integer,Void,Stauanlage>{
        private StauanlageDao mAsyncTaskDao;
        private WeakReference<Activity> mActivity;

        public LoadStauanlageForPrintAsyncTask(StauanlageDao dao, Activity activity){
            super();
            mAsyncTaskDao = dao;
            mActivity = new WeakReference<>(activity);
        }

        @Override
        protected Stauanlage doInBackground(Integer... integers) {
            int primaryKey = integers[0];
            return mAsyncTaskDao.selectStauanlageWith(primaryKey); //Database Querry Execution
        }

        @Override
        protected void onPostExecute(Stauanlage stauanlage) {
            super.onPostExecute(stauanlage);
            StauanlageHolder.setStauanlage(stauanlage);
            StauanlageHolder.isStauanlageLoadedFromDB = Boolean.TRUE;
            Uri uri = Uri.parse("file:///");
            Intent intent = createOpenFileIntent(uri, stauanlage.nameDerAnlage, stauanlage.datumUndUhrzeitLetzteBearbeitung);
            mActivity.get().startActivityForResult(intent, CREATE_FILE);
        }
    }
}
