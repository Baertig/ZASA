package de.badresden.zasa;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import de.badresden.zasa.Fragments.deleteStauanlageDialogFragment;
import de.badresden.zasa.StauanlageSimplyfied;
import de.badresden.zasa.activities.StauanlageSimplyfiedListAdapter;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

	private Drawable icon;
	private final ColorDrawable background;
	private StauanlageSimplyfiedListAdapter mAdapter;
	private int deletedStauanlagePosition;



	public SwipeToDeleteCallback(StauanlageSimplyfiedListAdapter adapter) {
		super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
		mAdapter = adapter;
		icon = ContextCompat.getDrawable(mAdapter.getContext(), R.drawable.ic_delete_black_24dp);
		background = new ColorDrawable(Color.RED);
	}

	@Override
	public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
		return false;
	}

	@Override
	public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
		int position = viewHolder.getAdapterPosition();

	}

	@Override
	public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
							@NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
							int actionState, boolean isCurrentlyActive) {
		super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
		View itemView = viewHolder.itemView;
		int backgroundCornerOffset = 20;
		int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
		int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
		int iconBottom = iconTop + icon.getIntrinsicHeight();

		if (dX > 0) { // Swiping to the right
			int iconLeft = itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
			int iconRight = itemView.getLeft() + iconMargin;
			icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

			background.setBounds(itemView.getLeft(), itemView.getTop(),
					itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
					itemView.getBottom());

		} else if (dX < 0) { // Swiping to the left
			int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
			int iconRight = itemView.getRight() - iconMargin;
			icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

			background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
					itemView.getTop(), itemView.getRight(), itemView.getBottom());
		} else { // view is unSwiped
			background.setBounds(0, 0, 0, 0);
		}
		background.draw(c);
		icon.draw(c);
	}
}
