package de.badresden.zasa;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Stauanlage.class},version = 1)
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
							.build();
				}
			}
		}
		return INSTANCE;
	}
}
