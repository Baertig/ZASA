package de.badresden.zasa.converters;

import androidx.room.TypeConverter;

import de.badresden.zasa.Answer;
//Autor: Georg

/**
 * definiert wie Daten vom Typ ANSWER in Datenbank gespeichert werden
 */
public class AnswerTypeConverter {
    @TypeConverter
    public static Answer toAnswer(String value){ // String in Answer umwandeln beim Holen von Daten aus der Datenbank
        return value == null ? null : Answer.valueOf(value);
    }

    @TypeConverter
    public static String toString(Answer value){ // Answer in String umwandeln beim Schreiben in die Datenbank
        return value == null ? null : value.name();
    }

}
