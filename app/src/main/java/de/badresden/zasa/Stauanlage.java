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
    public Integer hoeheAbsperrwerkUeberGruendung;
    @ColumnInfo(name = "stauinhalt_in_cbm")
    public Integer stauinhaltInCbm;
    @ColumnInfo(name = "bhq1_in_cbm_pro_sekunde")
    public Integer bhq1InCbmProSekunde;
    @ColumnInfo(name = "bhq2_in_cbm_pro_sekunde")
    public Integer bhq2InCbmProSekunde;
    @ColumnInfo(name = "betriebsvorschrift_normalfall_liegt_vor")
    public Answer BetriebsvorschriftNormalfallLiegtVor;
    @ColumnInfo(name = "betriebsvorschrift_hochwasser_liegt_vor")
    public Answer BetriebsvorschriftHochwasserLiegtVor;

    //Tragfähigkeit(Erdbauwerke)
    @ColumnInfo(name = "wasserseitige_boeschungsneigung_zu_luftseitige_boeschungsneigung_kleiner_1_zu_3")
    public Answer wasserseitigZuLuftseitigKleinerEinszuDrei;
    @ColumnInfo(name = "statische_berechnung_liegt_vor")
    public Answer statischeBerechnungLiegtVor;

    //Gebrauchstauglichkeit
    @ColumnInfo(name = "QHWE_von_BHW1_groesser_gleich_BHQ1")
    public Answer qHWEVonBHW1GoesserGleichBHQ1; //FIXME doppel W
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

    //Hochwasserentlastung(HWE)
    @ColumnInfo(name = "querschnittsreduktion_der_wasserwege")
    public Answer querschnittsreduktionDerWasserwege;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_an_wasserwegen")
    public Answer fehlstellenOderBeschaedigungenAnWasserwegen;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_im_tosbecken_HWE")
    public Answer fehlstellenOderBeschaedigungenImTosbeckenHWE;
    @ColumnInfo(name = "treibgutsperre_und_grobrechen_und_palisadenrechen_frei_und_funktionstuechtig")
    public Answer treibgutsperreUndGrobrechenUndPalisadenrechenFreiUndFunktionstuechtig;

    //Grundablass (GA)
    @ColumnInfo(name = "querschnittsreduktion_im_GA")
    public Answer querschnittsreduktionImGA;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_oder_undichtigkeiten_GA")
    public Answer fehlstellenOderBeschaedigungenOderUndichtigkeitenGA;
    @ColumnInfo(name = "fehlstellen_oder_beschaedigungen_im_tosbecken_GA")
    public Answer FehlstellenOderBeschaedigungenImTosbeckenGA;
    @ColumnInfo(name = "schwergaengigkeit_oder_blockieren_des_verschlusses")
    public Answer schwergaengigkeitOderBlockierenDesVerschlusses;

    //Messeinrichtungen
    @ColumnInfo(name = "messeinrichtung_funktionsfaehig")
    public Answer messeinrichtungFunktionsfaehig;

    //Dauerhaftigkeit
    @ColumnInfo(name = "uferveraenderungen_des_stausees")
    public Answer uferveraenderungenDesStausees;

    //Veränderungen an Erdbauwerken
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

    //Veränderungen an Massivbauswerken
    @ColumnInfo(name = "risse_massivbau")
    public Answer risseMassivbau;
    @ColumnInfo(name = "sichtbare_setzungen_massivbau")
    public Answer sichtbareSetzungenMassivbau;
    @ColumnInfo(name = "sichtbare_horizontalverschiebungen_massivbau")
    public Answer SichtbareHorizontalverschiebungenMassivbau;
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


    //FIXME Georg: ist erstmal nur zum testen


    public Stauanlage(String nameDerAnlage) {
        this.nameDerAnlage = nameDerAnlage;
    }

    //public Stauanlage(){}

    public void updateAllgemein(String nameDerAnlage, String geographischeLage, String gestautesGewaesser, String eigentuemerBetreiber,
                      String artDesAbsperrauwerkes, int hoeheAbsperrwerkUeberGruendung, int stauinhaltInCbm, int bhq1InCbmProSekunde, int bhq2InCbmProSekunde,
                      Answer betriebsvorschriftNormalfallLiegtVor, Answer betriebsvorschriftHochwasserLiegtVor, Date datumUndUhrzeitLetzteBearbeitung) {
        this.nameDerAnlage = nameDerAnlage;
        this.datumUndUhrzeitLetzteBearbeitung = datumUndUhrzeitLetzteBearbeitung;
        this.geographischeLage = geographischeLage;
        this.gestautesGewaesser = gestautesGewaesser;
        this.eigentuemerBetreiber = eigentuemerBetreiber;
        this.artDesAbsperrauwerkes = artDesAbsperrauwerkes;
        this.hoeheAbsperrwerkUeberGruendung = hoeheAbsperrwerkUeberGruendung;
        this.stauinhaltInCbm = stauinhaltInCbm;
        this.bhq1InCbmProSekunde = bhq1InCbmProSekunde;
        this.bhq2InCbmProSekunde = bhq2InCbmProSekunde;
        this.BetriebsvorschriftNormalfallLiegtVor = betriebsvorschriftNormalfallLiegtVor;
        this.BetriebsvorschriftHochwasserLiegtVor = betriebsvorschriftHochwasserLiegtVor;
    }

    public void uptdateGebrauchstauglichkeit(Answer qHWWEVonBHW1GoesserGleichBHQ1, Answer qHWEVonBHW2GroesserGleichBHQ2, Answer freiboardZurUKVonBrueckenOderStegenGroesserGleichHalbMeter,
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
        this.FehlstellenOderBeschaedigungenImTosbeckenGA = fehlstellenOderBeschaedigungenImTosbeckenGA;
        this.schwergaengigkeitOderBlockierenDesVerschlusses = schwergaengigkeitOderBlockierenDesVerschlusses;
        this.messeinrichtungFunktionsfaehig = messeinrichtungFunktionsfaehig;
    }
}
