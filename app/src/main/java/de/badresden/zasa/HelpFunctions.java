package de.badresden.zasa;

import android.widget.RadioButton;

/**
 * Klasse die statische Hiflsfunktionen enthält
 */
public class HelpFunctions {
	/**
	 * Wertet die Ausgewählte Antwort einer RadioGroup aus und gibt dem entsprechend JA/NEIN/UNBEKANNT zurück
	 */
	public static Answer decideRadioAnswer(int radioIdAnswer, int radioIdJa, int radioIdUnbekannt, int radioIdNein) {
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

	/**
	 * auswerten der Eingabe qHWE1 aus der Activity
	 */
	public static Answer decideQHEW1GreaterEqualBHQ1(Double qHWE1, Stauanlage stauanlage) {
		if (!Double.isNaN(qHWE1) && !Double.isNaN(stauanlage.bHQ1InCbmProSekunde)) {
			if (qHWE1 >= stauanlage.bHQ1InCbmProSekunde) {
				return Answer.JA;
			} else {
				return Answer.NEIN;
			}
		} else {
			return Answer.UNBEKANNT;
		}
	}

	/**
	 * auswerten der Eingabe qHWE2 aus der Activity
	 */
	public static Answer decideQHEW2GreaterEqualBHQ2(Double qHWE2, Stauanlage stauanlage) {
		if (!Double.isNaN(qHWE2) && !Double.isNaN(stauanlage.bHQ2InCbmProSekunde)) {
			if (qHWE2 >= stauanlage.bHQ1InCbmProSekunde) {
				return Answer.JA;
			} else {
				return Answer.NEIN;
			}
		} else {
			return Answer.UNBEKANNT;
		}
	}

	/**
	 * Methode wandel String in Double um und überprüft den String auf nicht zulässige Zeichen
	 * @param number String der von einem EditText mit inputOption NumberDecimal kommt
	 * @return String number in Double, gibt Double.NaN zurück bei unzulässigem String
	 */
	public static Double safeParseStringToDouble(String number){
		Double convertetNumb;
		// regex: ziffern 0 bis 9 bliebig oft(auch kein mal)[*]
		// Es kann ein . folgen[?] (einmal oder keinmal)
		// 		Wenn Punkt folgt muss mindestens 1[+] Ziffer 0-9 danach stehen
		if(number.matches("[0-9]*(\\.[0-9]+)?") && !number.isEmpty()){
			convertetNumb = Double.valueOf(number);
		}else{
			convertetNumb = Double.NaN;
		}
		return convertetNumb;
	}

	public static void loadAnswerInRadioGroup(Answer answer , RadioButton btnJa, RadioButton btnNein, RadioButton btnUnbekannt){
		switch (answer){
			case JA:
				btnJa.setChecked(true);
				btnNein.setChecked(false);
				btnUnbekannt.setChecked(false);
				break;
			case NEIN:
				btnNein.setChecked(true);
				btnJa.setChecked(false);
				btnUnbekannt.setChecked(false);
				break;
			case UNBEKANNT:
				btnUnbekannt.setChecked(true);
				btnNein.setChecked(false);
				btnJa.setChecked(false);
		}
	}
}
