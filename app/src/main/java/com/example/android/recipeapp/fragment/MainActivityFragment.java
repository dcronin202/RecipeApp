package com.example.android.recipeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.adapter.RecipeRecyclerViewAdapter;
import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.viewmodel.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivityFragment extends Fragment {

    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;

    private RecipeViewModel viewModel;


    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        setupViewModel();

        populateRecipeCards(view);

        return view;

    }


    private void setupViewModel() {

        viewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);
        viewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if (recipes.size() > 0) {
                    recipeRecyclerViewAdapter.updateRecipeList(recipes);
                }
            }
        });

        viewModel.getRecipeList();

    }

    private void populateRecipeCards(View parentView) {

        recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(getActivity(), new ArrayList<Recipe>());

        recyclerView = parentView.findViewById(R.id.recyclerview_main);
        recyclerView.setAdapter(recipeRecyclerViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

}
