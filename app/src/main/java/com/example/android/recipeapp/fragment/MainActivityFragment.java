package com.example.android.recipeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.adapter.RecipeCardRecyclerViewAdapter;
import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.viewmodel.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecipeCardRecyclerViewAdapter recipeRecyclerViewAdapter;

    private RecipeViewModel viewModel;

    private TextView errorMessage;


    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recipeRecyclerViewAdapter = new RecipeCardRecyclerViewAdapter(getActivity(), new ArrayList<Recipe>());
        recyclerView = view.findViewById(R.id.recyclerview_main);
        recyclerView.setAdapter(recipeRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        errorMessage = view.findViewById(R.id.error_message_display);

        setupViewModel();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        fetchContent();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);

    }

    private void fetchContent() {
        viewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if (recipes.size() > 0) {
                    recipeRecyclerViewAdapter.updateRecipeList(recipes);

                    recyclerView.setVisibility(View.VISIBLE);
                    errorMessage.setVisibility(View.GONE);

                } else {
                    errorMessage.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });

        viewModel.getRecipeList();
    }

}
