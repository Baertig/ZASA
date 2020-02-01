package de.badresden.zasa;

import java.util.Date;

/**
 * This Class stores the stauanlage objekt that is currently loaded in the UI
 */
public class StauanlageHolder {

	private static Stauanlage stauanlage;
	public static Boolean isStauanlageLoadedFromDB = Boolean.FALSE;


	public static void setStauanlage(Stauanlage stauanlage) {
		StauanlageHolder.stauanlage = stauanlage;
	}

	public static Stauanlage getStauanlage() {
		return stauanlage;
	}
	public void createStauanlage() {
		stauanlage = new Stauanlage();
		isStauanlageLoadedFromDB = Boolean.FALSE;
	}
	public static void updateAllgemein(String nameDerAnlage, String geographischeLage, String gestautesGewaesser, String eigentuemerBetreiber,
								String artDesAbsperrauwerkes, Double hoeheAbsperrwerkUeberGruendung, Double stauinhaltInCbm, Double bhq1InCbmProSekunde, Double bhq2InCbmProSekunde,
								Answer betriebsvorschriftNormalfallLiegtVor, Answer betriebsvorschriftHochwasserLiegtVor, Date datumUndUhrzeitLetzteBearbeitung) {

		stauanlage.updateAllgemein(nameDerAnlage, geographischeLage, gestautesGewaesser, eigentuemerBetreiber,
				artDesAbsperrauwerkes, hoeheAbsperrwerkUeberGruendung, stauinhaltInCbm, bhq1InCbmProSekunde,
				bhq2InCbmProSekunde, betriebsvorschriftNormalfallLiegtVor, betriebsvorschriftHochwasserLiegtVor, datumUndUhrzeitLetzteBearbeitung);
	}

	public static void updateTragfaehigkeit(Answer wasserseitigZuLuftseitigKleinerEinszuDrei, Answer statischeBerechnungLiegtVor) {

		stauanlage.updateTragfaehigkeit(wasserseitigZuLuftseitigKleinerEinszuDrei, statischeBerechnungLiegtVor);
	}

	public static void uptdateGebrauchstauglichkeit(Answer qHWWEVonBHW1GoesserGleichBHQ1, Answer qHWEVonBHW2GroesserGleichBHQ2, Answer freiboardZurUKVonBrueckenOderStegenGroesserGleichHalbMeter,
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

	public static void updateDauerhaftigkeit(Answer uferveraenderungenDesStausees, Answer rutschungen, Answer risseErdbau, Answer sichtbareSetzungenErdbaubau, Answer sichtbareHebungen, Answer sichtbareHorizontalverschiebungenErdbau,
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

	/**
	 * leert den Zwischenspeicher
	 */
	public static void clear() {
		stauanlage = null;
		StauanlageViewModel.stauanlageIsLoadedFromDB = Boolean.FALSE;
	}
}
