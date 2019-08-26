package de.badresden.zasa;

import androidx.room.ColumnInfo;

import java.util.Date;
//Autor: Georg
public class StauanlageSimplyfied {
    @ColumnInfo(name = "primary_key")
    public int primaryKey;

    @ColumnInfo(name = "datum_und_uhrzeit_letzte_bebearbeitung")
    public Date datumUndUhrzeitLetzteBearbeitung;

    @ColumnInfo(name = "name_der_Anlage")
    public String namederAnlage;
}
