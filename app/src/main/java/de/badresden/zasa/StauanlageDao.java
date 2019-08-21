package de.badresden.zasa;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface StauanlageDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(Stauanlage stauanlage);

	@Query("SELECT * FROM stauanlage_table")
	LiveData<List<Stauanlage>> getAllStauanlagen();

	@Query("SELECT primary_key,name_der_Anlage,datum_und_uhrzeit_letzte_bebearbeitung FROM stauanlage_table")
	LiveData<List<StauanlageSimplyfied>> getAllStauanlagenSimplyfied();

	@Update
	void update(Stauanlage... stauanlagen);

	//FIXME: war erstmal nur zum testen
	@Query("DELETE FROM stauanlage_table")
	void deleteAll();

	@Query("SELECT * FROM stauanlage_table WHERE name_der_Anlage = :nameDerAnlage AND " +
					"datum_und_uhrzeit_letzte_bebearbeitung = :datumUndUhrzeitLetzteBearbeitung")
	List<Stauanlage> getStauanlagenWith(String nameDerAnlage, Date datumUndUhrzeitLetzteBearbeitung);
}