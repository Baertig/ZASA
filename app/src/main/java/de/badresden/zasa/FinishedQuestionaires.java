package de.badresden.zasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class FinishedQuestionaires extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finished_questionaires);
		RecyclerView recyclerView = findViewById(R.id.recyclerview);
		final StauanlageSimplyfiedListAdapter adapter = new StauanlageSimplyfiedListAdapter(this);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}
}
