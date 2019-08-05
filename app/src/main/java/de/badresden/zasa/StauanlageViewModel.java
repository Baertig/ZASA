package de.badresden.zasa;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StauanlageViewModel extends AndroidViewModel {
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

	public void insert(Stauanlage stauanlage){
		mRepository.insert(stauanlage);
	}

	public void update(Stauanlage stauanlage){
		mRepository.update(stauanlage);
	}

}
