package de.badresden.zasa;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Stauanlage.class},version = 3)
@TypeConverters({DateTypeConverter.class,AnswerTypeConverter.class})
public abstract class StauanlageRoomDatabase extends RoomDatabase {

	public abstract StauanlageDao stauanlageDao();
	private static StauanlageRoomDatabase INSTANCE;
	//Instance der Datenbank wird nur dann erzeugt wenn keine andere existiert
	public static StauanlageRoomDatabase getDatabase(final Context context){
		if (INSTANCE == null){
			synchronized (StauanlageRoomDatabase.class){
				if (INSTANCE == null){
					//create Database here
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
							StauanlageRoomDatabase.class,"stauanlage_database")
							// Migration is not nessacary yet
							.fallbackToDestructiveMigration()
							.addCallback(sRoomDataBaseCallback)
							.build();
				}
			}
		}
		return INSTANCE;
	}
	//FIXME Georg: nur zum Testen
	private static RoomDatabase.Callback sRoomDataBaseCallback =
			new RoomDatabase.Callback(){
				@Override
				public void onOpen(@NonNull SupportSQLiteDatabase db) {
					super.onOpen(db);
					new PopulateDbAsync(INSTANCE).execute();
				}
			};

	private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{
		private final StauanlageDao mStauanlageDao;
		private Stauanlage stauanlageEins = new Stauanlage();
		private Stauanlage stauanlageZwei = new Stauanlage();
		Stauanlage[] stauanlagen = {stauanlageEins, stauanlageZwei};

		PopulateDbAsync(StauanlageRoomDatabase db){
			mStauanlageDao = db.stauanlageDao();
		}

		@Override
		protected Void doInBackground(Void... voids) {
			stauanlagen[0].nameDerAnlage = "Staudamm 1";
			stauanlagen[1].nameDerAnlage = "Staudamm 2";
			mStauanlageDao.deleteAll();
			for(int i = 0; i < stauanlagen.length; i++){
				mStauanlageDao.insert(stauanlagen[i]);
			}
			return null;
		}
	}
}
