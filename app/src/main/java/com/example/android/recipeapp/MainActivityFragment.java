package com.example.android.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.viewmodel.RecipeViewModel;

import java.util.ArrayList;

public class MainActivityFragment extends Fragment {

    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;

    private RecipeViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setupViewModel();

        View view = inflater.inflate(R.layout.activity_main, container, false);

        recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(getActivity(), new ArrayList<Recipe>());
        recyclerView = view.findViewById(R.id.recyclerview_main);
        recyclerView.setAdapter(recipeRecyclerViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;

    }

    private void setupViewModel() {

        viewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);
        viewModel.getRecipeList();

    }

}
