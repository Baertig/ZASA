package de.badresden.zasa;

import android.app.Activity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.Date;

//Autor: Georg

/**
 * Die Antworten eines Fragebogens (Die Fragen in allen Activities einmal durchgegangen) werden in einem Objekt von Typ Stauanlage gespeichert
 * Das Objekt ist gleichzeitig einen Entity der RoomDatabase, durch sie wird auch eine Tabelle, mit Spalten und deren Datentypen beschrieben
 * In der Datenbank in der Tabelle "stauanlage_table" wird ein Stauanlagen Objekt dann gespeichert
 */

@Entity(tableName = "stauanlage_table")
public class Stauanlage { //der Fragebogen beschreibt eine Stauanlage, deshalb der Name der Klasse
//Die Namen der Variablen orientieren sich stark an der Vorlage um sie später eindeutig wiederzuerkennen, deshalb sind sie so lang
//In den Activities wurden Abkürzungen verwendet, damit der Code übersichtlich bleibt.

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "primary_key")
    public int primaryKey;

    @ColumnInfo(name = "datum_und_uhrzeit_letzte_bebearbeitung")
    public Date datumUndUhrzeitLetzteBearbeitung;

    //Eigenschaften unterteilt in die einzelnen Kategorien
    // " -- " = steht für Unterkategorie
    //Allgemeine Angaben
    @ColumnInfo(name = "name_der_Anlage")
    public String nameDerAnlage;
    //geographische Lage
    @ColumnInfo(name = "longitude")
    public Double longitude;
    @ColumnInfo(name = "latitude")
    public Double latitude;
    @ColumnInfo(name = "postleitzahl")
    public String plz;
    @ColumnInfo(name = "stadt")
    public String ort;
    @ColumnInfo(name = "straße_nummer")
    public String strasseNr;

    @ColumnInfo(name = "gestautes_gewaesser")
    public String gestautesGewaesser;
    @ColumnInfo(name = "einzugsgebiet_der_anlage_in_sqkm")
    public Double einzugsgebietDerAnlageInsqkm; 
    @ColumnInfo(name = "eigentuemer_betreiber")
    public String eigentuemerBetreiber;
    @ColumnInfo(name = "art_des_absperrbauwerkes")
    public String artDesAbsperrauwerkes;
    @ColumnInfo(name = "hoehe_absperrwerk_ueber_gruendung")
    public Double hoeheAbsperrwerkUeberGruendung;
    @ColumnInfo(name = "hohe_absperrwerk_ueber_gruendung_hoehenbezugssystem")
    public Hoehenbezugssysteme hoeheAbsperrwerkUeberGruendungHoehenbezugssystem;
    @ColumnInfo(name = "hoehe_absperrwerk_ueber_gelaende")
    public Double hoeheAbsperrwerkUeberGelaende;
    @ColumnInfo(name = "hoehe_absperrwerk_ueber_gelaende_hoehenbezugssystem")
    public Hoehenbezugssysteme hoeheAbsperrwerkUeberGelaendeHoehenbezugssystem;
    @ColumnInfo(name = "hoehe_absperrwerk_oberkante_krone")
    public Double hoeheAbsperrwerkOberkanteKrone;
    @ColumnInfo(name = "hoehe_absperrwerk_oberkante_krone_hoehenbezugssystem")
    public Hoehenbezugssysteme hoeheAbsperrwerkOberkanteKroneHoehenbezugssystem;
    @ColumnInfo(name = "hoehe_tiefster_punkt_im_gelaende_luftseite")
    public Double hoeheTiefsterPunktImGelaendeLuftseite;
    @ColumnInfo(name = "hoehe_tiefster_punkt_im_gelaende_luftseite_hoehenbezugssystem")
    public Hoehenbezugssysteme hoeheTiefsterPunktImGelaendeLuftseiteHoehenbezugssystem;
    @ColumnInfo(name = "kronenbreite_in_m")
    public Double kronenbreiteInm;
    @ColumnInfo(name = "berme_vorhanden")
    public Boolean bermeVorhanden;
    @ColumnInfo(name = "berme_hoehe_in_m")
    public Double bermeHoeheInm;
    @ColumnInfo(name = "bermen_breite_in_m")
    public Double bermeBreiteInm;
    @ColumnInfo(name = "stauinhalt_in_cbm")
    public Double stauinhaltInCbm;
    @ColumnInfo(name = "bhq1_in_cbm_pro_sekunde")
    public Double bHQ1InCbmProSekunde;
    @ColumnInfo(name = "bhq2_in_cbm_pro_sekunde")
    public Double bHQ2InCbmProSekunde;
    @ColumnInfo(name = "betriebsvorschrift_normalfall_liegt_vor")
    public Answer betriebsvorschriftNormalfallLiegtVor;
    @ColumnInfo(name = "betriebsvorschrift_hochwasser_liegt_vor")
    public Answer betriebsvorschriftHochwasserLiegtVor;
    //TODO Klassifizierung der Stauanlage


    //Tragfähigkeit
    //TODO wasserseitige Neigung und luftseitige Neigung abfragen
    @ColumnInfo(name = "wasserseitige_boeschungsneigung_zu_luftseitige_boeschungsneigung_kleiner_1_zu_3")
    public Answer wasserseitigZuLuftseitigKleinerEinszuDrei;
    @ColumnInfo(name = "statische_berechnung_liegt_vor")
    public Answer statischeBerechnungLiegtVor;

    //Gebrauchstauglichkeit
    @ColumnInfo(name = "QHWE_von_BHW1_groesser_gleich_BHQ1")
    public Answer qHWEVonBHW1GoesserGleichBHQ1;
    @ColumnInfo(name = "QHWE_von_BHW2_groesser_gleich_BHQ2")
    public Answer qHWEVonBHW2GroesserGleichBHQ2;
    @ColumnInfo(name = "freibord_zur_UK_von_bruecken_oder_stegen_groesser_gleich_halb_meter")
    public Answer FreiboardZurUKVonBrueckenOderStegenGroesserGleichHalbMeter;
    @ColumnInfo(name = "freibord_zur_dammkrone_pauschal")
    public Answer FreibordZurDammkronePauschal;
    @ColumnInfo(name = "nachweis_nach_DVWK-Mbl_246_mit_im_BF2_um_15_prozent_reduzierter_windgeschwindigkeit")
    public Answer nachweisNachDVWKMbl246MitImBF2Um15ProzentReduzierterWindgeschwindigkeit;
    @ColumnInfo(name = "bisherige_betriebsauffaelligkeiten")
    public String bisherigeBetriebsauffaelligkeiten;

    // -- Hochwasserentlastung(HWE)
    @ColumnInfo(name = "querschnittsreduktion_der_wasserwege")
    public Answer querschnittsreduktionDerWasserwege;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_an_wasserwegen")
    public Answer fehlstellenOderBeschaedigungenAnWasserwegen;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_im_tosbecken_HWE")
    public Answer fehlstellenOderBeschaedigungenImTosbeckenHWE;
    @ColumnInfo(name = "treibgutsperre_und_grobrechen_und_palisadenrechen_frei_und_funktionstuechtig")
    public Answer treibgutsperreUndGrobrechenUndPalisadenrechenFreiUndFunktionstuechtig;

    // -- Grundablass (GA)
    @ColumnInfo(name = "querschnittsreduktion_im_GA")
    public Answer querschnittsreduktionImGA;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_oder_undichtigkeiten_GA")
    public Answer fehlstellenOderBeschaedigungenOderUndichtigkeitenGA;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_im_tosbecken_GA")
    public Answer fehlstellenOderBeschaedigungenImTosbeckenGA;
    @ColumnInfo(name = "schwergaengigkeit_oder_blockieren_des_verschlusses")
    public Answer schwergaengigkeitOderBlockierenDesVerschlusses;

    // -- Messeinrichtungen
    @ColumnInfo(name = "messeinrichtung_funktionsfaehig")
    public Answer messeinrichtungFunktionsfaehig;

    //Dauerhaftigkeit
    @ColumnInfo(name = "uferveraenderungen_des_stausees")
    public Answer uferveraenderungenDesStausees;

    // -- Veränderungen an Erdbauwerken
    @ColumnInfo(name = "rutschungen")
    public Answer rutschungen;
    @ColumnInfo(name = "risse_erdbau")
    public Answer risseErdbau;
    @ColumnInfo(name = "sichtbare_setzungen_erdbau")
    public Answer sichtbareSetzungenErdbaubau;
    @ColumnInfo(name = "sichtbare_hebungen")
    public Answer sichtbareHebungen;
    @ColumnInfo(name = "sichtbare_horizontalverschiebungen_erdbau")
    public Answer sichtbareHorizontalverschiebungenErdbau;
    @ColumnInfo(name = "luftseitige_wasseraustritte_erdbau")
    public Answer luftseitigeWasseraustritteErdbau;
    @ColumnInfo(name = "materialaustrag_durch_sickerwasser")
    public Answer materialaustragDurchSickerwasser;
    @ColumnInfo(name = "erosion_der_wasserseitigen_schutzschicht")
    public Answer erosionDerWasserseitigenSchutzschicht;
    @ColumnInfo(name = "fehlstellen_in_der_grasnarbe_auf_dem_damm")
    public Answer fehlstellenInDerGrasnarbeAufDemDamm;
    @ColumnInfo(name = "gehoelzbewuchs_ohne_zusatzquerschnitt")
    public Answer gehoelzbewuchsOhneZusatzquerschnitt;
    @ColumnInfo(name = "einschraenkung_visuelle_inspektionsmoeglichkeiten_bewuchs_luftseitigen_boeschung")
    public Answer einschraenkungVisuelleInspektionsmoeglichkeitenBewuchsLuftseitigenBoeschung;
    @ColumnInfo(name = "grabende_tiere")
    public Answer grabendeTiere;

    // -- Veränderungen an Massivbauwerken
    @ColumnInfo(name = "risse_massivbau")
    public Answer risseMassivbau;
    @ColumnInfo(name = "sichtbare_setzungen_massivbau")
    public Answer sichtbareSetzungenMassivbau;
    @ColumnInfo(name = "sichtbare_horizontalverschiebungen_massivbau")
    public Answer sichtbareHorizontalverschiebungenMassivbau;
    @ColumnInfo(name = "neigungsaenderungen_oder_kippungen")
    public Answer neigungsaenderungenOderKippungen;
    @ColumnInfo(name = "abplatzungen")
    public Answer abplatzungen;
    @ColumnInfo(name = "auswaschungen_oder_ausbluehungen")
    public Answer auswaschungenOderAusbluehungen;
    @ColumnInfo(name = "luftseitige_wasseraustritte_massivbau")
    public Answer luftseitigeWasseraustritteMassivbau;
    @ColumnInfo(name = "fehlstellen_im_mauerwerk")
    public Answer fehlstellenImMauerwerk;


    public Stauanlage(){
        this.hoeheAbsperrwerkUeberGruendungHoehenbezugssystem = Hoehenbezugssysteme.HN;
        this.hoeheAbsperrwerkUeberGelaendeHoehenbezugssystem = Hoehenbezugssysteme.HN;
        this.hoeheAbsperrwerkOberkanteKroneHoehenbezugssystem = Hoehenbezugssysteme.HN;
        this.hoeheTiefsterPunktImGelaendeLuftseiteHoehenbezugssystem = Hoehenbezugssysteme.HN;
        this.bermeVorhanden = false;
    }

    /**
     * Methode um die Eigenschaften der Kategorie "Allgemein" zu setzten
     */
    public void updateAllgemein(String nameDerAnlage, Double longitude, Double latitude, String plz, String stadt, String strasseeNr,
                                String gestautesGewaesser, Double einzugsgebietDerAnlageInsqkm, String eigentuemerBetreiber,
                                String artDesAbsperrauwerkes, Double hoeheAbsperrwerkUeberGruendung,
                                Hoehenbezugssysteme hoeheAbsperrwerkUeberGruendungHoehenbezugssystem,
                                Double hoeheAbsperrwerkUeberGelaende, Hoehenbezugssysteme hoeheAbsperrwerkUeberGelaendeHoehenbezugssystem,
                                Double hoeheAbsperrwerkOberkanteKrone, Hoehenbezugssysteme hoeheAbsperrwerkOberkanteKroneHoehenbezugssystem,
                                Double hoeheTiefsterPunktImGelaendeLuftseite, Hoehenbezugssysteme hoeheTiefsterPunktImGelaendeLuftseiteHoehenbezugssystem,
                                Double kronenbreiteInm, Boolean bermeVorhanden, Double bermeHoeheInm,
                                Double bermeBreiteInm, Double stauinhaltInCbm,
                                Double bHQ1InCbmProSekunde, Double bHQ2InCbmProSekunde, Answer betriebsvorschriftNormalfallLiegtVor,
                                Answer betriebsvorschriftHochwasserLiegtVor, Date datumUndUhrzeitLetzteBearbeitung) {
        this.nameDerAnlage = nameDerAnlage;
        this.datumUndUhrzeitLetzteBearbeitung = datumUndUhrzeitLetzteBearbeitung;
        this.longitude = longitude;
        this.latitude = latitude;
        this.plz = plz;
        this.ort = stadt;
        this.strasseNr = strasseeNr;
        this.gestautesGewaesser = gestautesGewaesser;
        this.einzugsgebietDerAnlageInsqkm = einzugsgebietDerAnlageInsqkm; 
        this.eigentuemerBetreiber = eigentuemerBetreiber;
        this.artDesAbsperrauwerkes = artDesAbsperrauwerkes;
        this.hoeheAbsperrwerkUeberGruendung = hoeheAbsperrwerkUeberGruendung;
        this.hoeheAbsperrwerkUeberGruendungHoehenbezugssystem = hoeheAbsperrwerkUeberGruendungHoehenbezugssystem;
        this.hoeheAbsperrwerkUeberGelaende = hoeheAbsperrwerkUeberGelaende;
        this.hoeheAbsperrwerkUeberGelaendeHoehenbezugssystem = hoeheAbsperrwerkUeberGelaendeHoehenbezugssystem;
        this.hoeheAbsperrwerkOberkanteKrone = hoeheAbsperrwerkOberkanteKrone;
        this.hoeheAbsperrwerkOberkanteKroneHoehenbezugssystem = hoeheAbsperrwerkOberkanteKroneHoehenbezugssystem;
        this.hoeheTiefsterPunktImGelaendeLuftseite = hoeheTiefsterPunktImGelaendeLuftseite;
        this.hoeheTiefsterPunktImGelaendeLuftseiteHoehenbezugssystem = hoeheTiefsterPunktImGelaendeLuftseiteHoehenbezugssystem;
        this.kronenbreiteInm = kronenbreiteInm;
        this.bermeVorhanden = bermeVorhanden;
        this.bermeHoeheInm = bermeHoeheInm;
        this.bermeBreiteInm = bermeBreiteInm;
        this.stauinhaltInCbm = stauinhaltInCbm;
        this.bHQ1InCbmProSekunde = bHQ1InCbmProSekunde;
        this.bHQ2InCbmProSekunde = bHQ2InCbmProSekunde;
        this.betriebsvorschriftNormalfallLiegtVor = betriebsvorschriftNormalfallLiegtVor;
        this.betriebsvorschriftHochwasserLiegtVor = betriebsvorschriftHochwasserLiegtVor;
    }

    /**
     * Methode um die Eigenschaften der Kategorie "Tragfähigkeit" zu setzten
     */
    public void updateTragfaehigkeit(Answer wasserseitigZuLuftseitigKleinerEinszuDrei, Answer statischeBerechnungLiegtVor) {
        this.wasserseitigZuLuftseitigKleinerEinszuDrei = wasserseitigZuLuftseitigKleinerEinszuDrei;
        this.statischeBerechnungLiegtVor = statischeBerechnungLiegtVor;
    }

    /**
     * Methode um die Eigenschaften der Kategorie "Gebrauchstauglichkeit" zu setzten
     */
    public void updateGebrauchstauglichkeit(Answer qHWWEVonBHW1GoesserGleichBHQ1, Answer qHWEVonBHW2GroesserGleichBHQ2, Answer freiboardZurUKVonBrueckenOderStegenGroesserGleichHalbMeter,
                                            Answer freibordZurDammkronePauschal, Answer nachweisNachDVWKMbl246MitImBF2Um15ProzentReduzierterWindgeschwindigkeit, String bisherigeBetriebsauffaelligkeiten,
                                            Answer querschnittsreduktionDerWasserwege, Answer fehlstellenOderBeschaedigungenAnWasserwegen, Answer fehlstellenOderBeschaedigungenImTosbeckenHWE, Answer treibgutsperreUndGrobrechenUndPalisadenrechenFreiUndFunktionstuechtig,
                                            Answer querschnittsreduktionImGA, Answer fehlstellenOderBeschaedigungenOderUndichtigkeitenGA, Answer fehlstellenOderBeschaedigungenImTosbeckenGA,
                                            Answer schwergaengigkeitOderBlockierenDesVerschlusses, Answer messeinrichtungFunktionsfaehig) {
        this.qHWEVonBHW1GoesserGleichBHQ1 = qHWWEVonBHW1GoesserGleichBHQ1;
        this.qHWEVonBHW2GroesserGleichBHQ2 = qHWEVonBHW2GroesserGleichBHQ2;
        this.FreiboardZurUKVonBrueckenOderStegenGroesserGleichHalbMeter = freiboardZurUKVonBrueckenOderStegenGroesserGleichHalbMeter;
        this.FreibordZurDammkronePauschal = freibordZurDammkronePauschal;
        this.nachweisNachDVWKMbl246MitImBF2Um15ProzentReduzierterWindgeschwindigkeit = nachweisNachDVWKMbl246MitImBF2Um15ProzentReduzierterWindgeschwindigkeit;
        this.bisherigeBetriebsauffaelligkeiten = bisherigeBetriebsauffaelligkeiten;
        this.querschnittsreduktionDerWasserwege = querschnittsreduktionDerWasserwege;
        this.fehlstellenOderBeschaedigungenAnWasserwegen = fehlstellenOderBeschaedigungenAnWasserwegen;
        this.fehlstellenOderBeschaedigungenImTosbeckenHWE = fehlstellenOderBeschaedigungenImTosbeckenHWE;
        this.treibgutsperreUndGrobrechenUndPalisadenrechenFreiUndFunktionstuechtig = treibgutsperreUndGrobrechenUndPalisadenrechenFreiUndFunktionstuechtig;
        this.querschnittsreduktionImGA = querschnittsreduktionImGA;
        this.fehlstellenOderBeschaedigungenOderUndichtigkeitenGA = fehlstellenOderBeschaedigungenOderUndichtigkeitenGA;
        this.fehlstellenOderBeschaedigungenImTosbeckenGA = fehlstellenOderBeschaedigungenImTosbeckenGA;
        this.schwergaengigkeitOderBlockierenDesVerschlusses = schwergaengigkeitOderBlockierenDesVerschlusses;
        this.messeinrichtungFunktionsfaehig = messeinrichtungFunktionsfaehig;
    }

    /**
     *Methode um die Eigenschaften der Kategorie "Dauerhaftigkeit" zu setzten
     */
    public void updateDauerhaftigkeit(Answer uferveraenderungenDesStausees, Answer rutschungen, Answer risseErdbau, Answer sichtbareSetzungenErdbaubau, Answer sichtbareHebungen, Answer sichtbareHorizontalverschiebungenErdbau,
                                      Answer luftseitigeWasseraustritteErdbau, Answer materialaustragDurchSickerwasser, Answer erosionDerWasserseitigenSchutzschicht, Answer fehlstellenInDerGrasnarbeAufDemDamm,
                                      Answer gehoelzbewuchsOhneZusatzquerschnitt, Answer einschraenkungVisuelleInspektionsmoeglichkeitenBewuchsLuftseitigenBoeschung, Answer grabendeTiere, Answer risseMassivbau,
                                      Answer sichtbareSetzungenMassivbau, Answer sichtbareHorizontalverschiebungenMassivbau, Answer neigungsaenderungenOderKippungen, Answer abplatzungen, Answer auswaschungenOderAusbluehungen,
                                      Answer luftseitigeWasseraustritteMassivbau, Answer fehlstellenImMauerwerk) {
        this.uferveraenderungenDesStausees = uferveraenderungenDesStausees;
        this.rutschungen = rutschungen;
        this.risseErdbau = risseErdbau;
        this.sichtbareSetzungenErdbaubau = sichtbareSetzungenErdbaubau;
        this.sichtbareHebungen = sichtbareHebungen;
        this.sichtbareHorizontalverschiebungenErdbau = sichtbareHorizontalverschiebungenErdbau;
        this.luftseitigeWasseraustritteErdbau = luftseitigeWasseraustritteErdbau;
        this.materialaustragDurchSickerwasser = materialaustragDurchSickerwasser;
        this.erosionDerWasserseitigenSchutzschicht = erosionDerWasserseitigenSchutzschicht;
        this.fehlstellenInDerGrasnarbeAufDemDamm = fehlstellenInDerGrasnarbeAufDemDamm;
        this.gehoelzbewuchsOhneZusatzquerschnitt = gehoelzbewuchsOhneZusatzquerschnitt;
        this.einschraenkungVisuelleInspektionsmoeglichkeitenBewuchsLuftseitigenBoeschung = einschraenkungVisuelleInspektionsmoeglichkeitenBewuchsLuftseitigenBoeschung;
        this.grabendeTiere = grabendeTiere;
        this.risseMassivbau = risseMassivbau;
        this.sichtbareSetzungenMassivbau = sichtbareSetzungenMassivbau;
        this.sichtbareHorizontalverschiebungenMassivbau = sichtbareHorizontalverschiebungenMassivbau;
        this.neigungsaenderungenOderKippungen = neigungsaenderungenOderKippungen;
        this.abplatzungen = abplatzungen;
        this.auswaschungenOderAusbluehungen = auswaschungenOderAusbluehungen;
        this.luftseitigeWasseraustritteMassivbau = luftseitigeWasseraustritteMassivbau;
        this.fehlstellenImMauerwerk = fehlstellenImMauerwerk;
    }

    /**
     * Is made to make printing easier
     * @param activity is used to retriev String Resources
     * @return returns HashMap representing the Stauanlage with a description of every field as the key and its value. Should only be used with foreach loops. Don't try to get something with a Key.
     *
     */
    public ArrayList<AttributeDetailed> getAttributeDetailedList(Activity activity){
        ArrayList<AttributeDetailed> attributeDetailedList = new ArrayList<>();
        //Allgemeine Angaben
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.questionnaire_table_title), this.nameDerAnlage));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_lage), this.plz));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_gewaesser), this.gestautesGewaesser));
        attributeDetailedList.add(new AttributeDetailed(activity.getString(R.string.einzugsgebiet_der_anlage), this.einzugsgebietDerAnlageInsqkm)); 
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_eigentuemer),this.eigentuemerBetreiber));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_art_des_absperrbauwerkes), this.artDesAbsperrauwerkes));
        //TODO think about how to add the Hoehenbezugssystem Information to the list
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_hoehe_ueber_gruendung),this.hoeheAbsperrwerkUeberGruendung
                + " (" + this.hoeheAbsperrwerkUeberGruendungHoehenbezugssystem.toString() + ")"));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_hoehe_ueber_gelaende), this.hoeheAbsperrwerkUeberGruendung
                + " (" + this.hoeheAbsperrwerkUeberGelaendeHoehenbezugssystem.toString() + ")"));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_hoehe_oberkante_krone), this.hoeheAbsperrwerkOberkanteKrone
                + " (" + this.hoeheAbsperrwerkOberkanteKroneHoehenbezugssystem.toString() + ")"));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_hoehe_tiefster_punkt), this.hoeheTiefsterPunktImGelaendeLuftseite
                + " (" + this.hoeheTiefsterPunktImGelaendeLuftseiteHoehenbezugssystem.toString() + ")"));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_kronenbreite), this.kronenbreiteInm));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_bermen_vorhanden), this.bermeVorhanden));
        if(this.bermeVorhanden){
            attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_berme_hoehe), this.bermeHoeheInm));
            attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_berme_breite), this.bermeBreiteInm));
        }
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_stauinhalt),this.stauinhaltInCbm));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_bhq1), this.bHQ1InCbmProSekunde));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_bhq2), this.bHQ2InCbmProSekunde));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_betriebsvorschrift_normalbetrieb), this.betriebsvorschriftNormalfallLiegtVor));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_betriebsvorschrift_hochwasserfall), this.betriebsvorschriftHochwasserLiegtVor));
        //Tragfähigkeit
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_boeschungsneigung_verhaeltnis),
                this.wasserseitigZuLuftseitigKleinerEinszuDrei));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_statische_berechnung), this.statischeBerechnungLiegtVor));
        //Gebrauchstauglichkeit
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_qhwe1), this.qHWEVonBHW1GoesserGleichBHQ1)); //FIXME vllt sollte eher QHWE angezeigt werden
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_qhwe2), this.qHWEVonBHW2GroesserGleichBHQ2)); //FIXME und das ein Hinweis kommen das es nicht größer gleich ist...
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_nachweis_dvwk),
            this.nachweisNachDVWKMbl246MitImBF2Um15ProzentReduzierterWindgeschwindigkeit));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_betriebsauffaelligkeiten),
                this.bisherigeBetriebsauffaelligkeiten));
        // -- Hochwasserentlastung(HWE)
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_querschnittsreduktion_hwe), this.querschnittsreduktionDerWasserwege));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_beschaedigungen_wasserwege), this.fehlstellenOderBeschaedigungenAnWasserwegen));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_beschaedigungen_tosbecken_hwe), this.fehlstellenOderBeschaedigungenImTosbeckenHWE));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_frei_und_funktionstuechtig),
                this.treibgutsperreUndGrobrechenUndPalisadenrechenFreiUndFunktionstuechtig));
        // -- Grundablass
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_querschnittsreduktion_ga), this.querschnittsreduktionImGA));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_beschaedigungen_ga), this.fehlstellenOderBeschaedigungenOderUndichtigkeitenGA));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_beschaedigungen_tosbecken_ga), this.fehlstellenOderBeschaedigungenImTosbeckenGA));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_schwergaengigkeit_verschluss), this.schwergaengigkeitOderBlockierenDesVerschlusses));
        // -- Messeinrichtungen
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_messeinrichtungen_funktionsfaehig), this.messeinrichtungFunktionsfaehig));
        //Dauerhaftigkeit
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_uferveraenderungen), this.uferveraenderungenDesStausees));
        // -- Veränderungen an Erdbauwerken
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_rutschungen), this.rutschungen));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_risse_erd), this.risseErdbau));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_setzungen_erd), this.sichtbareSetzungenErdbaubau));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_hebungen_erd), this.sichtbareHebungen));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_verschiebungen_erd), this.sichtbareHorizontalverschiebungenErdbau));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_wasseraustritte_erd), this.luftseitigeWasseraustritteErdbau));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_materialaustritt_erd), this.materialaustragDurchSickerwasser));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_erosion_erd), this.erosionDerWasserseitigenSchutzschicht));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_fehlstellen_grasnarbe), this.fehlstellenInDerGrasnarbeAufDemDamm));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_gehoelzbewuchs), this.gehoelzbewuchsOhneZusatzquerschnitt));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_visuelle_einschraenkung),
                this.einschraenkungVisuelleInspektionsmoeglichkeitenBewuchsLuftseitigenBoeschung));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_grabende_tiere), this.grabendeTiere));
        // -- Veränderungen an Massivbauwerken
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_risse_massiv), this.risseMassivbau));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_setzungen_massiv), this.sichtbareSetzungenMassivbau));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_verschiebungen_massiv), this.sichtbareHorizontalverschiebungenMassivbau));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_kippungen), this.neigungsaenderungenOderKippungen));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_abplatzungen), this.abplatzungen));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_auswaschungen), this.auswaschungenOderAusbluehungen));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_wasseraustritte_massiv), this.luftseitigeWasseraustritteMassivbau));
        attributeDetailedList.add(new AttributeDetailed(activity.getResources().getString(R.string.lbl_question_fehlstellen_mauerwerk), this.fehlstellenImMauerwerk));
        return attributeDetailedList;
    }
}
