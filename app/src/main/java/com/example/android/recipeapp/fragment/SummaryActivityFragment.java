package com.example.android.recipeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.viewmodel.RecipeViewModel;

public class SummaryActivityFragment extends Fragment {

    private static final String LOG_TAG = SummaryActivityFragment.class.getSimpleName();

    public static final String RECIPE_DETAILS = "recipes";

    private RecipeViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_recipe_overview, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);

        populateRecipeDetails(view);

        return view;

    }

    private void populateRecipeDetails(View view) {

        final Recipe recipe = viewModel.getRecipeDetails();

        TextView recipeName = view.findViewById(R.id.overview_recipe_name);
        recipeName.setText(recipe.getRecipeName());


    }

}
