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
	public static void createStauanlage() {
		stauanlage = new Stauanlage();
		isStauanlageLoadedFromDB = Boolean.FALSE;
	}
	/**
	 * leert den Zwischenspeicher
	 */
	public static void clear() {
		stauanlage = null;
		isStauanlageLoadedFromDB = Boolean.FALSE;
	}
}
