package de.badresden.zasa.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import de.badresden.zasa.R;
import de.badresden.zasa.StauanlageSimplyfied;
import de.badresden.zasa.StauanlageViewModel;

public class FinishedQuestionairesActivity extends AppCompatActivity {
	private StauanlageViewModel mStauanlageViewModel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finished_questionaires);
		RecyclerView recyclerView = findViewById(R.id.recyclerview);
		final StauanlageSimplyfiedListAdapter adapter = new StauanlageSimplyfiedListAdapter(this);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		mStauanlageViewModel = ViewModelProviders.of(this).get(StauanlageViewModel.class);
		mStauanlageViewModel.getAllStauanlagenSimplyfied().observe(this,
				new Observer<List<StauanlageSimplyfied>>() {
					@Override
					public void onChanged(@Nullable final List<StauanlageSimplyfied> stauanlageSimplyfiedList) {
						adapter.setStauanlageSimplyfiedList(stauanlageSimplyfiedList);
					}
				});
	}
}
