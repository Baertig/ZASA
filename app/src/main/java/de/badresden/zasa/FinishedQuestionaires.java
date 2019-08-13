package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class FinishedQuestionaires extends AppCompatActivity {
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
					public void onChanged(List<StauanlageSimplyfied> stauanlageSimplyfiedList) {
						adapter.setStauanlageSimplyfiedList(stauanlageSimplyfiedList);
					}
				});
	}
}
