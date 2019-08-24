package de.badresden.zasa;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class StauanlageRepository {
	private StauanlageDao mStauanlageDao;
	private LiveData<List<Stauanlage>> mAllStauanlagen;
	private LiveData<List<StauanlageSimplyfied>> mAllStauanlagenSimplyfied;
	private List<Stauanlage> stauanlageFromNameAndDate;

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

	public void update(Stauanlage stauanlage){
		new updateAsyncTask(mStauanlageDao).execute(stauanlage);
	}
	public List<Stauanlage> getStauanlageWith(String nameDerAnlage, Date datumUndUhrzeitLetzteBearbeitung){
		new getStauanlageWithTask(mStauanlageDao,this,datumUndUhrzeitLetzteBearbeitung).execute(nameDerAnlage);
		return this.stauanlageFromNameAndDate;
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
	private static class getStauanlageWithTask extends AsyncTask<String,Void,List<Stauanlage>>{
		private StauanlageDao mAsyncTaskDao;
		private StauanlageRepository mStauanlageRepository;
		private Date datumUndUhrzeitLetzteBearbeitung;

		public getStauanlageWithTask(StauanlageDao mAsyncTaskDao, StauanlageRepository mStauanlageRepository, Date datumUndUhrzeitLetzteBearbeitung) {
			this.mAsyncTaskDao = mAsyncTaskDao;
			this.mStauanlageRepository = mStauanlageRepository;
			this.datumUndUhrzeitLetzteBearbeitung = datumUndUhrzeitLetzteBearbeitung;
		}

		@Override
		protected void onPostExecute(List<Stauanlage> stauanlagen) {
			super.onPostExecute(stauanlagen);
			mStauanlageRepository.stauanlageFromNameAndDate = stauanlagen;
		}

		@Override
		protected List<Stauanlage> doInBackground(String... strings) {
			return mAsyncTaskDao.getStauanlagenWith(strings[0],datumUndUhrzeitLetzteBearbeitung);
		}
	}
}
