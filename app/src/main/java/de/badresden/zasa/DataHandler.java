package de.badresden.zasa;


import java.util.Date;
import java.util.HashMap;

public class DataHandler {
    private static Date datumUndUhrzeitLetzteBearbeitung = null;
    private static String nameDerAnlage = null;
    private static int id = -1; //TODO: wenn Object geladen wurde könnte auch das feld id genutzt werden um Stauanlage objekt zu laden

    public static Date getDatumUndUhrzeitLetzteBearbeitung() {
        return datumUndUhrzeitLetzteBearbeitung;
    }

    public static void setDatumUndUhrzeitLetzteBearbeitung(Date datumUndUhrzeitLetzteBearbeitung) {
        DataHandler.datumUndUhrzeitLetzteBearbeitung = datumUndUhrzeitLetzteBearbeitung;
    }

    public static String getNameDerAnlage() {
        return nameDerAnlage;
    }

    public static void setNameDerAnlage(String nameDerAnlage) {
        DataHandler.nameDerAnlage = nameDerAnlage;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        DataHandler.id = id;
    }
}
