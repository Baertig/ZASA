package de.badresden.zasa;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "stauanlage_tabelle")
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

    //Tragfähigkeit(Erdbauwerke)
    @ColumnInfo(name = "wasserseitige_boeschungsneigung_zu_luftseitige_boeschungsneigung_kleiner_1_zu_3")
    public boolean wasserseitigZuLuftseitigKleinerEinszuDrei;
    @ColumnInfo(name = "statische_berechnung_liegt_vor")
    public boolean statischeBerechnungLiegtVor;

    //Gebrauchstauglichkeit
    @ColumnInfo(name = "QHWE_von_BHW1_groesser_gleich_BHQ1")
    public boolean qHWWEVonBHW1GoesserGleichBHQ1;
    @ColumnInfo(name = "QHWE_von_BHW2_groesser_gleich_BHQ2")
    public boolean qHWEVonBHW2GroesserGleichBHQ2;
    @ColumnInfo(name = "freibord_zur_UK_von_fruecken_oder_ftegen_groesser_gleich_halb_meter")
    public boolean FreiboardZurUKVonBrueckenOderStegenGroesserGleichHalbMeter;
    @ColumnInfo(name = "freibord_zur_dammkrone_pauschal")
    public boolean FreibordZurDammkronePauschal;
    @ColumnInfo(name = "nachweis_nach_DVWK-Mbl_246_mit_im_BF2_um_15_prozent_reduzierter_windgeschwindigkeit")
    public boolean nachweisNachDVWKMbl246MitImBF2Um15ProzentReduzierterWindgeschwindigkeit;
    @ColumnInfo(name = "bisherige_betriebsauffaelligkeiten")
    public String bisherigeBetriebsauffaelligkeiten;

    //Hochwasserentlastung(HWE)
    @ColumnInfo(name = "querschnittsreduktion_der_wasserwege")
    public boolean querschnittsreduktionDerWasserwege;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_an_wasserwegen")
    public boolean fehlstellenOderBeschaedigungenAnWasserwegen;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_im_tosbecken_HWE")
    public boolean fehlstellenOderBeschaedigungenImTosbeckenHWE;
    @ColumnInfo(name = "treibgutsperre_und_grobrechen_und_palisadenrechen_frei_und_funktionstuechtig")
    public boolean treibgutsperreUndGrobrechenUndPalisadenrechenFreiUndFunktionstuechtig;

    //Grundablass (GA)
    @ColumnInfo(name = "querschnittsreduktion_im_GA")
    public boolean querschnittsreduktionImGA;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_oder_undichtigkeiten_GA")
    public boolean fehlstellenOderBeschaedigungenOderUndichtigkeitenGA;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_im_tosbecken_GA")
    public boolean FehlstellenOderBeschaedigungenImTosbeckenGA;
    @ColumnInfo(name = "schwergaengigkeit_oder_blockieren_des_verschlusses")
    public boolean schwergaengigkeitOderBlockierenDesVerschlusses;

    //Messeinrichtungen
    @ColumnInfo(name = "messeinrichtung_funktionsfaehig")
    public boolean messeinrichtungFunktionsfaehig;

    //Dauerhaftigkeit
    @ColumnInfo(name = "uferveraenderungen_des_stausees")
    public boolean uferveraenderungenDesStausees;

    //Veränderungen an Erdbauwerken
    @ColumnInfo(name = "rutschungen")
    public boolean rutschungen;
    @ColumnInfo(name = "risse")
    public boolean risse;
    @ColumnInfo(name = "sichtbare_setzungen")
    public boolean sichtbareSetzungen;
    @ColumnInfo(name = "sichtbare_hebungen")
    public boolean sichtbareHebungen;
    @ColumnInfo(name = "sichtbare_horizontalverschiebungen")
    public boolean sichtbareHorizontalverschiebungen;
    @ColumnInfo(name = "luftseitige_wasseraustritte")
    public boolean luftseitigeWasseraustritte;
    @ColumnInfo(name = "materialaustrag_durch_sickerwasser")
    public boolean materialaustragDurchSickerwasser;
    @ColumnInfo(name = "erosion_der_wasserseitigen_schutzschicht")
    public boolean erosionDerWasserseitigenSchutzschicht;
    @ColumnInfo(name = "fehlstellen_in_der_grasnarbe_auf_dem_damm")
    public boolean fehlstellenInDerGrasnarbeAufDemDamm;
    @ColumnInfo(name = "gehoelzbewuchs_ohne_zusatzquerschnitt")
    public boolean gehoelzbewuchsOhneZusatzquerschnitt;
    @ColumnInfo(name = "einschraenkung_visuelle_inspektionsmoeglichkeiten_bewuchs_luftseitigen_boeschung")
    public boolean einschraenkungVisuelleInspektionsmoeglichkeitenBewuchsLuftseitigenBoeschung;
    @ColumnInfo(name = "grabende_tiere")
    public boolean grabendeTiere;
}
