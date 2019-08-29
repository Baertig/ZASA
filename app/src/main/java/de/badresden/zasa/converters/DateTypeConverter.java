package de.badresden.zasa.converters;

import androidx.room.TypeConverter;

import java.util.Date;
//Autor: Georg

/**
 * definiert wie Daten vom Typ Date in der Datenbank gespeichert werden
 */
public class DateTypeConverter {
	@TypeConverter
	public static Date toDate(Long value) { // Long in Date umwandeln beim Holen von Daten aus der Datenbank
		return value == null ? null : new Date(value);
	}

	@TypeConverter
	public static Long toLong(Date value) { // Date in Long umwandeln beim Schreiben in die Datenbank
		return value == null ? null : value.getTime();
	}
}
