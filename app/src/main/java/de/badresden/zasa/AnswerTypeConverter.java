package de.badresden.zasa;

import androidx.room.TypeConverter;

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
