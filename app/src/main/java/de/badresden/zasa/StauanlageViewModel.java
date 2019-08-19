package de.badresden.zasa;

import android.app.Application;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StauanlageViewModel extends AndroidViewModel {

	public static Stauanlage stauanlage;
	private StauanlageRepository mRepository;
	private LiveData <List<StauanlageSimplyfied>> mAllStauanlagenSimplyfied; //TODO: aktualisiert sich irgendwie nicht richtig


	public StauanlageViewModel(@NonNull Application application) {
		super(application);
		mRepository = new StauanlageRepository(application);
		mAllStauanlagenSimplyfied = mRepository.getAllStauanlagenSimplyfied();
		stauanlage = new Stauanlage("useless placeholder Name"); //FIXME
	}

	public LiveData<List<StauanlageSimplyfied>> getAllStauanlagenSimplyfied(){
		return mAllStauanlagenSimplyfied;
	}

	// das private Feld wird gespeichert
	public void insert(){
		mRepository.insert(stauanlage);
        //mAllStauanlagenSimplyfied = mRepository.getAllStauanlagenSimplyfied();
	}

	public void update(Stauanlage stauanlage){
		mRepository.update(stauanlage);
	}

	public void createStauanlage(){
		stauanlage = new Stauanlage("useless placeholder Name");//FIXME
	}
	public void updateStauanlage(String nameDerAnlage, String geographischeLage, String gestautesGewaesser, String eigentuemerBetreiber,
								 String artDesAbsperrauwerkes, int hoeheAbsperrwerkUeberGruendung, int stauinhaltInCbm, int bhq1InCbmProSekunde, int bhq2InCbmProSekunde,
								 Answer betriebsvorschriftNormalfallLiegtVor, Answer betriebsvorschriftHochwasserLiegtVor,Date datumUndUhrzeitLetzteBearbeitung){

		stauanlage.updateAllgemein(nameDerAnlage, geographischeLage, gestautesGewaesser, eigentuemerBetreiber,
									artDesAbsperrauwerkes, hoeheAbsperrwerkUeberGruendung, stauinhaltInCbm, bhq1InCbmProSekunde,
									bhq2InCbmProSekunde, betriebsvorschriftNormalfallLiegtVor, betriebsvorschriftHochwasserLiegtVor,datumUndUhrzeitLetzteBearbeitung);
	}

	public void loadStauanlageWith(String nameDerAnlage, Date datumUndUhrzeitLetzteBearbeitung){
		stauanlage = mRepository.getStauanlageWith(nameDerAnlage,datumUndUhrzeitLetzteBearbeitung).get(0); //FIXME Fehlerbehandlung wenn mehrer Stauanlagen gefunden wurden
	}

	public Answer decideRadioAnswer(int radioIdAnswer,int radioIdJa, int radioIdUnbekannt, int radioIdNein){
		if (radioIdAnswer == radioIdJa){
			return Answer.JA;
		}else if(radioIdAnswer == radioIdUnbekannt){
			return Answer.UNBEKANNT;
		}else if(radioIdAnswer == radioIdNein){
			return Answer.NEIN;
		}else {
			return null; //FIXME sollte eigentlich nicht eintreten ... vllt sollte ein Exception geworfen werden ?
		}

	}
}
