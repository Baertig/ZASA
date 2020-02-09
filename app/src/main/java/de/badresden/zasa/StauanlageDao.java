package de.badresden.zasa;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Autor: Georg

/**
 * Im DAO werden die SQL - Befehle definiert
 */
@Dao
public interface StauanlageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Stauanlage stauanlage);

    @Query("SELECT primary_key,name_der_Anlage,datum_und_uhrzeit_letzte_bebearbeitung FROM stauanlage_table")
    LiveData<List<StauanlageSimplyfied>> getAllStauanlagenSimplyfied();

    @Update()
    void update(Stauanlage... stauanlagen);

    @Query("DELETE FROM stauanlage_table")
    void deleteAll();

    @Query("SELECT * FROM  stauanlage_table WHERE primary_key = :primaryKey")
    Stauanlage selectStauanlageWith(int primaryKey);

    @Delete
    void delete(Stauanlage stauanlage);
}
