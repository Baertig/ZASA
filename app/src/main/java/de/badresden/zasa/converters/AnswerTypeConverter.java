package de.badresden.zasa.converters;

import androidx.room.TypeConverter;

import de.badresden.zasa.Answer;

public class AnswerTypeConverter {
    @TypeConverter
    public static Answer toAnswer(String value){
        return value == null ? null : Answer.valueOf(value);
    }

    @TypeConverter
    public static String toString(Answer value){
        return value == null ? null : value.name();
    }

}
