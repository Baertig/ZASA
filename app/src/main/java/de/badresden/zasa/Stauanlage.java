package de.badresden.zasa;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "stauanlage_table")
public class Stauanlage {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "primary_key")
    public int primaryKey;

    @ColumnInfo(name = "datum_und_uhrzeit_letzte_bebearbeitung")
    public Date datumUndUhrzeitLetzteBearbeitung;

    //Allgemeine Angaben
    @ColumnInfo(name = "name_der_Anlage")
    public String nameDerAnlage;
    @ColumnInfo(name = "geographische_lage")
    public String geographischeLage;
    @ColumnInfo(name = "gestautes_gewaesser")
    public String gestautesGewaesser;
    @ColumnInfo(name = "eigentuemer_betreiber")
    public String eigentuemerBetreiber;
    @ColumnInfo(name = "art_des_absperrbauwerkes")
    public String artDesAbsperrauwerkes;
    @ColumnInfo(name = "hoehe_absperrwerk_ueber_gruendung")
    public int hoeheAbsperrwerkUeberGruendung;
    @ColumnInfo(name = "stauinhalt_in_cbm")
    public int stauinhaltInCbm;
    @ColumnInfo(name = "bhq1_in_cbm_pro_sekunde")
    public int bhq1InCbmProSekunde;
    @ColumnInfo(name = "bhq2_in_cbm_pro_sekunde")
    public int bhq2InCbmProSekunde;
    @ColumnInfo(name = "betriebsvorschrift_normalfall_liegt_vor")
    public boolean BetriebsvorschriftNormalfallLiegtVor;
    @ColumnInfo(name = "betriebsvorschrift_hochwasser_liegt_vor")
    public boolean BetriebsvorschriftHochwasserLiegtVor;

    //FIXME Georg: ist erstmal nur zum testen
    public Stauanlage(String nameDerAnlage) {
        this.nameDerAnlage = nameDerAnlage;
    }
}
