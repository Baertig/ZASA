package de.badresden.zasa;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StauanlageDao {
	@Insert
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
}
