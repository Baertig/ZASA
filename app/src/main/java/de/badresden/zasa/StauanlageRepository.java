package de.badresden.zasa;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

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

    public Stauanlage getStauanlagewith(int primaryKey) {
        return mStauanlageDao.selectStauanlageWith(primaryKey);
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

    private static class deleteStauanlageAsyncTask extends AsyncTask<Stauanlage, Void, Void> {
        private StauanlageDao mAsyncTaskDao;

        deleteStauanlageAsyncTask(StauanlageDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Stauanlage... stauanlagen) {
            mAsyncTaskDao.delete(stauanlagen[0]);
            return null;
        }
    }
}
