package de.badresden.zasa.converters;

import androidx.room.TypeConverter;

import de.badresden.zasa.Stauanlage.Klassifizierung;
//Autor: Georg

/**
 * definiert wie Daten vom Typ Stauanlage.Klassifizierung in Datenbank gespeichert werden
 */
public class KlassifizierungTypeConverter {
	@TypeConverter
	public static Klassifizierung toKlassifizierung
			(String value){
		return value == null ? null : Klassifizierung.valueOf(value);
	}

	@TypeConverter
	public static String toString(Klassifizierung value){ // Answer in String umwandeln beim Schreiben in die Datenbank
		return value == null ? null : value.name();
	}

}
