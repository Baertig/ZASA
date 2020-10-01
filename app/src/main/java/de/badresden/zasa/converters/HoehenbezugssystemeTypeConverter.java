package de.badresden.zasa.converters;

import androidx.room.TypeConverter;


import de.badresden.zasa.Hoehenbezugssysteme;
//Autor: Georg

/**
 * definiert wie Daten vom Typ Hoehenbezugssysteme in Datenbank gespeichert werden
 */
public class HoehenbezugssystemeTypeConverter {
    @TypeConverter
    public static Hoehenbezugssysteme toHoehenbezugssystem(String value){
        return value == null ? null : Hoehenbezugssysteme.valueOf(value);
    }

    @TypeConverter
    public static String toString(Hoehenbezugssysteme value){
        return value == null ? null : value.name();
    }

}
