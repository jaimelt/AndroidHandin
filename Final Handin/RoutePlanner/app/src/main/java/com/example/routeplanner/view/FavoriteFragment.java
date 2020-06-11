package com.example.routeplanner.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.routeplanner.R;
import com.example.routeplanner.adapters.FavoriteAdapter;
import com.example.routeplanner.model.MyRoute;
import com.example.routeplanner.viewmodel.FavoriteFragmentViewModel;

import java.util.List;
import java.util.Objects;

public class FavoriteFragment extends Fragment {
    private FavoriteAdapter adapter;
    private FavoriteFragmentViewModel viewModel;
    private Drawable deleteIcon;
    private ItemTouchHelper.Callback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            viewModel.removeRoute(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            View itemView = viewHolder.itemView;
            final ColorDrawable background = new ColorDrawable(Color.parseColor("#BF1633"));
            background.setBounds(itemView.getLeft(), itemView.getTop(), ((int) dX), itemView.getBottom());
            background.draw(c);

            int iconMargin = (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
            deleteIcon.setBounds(itemView.getLeft() + iconMargin, itemView.getTop() + iconMargin, itemView.getLeft() + iconMargin + deleteIcon.getIntrinsicWidth(), itemView.getBottom() - iconMargin);
            deleteIcon.draw(c);

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    public FavoriteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        deleteIcon = ContextCompat.getDrawable(Objects.requireNonNull(this.getContext()), R.drawable.ic_delete);

        viewModel = new ViewModelProvider(this).get(FavoriteFragmentViewModel.class);

        viewModel.getRoutes().observe(getViewLifecycleOwner(), new Observer<List<MyRoute>>() {
            @Override
            public void onChanged(List<MyRoute> myRoutes) {
                adapter.setRoutes(myRoutes);
            }
        });
        return inflater.inflate(R.layout.favorite_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.favorite);
        adapter = new FavoriteAdapter();
        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
    }
}
