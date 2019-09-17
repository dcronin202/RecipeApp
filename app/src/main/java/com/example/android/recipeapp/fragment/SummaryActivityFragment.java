package com.example.android.recipeapp.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.adapter.IngredientsRecyclerViewAdapter;
import com.example.android.recipeapp.adapter.SimpleStepsRecyclerViewAdapter;
import com.example.android.recipeapp.data.Ingredient;
import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.data.RecipeStep;
import com.example.android.recipeapp.viewmodel.RecipeDetailViewModel;
import com.example.android.recipeapp.viewmodel.RecipeViewModel;

import java.util.ArrayList;

public class SummaryActivityFragment extends Fragment {

    private static final String LOG_TAG = SummaryActivityFragment.class.getSimpleName();

    public static final String RECIPE_DETAILS = "recipes";

    private RecipeDetailViewModel viewModel;

    private RecyclerView recyclerView;
    private IngredientsRecyclerViewAdapter ingredientsRecyclerViewAdapter;
    private SimpleStepsRecyclerViewAdapter simpleStepsRecyclerViewAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(RecipeDetailViewModel.class);

        populateRecipeDetails(view);

        populateIngredientDetails(view);

        populateRecipeSteps(view);

        return view;

    }

    private void populateRecipeDetails(View view) {

        final Recipe recipe = viewModel.getRecipeDetails();

        TextView overviewUrl = view.findViewById(R.id.overview_video_url);
        overviewUrl.setText(recipe.getRecipeSteps()
                .get(recipe.getRecipeSteps().size()-1)
                .getRecipeVideoUrl());

        TextView recipeName = view.findViewById(R.id.overview_recipe_name);
        recipeName.setText(recipe.getRecipeName());

        // For showing serving size
        Resources resources = getResources();
        String servingSize = String.valueOf(recipe.getServingSize());
        String servingSizeText = resources.getString(R.string.summary_serving_size, servingSize);
        TextView servings = view.findViewById(R.id.overview_serving_size);
        servings.setText(servingSizeText);
    }

    private void populateIngredientDetails(View parentView) {

        final Recipe recipe = viewModel.getRecipeDetails();

        recyclerView = parentView.findViewById(R.id.recyclerview_ingredients);

        ingredientsRecyclerViewAdapter = new IngredientsRecyclerViewAdapter(getActivity(), recipe.getRecipeIngredients());
        recyclerView.setAdapter(ingredientsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void populateRecipeSteps(View parentView) {

        final Recipe recipe = viewModel.getRecipeDetails();

        recyclerView = parentView.findViewById(R.id.recyclerview_simple_steps);

        simpleStepsRecyclerViewAdapter = new SimpleStepsRecyclerViewAdapter(getActivity(), recipe.getRecipeSteps());
        recyclerView.setAdapter(simpleStepsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

}
