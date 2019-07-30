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

	@Query("SELECT * FROM stauanlage_tabelle")
	LiveData<List<Stauanlage>> holeAlleStauanlagen();

	@Query("SELECT primary_key,name_der_Anlage,datum_und_uhrzeit_letzte_bebearbeitung FROM stauanlage_tabelle")
	LiveData<List<StauanlageSimplyfied>> holeAlleStauanlageSimplyfied();

	@Update
	void update(Stauanlage... stauanlagen);
}
