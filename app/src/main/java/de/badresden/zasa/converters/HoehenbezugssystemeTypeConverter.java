package de.badresden.zasa.converters;

import androidx.room.TypeConverter;

import de.badresden.zasa.Answer;
import de.badresden.zasa.Hoehenbezugssysteme;
//Autor: Georg

/**
 * definiert wie Daten vom Typ Hoehenbezugssysteme in Datenbank gespeichert werden
 */
public class HoehenbezugssystemeTypeConverter {
    @TypeConverter
    public static Hoehenbezugssysteme toAnswer(String value){ // String in Answer umwandeln beim Holen von Daten aus der Datenbank
        return value == null ? null : Hoehenbezugssysteme.valueOf(value);
    }

    @TypeConverter
    public static String toString(Hoehenbezugssysteme value){ // Answer in String umwandeln beim Schreiben in die Datenbank
        return value == null ? null : value.name();
    }

}
