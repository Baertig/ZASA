package de.badresden.zasa;

import android.app.Application;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StauanlageViewModel extends AndroidViewModel {

	private Stauanlage stauanlage;
	private StauanlageRepository mRepository;
	private LiveData<List<Stauanlage>> mAllStauanlagen;
	private LiveData <List<StauanlageSimplyfied>> mAllStauanlagenSimplyfied;


	public StauanlageViewModel(@NonNull Application application) {
		super(application);
		mRepository = new StauanlageRepository(application);
		mAllStauanlagen = mRepository.getAllStauanlagen();
		mAllStauanlagenSimplyfied = mRepository.getAllStauanlagenSimplyfied();
	}
    // wrapper Methods:
	public LiveData<List<Stauanlage>> getAllStauanlagen(){
		return mAllStauanlagen;
	}

	public LiveData<List<StauanlageSimplyfied>> getAllStauanlagenSimplyfied(){
		return mAllStauanlagenSimplyfied;
	}

	// das private Feld wird gespeichert
	public void insert(){
		mRepository.insert(stauanlage);
	}

	public void update(Stauanlage stauanlage){
		mRepository.update(stauanlage);
	}

	public void createStauanlage(){
		stauanlage = new Stauanlage();
	}
	public void updateStauanlage(String nameDerAnlage, String geographischeLage, String gestautesGewaesser, String eigentuemerBetreiber,
								 String artDesAbsperrauwerkes, int hoeheAbsperrwerkUeberGruendung, int stauinhaltInCbm, int bhq1InCbmProSekunde, int bhq2InCbmProSekunde,
								 Answer betriebsvorschriftNormalfallLiegtVor, Answer betriebsvorschriftHochwasserLiegtVor){

		stauanlage.updateAllgemein(nameDerAnlage, geographischeLage, gestautesGewaesser, eigentuemerBetreiber,
									artDesAbsperrauwerkes, hoeheAbsperrwerkUeberGruendung, stauinhaltInCbm, bhq1InCbmProSekunde,
									bhq2InCbmProSekunde, betriebsvorschriftNormalfallLiegtVor, betriebsvorschriftHochwasserLiegtVor);
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
