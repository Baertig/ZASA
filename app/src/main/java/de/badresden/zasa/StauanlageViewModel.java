package de.badresden.zasa;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class StauanlageViewModel extends AndroidViewModel {

    public static Stauanlage stauanlage = null;
    private StauanlageRepository mRepository;
    private LiveData<List<StauanlageSimplyfied>> mAllStauanlagenSimplyfied;


    public StauanlageViewModel(@NonNull Application application) {
        super(application);
        mRepository = new StauanlageRepository(application);
        mAllStauanlagenSimplyfied = mRepository.getAllStauanlagenSimplyfied();
    }

    public LiveData<List<StauanlageSimplyfied>> getAllStauanlagenSimplyfied() {
        return mAllStauanlagenSimplyfied;
    }

    // das private Feld wird gespeichert
    public void insert() {
        mRepository.insert(stauanlage);
        stauanlage = null; // leeren des Zwischenspeichers, damit neue stauanlage gespeichert werden kann
        //mAllStauanlagenSimplyfied = mRepository.getAllStauanlagenSimplyfied();
    }

    public void update(Stauanlage stauanlage) {
        mRepository.update(stauanlage);
    }

    public void createStauanlage() {
        stauanlage = new Stauanlage();
    }

    public void updateAllgemein(String nameDerAnlage, String geographischeLage, String gestautesGewaesser, String eigentuemerBetreiber,
                                String artDesAbsperrauwerkes, Double hoeheAbsperrwerkUeberGruendung, Double stauinhaltInCbm, Double bhq1InCbmProSekunde, Double bhq2InCbmProSekunde,
                                Answer betriebsvorschriftNormalfallLiegtVor, Answer betriebsvorschriftHochwasserLiegtVor, Date datumUndUhrzeitLetzteBearbeitung) {

        stauanlage.updateAllgemein(nameDerAnlage, geographischeLage, gestautesGewaesser, eigentuemerBetreiber, //TODO beim renaming beachten !!!
                artDesAbsperrauwerkes, hoeheAbsperrwerkUeberGruendung, stauinhaltInCbm, bhq1InCbmProSekunde,
                bhq2InCbmProSekunde, betriebsvorschriftNormalfallLiegtVor, betriebsvorschriftHochwasserLiegtVor, datumUndUhrzeitLetzteBearbeitung);
    }

    public void updateTragfaehigkeit(Answer wasserseitigZuLuftseitigKleinerEinszuDrei, Answer statischeBerechnungLiegtVor) {

        stauanlage.updateTragfaehigkeit(wasserseitigZuLuftseitigKleinerEinszuDrei, statischeBerechnungLiegtVor);
    }

    public void uptdateGebrauchstauglichkeit(Answer qHWWEVonBHW1GoesserGleichBHQ1, Answer qHWEVonBHW2GroesserGleichBHQ2, Answer freiboardZurUKVonBrueckenOderStegenGroesserGleichHalbMeter,
                                             Answer freibordZurDammkronePauschal, Answer nachweisNachDVWKMbl246MitImBF2Um15ProzentReduzierterWindgeschwindigkeit, String bisherigeBetriebsauffaelligkeiten,
                                             Answer querschnittsreduktionDerWasserwege, Answer fehlstellenOderBeschaedigungenAnWasserwegen, Answer fehlstellenOderBeschaedigungenImTosbeckenHWE, Answer treibgutsperreUndGrobrechenUndPalisadenrechenFreiUndFunktionstuechtig,
                                             Answer querschnittsreduktionImGA, Answer fehlstellenOderBeschaedigungenOderUndichtigkeitenGA, Answer fehlstellenOderBeschaedigungenImTosbeckenGA,
                                             Answer schwergaengigkeitOderBlockierenDesVerschlusses, Answer messeinrichtungFunktionsfaehig) {

        stauanlage.updateGebrauchstauglichkeit(qHWWEVonBHW1GoesserGleichBHQ1, qHWEVonBHW2GroesserGleichBHQ2, freiboardZurUKVonBrueckenOderStegenGroesserGleichHalbMeter,
                freibordZurDammkronePauschal, nachweisNachDVWKMbl246MitImBF2Um15ProzentReduzierterWindgeschwindigkeit, bisherigeBetriebsauffaelligkeiten,
                querschnittsreduktionDerWasserwege, fehlstellenOderBeschaedigungenAnWasserwegen, fehlstellenOderBeschaedigungenImTosbeckenHWE, treibgutsperreUndGrobrechenUndPalisadenrechenFreiUndFunktionstuechtig,
                querschnittsreduktionImGA, fehlstellenOderBeschaedigungenOderUndichtigkeitenGA, fehlstellenOderBeschaedigungenImTosbeckenGA,
                schwergaengigkeitOderBlockierenDesVerschlusses, messeinrichtungFunktionsfaehig);
    }

    public void updateDauerhaftigkeit(Answer uferveraenderungenDesStausees, Answer rutschungen, Answer risseErdbau, Answer sichtbareSetzungenErdbaubau, Answer sichtbareHebungen, Answer sichtbareHorizontalverschiebungenErdbau,
                                      Answer luftseitigeWasseraustritteErdbau, Answer materialaustragDurchSickerwasser, Answer erosionDerWasserseitigenSchutzschicht, Answer fehlstellenInDerGrasnarbeAufDemDamm,
                                      Answer gehoelzbewuchsOhneZusatzquerschnitt, Answer einschraenkungVisuelleInspektionsmoeglichkeitenBewuchsLuftseitigenBoeschung, Answer grabendeTiere, Answer risseMassivbau,
                                      Answer sichtbareSetzungenMassivbau, Answer sichtbareHorizontalverschiebungenMassivbau, Answer neigungsaenderungenOderKippungen, Answer abplatzungen, Answer auswaschungenOderAusbluehungen,
                                      Answer luftseitigeWasseraustritteMassivbau, Answer fehlstellenImMauerwerk) {
        stauanlage.updateDauerhaftigkeit(uferveraenderungenDesStausees, rutschungen, risseErdbau, sichtbareSetzungenErdbaubau, sichtbareHebungen, sichtbareHorizontalverschiebungenErdbau,
                luftseitigeWasseraustritteErdbau, materialaustragDurchSickerwasser, erosionDerWasserseitigenSchutzschicht, fehlstellenInDerGrasnarbeAufDemDamm,
                gehoelzbewuchsOhneZusatzquerschnitt, einschraenkungVisuelleInspektionsmoeglichkeitenBewuchsLuftseitigenBoeschung, grabendeTiere, risseMassivbau,
                sichtbareSetzungenMassivbau, sichtbareHorizontalverschiebungenMassivbau, neigungsaenderungenOderKippungen, abplatzungen, auswaschungenOderAusbluehungen,
                luftseitigeWasseraustritteMassivbau, fehlstellenImMauerwerk);
    }

    public void loadStauanlageWith(String nameDerAnlage, Date datumUndUhrzeitLetzteBearbeitung) {
        stauanlage = mRepository.getStauanlageWith(nameDerAnlage, datumUndUhrzeitLetzteBearbeitung).get(0); //FIXME fuer OOP Abgabe loeschen
    }

    public Answer decideRadioAnswer(int radioIdAnswer, int radioIdJa, int radioIdUnbekannt, int radioIdNein) {
        if (radioIdAnswer == radioIdJa) {
            return Answer.JA;
        } else if (radioIdAnswer == radioIdUnbekannt) {
            return Answer.UNBEKANNT;
        } else if (radioIdAnswer == radioIdNein) {
            return Answer.NEIN;
        } else {
            return null;
        }
    }

    public Answer decideQHEW1GreaterEqualBHQ1(Double qhwe1) {
        if (!Double.isNaN(qhwe1) && !Double.isNaN(stauanlage.bhq1InCbmProSekunde)) {
            if (qhwe1 >= stauanlage.bhq1InCbmProSekunde) {
                return Answer.JA;
            } else {
                return Answer.NEIN;
            }
        } else {
            return Answer.UNBEKANNT;
        }
    }

    public Answer decideQHEW2GreaterEqualBHQ2(Double qhwe2) {
        if (!Double.isNaN(qhwe2) && !Double.isNaN(stauanlage.bhq2InCbmProSekunde)) {
            if (qhwe2 >= stauanlage.bhq1InCbmProSekunde) {
                return Answer.JA;
            } else {
                return Answer.NEIN;
            }
        } else {
            return Answer.UNBEKANNT;
        }
    }
}
