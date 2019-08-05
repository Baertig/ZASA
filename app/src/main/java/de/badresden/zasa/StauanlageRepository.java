package de.badresden.zasa;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StauanlageRepository {
	private StauanlageDao mStauanlageDao;
	private LiveData<List<Stauanlage>> mAllStauanlagen;
	private LiveData<List<StauanlageSimplyfied>> mAllStauanlagenSimplyfied;

	public StauanlageRepository(Application application) {
		StauanlageRoomDatabase db = StauanlageRoomDatabase.getDatabase(application);
		mStauanlageDao = db.stauanlageDao();
		mAllStauanlagen = mStauanlageDao.getAllStauanlagen();
		mAllStauanlagenSimplyfied = mStauanlageDao.getAllStauanlagenSimplyfied();
	}

	LiveData<List<Stauanlage>> getAllStauanlagen(){
		return mAllStauanlagen;
	}

	LiveData<List<StauanlageSimplyfied>> getAllStauanlagenSimplyfied(){
		return mAllStauanlagenSimplyfied;
	}

	public void insert(Stauanlage stauanlage){
		new insertAsyncTask(mStauanlageDao).execute(stauanlage);
	}
	//FIXME Georg: im Dao können mehrer Stauanlagen Objekte übergeben werden
	public void update(Stauanlage stauanlage){
		new updateAsyncTask(mStauanlageDao).execute(stauanlage);
	}
	//AsyncTask to insert Object in Database
	private static class insertAsyncTask extends AsyncTask<Stauanlage,Void,Void>{
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

	//AsyncTask to Update Objects in Database
	//it is only possible to update one Object simultaneously ... should be enough
	private static class updateAsyncTask extends AsyncTask<Stauanlage,Void,Void>{
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
}
