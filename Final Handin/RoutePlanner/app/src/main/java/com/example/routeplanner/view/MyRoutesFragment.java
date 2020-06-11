package com.example.routeplanner.view;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
import com.example.routeplanner.adapters.MyRoutesAdapter;
import com.example.routeplanner.model.MyRoute;
import com.example.routeplanner.viewmodel.MyRoutesFragmentViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class MyRoutesFragment extends Fragment {
    private MyRoutesFragmentViewModel viewModel;
    private MyRoutesAdapter adapter;
    private Drawable deleteIcon;
    private ProgressBar progressBar;
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

    public MyRoutesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View view = inflater.inflate(R.layout.my_routes_fragment, container, false);
        progressBar = view.findViewById(R.id.progress_bar);

        deleteIcon = ContextCompat.getDrawable(Objects.requireNonNull(this.getContext()), R.drawable.ic_delete);

        viewModel = new ViewModelProvider(this).get(MyRoutesFragmentViewModel.class);

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else progressBar.setVisibility(View.GONE);
            }
        });
        viewModel.getRoutesData().observe(getViewLifecycleOwner(), new Observer<ArrayList<MyRoute>>() {

            @Override
            public void onChanged(ArrayList<MyRoute> routes) {
                adapter.setRoutes(routes);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_my_routes);
        adapter = new MyRoutesAdapter();
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar, menu);

        MenuItem addRoute = menu.findItem(R.id.add);
        addRoute.getActionView();

        addRoute.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getActivity(), AddRouteActivity.class);
                startActivity(intent);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
