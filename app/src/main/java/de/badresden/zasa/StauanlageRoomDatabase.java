package de.badresden.zasa;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import de.badresden.zasa.converters.AnswerTypeConverter;
import de.badresden.zasa.converters.DateTypeConverter;

//Autor: Georg

/**
 * RoomDatabase der App
 * mit einer statischen Methode die sicherstellt das eine Instance der RoomDatabase nur dann erzeugt wird,
 * wenn es noch keine gibt, ansonsten wird immer die vorhandene zurückgegeben
 */
@SuppressWarnings("WeakerAccess")
@Database(entities = {Stauanlage.class}, version = 4)
@TypeConverters({DateTypeConverter.class, AnswerTypeConverter.class})
public abstract class StauanlageRoomDatabase extends RoomDatabase {

    public abstract StauanlageDao stauanlageDao();

    private static StauanlageRoomDatabase INSTANCE;

    //Instance der Datenbank wird nur dann erzeugt wenn keine andere existiert
    public static StauanlageRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StauanlageRoomDatabase.class) {
                if (INSTANCE == null) {
                    //Erzeugen der Datenbank
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StauanlageRoomDatabase.class, "stauanlage_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
