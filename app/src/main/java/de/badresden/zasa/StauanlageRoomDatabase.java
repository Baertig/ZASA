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

@Database(entities = {Stauanlage.class},version = 1)
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
							// Migration is not nessacary yet, only version 1 exists
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
		Stauanlage[] stauanlagen = {new Stauanlage("Ernst"),
				new Stauanlage("Peter")};
		PopulateDbAsync(StauanlageRoomDatabase db){
			mStauanlageDao = db.stauanlageDao();
		}

		@Override
		protected Void doInBackground(Void... voids) {
			mStauanlageDao.deleteAll();

			for(int i = 0; i < stauanlagen.length; i++){
				mStauanlageDao.insert(stauanlagen[i]);
			}
			return null;
		}
	}
}
