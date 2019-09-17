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

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.data.RecipeStep;
import com.example.android.recipeapp.viewmodel.RecipeViewModel;

public class SummaryActivityFragment extends Fragment {

    private static final String LOG_TAG = SummaryActivityFragment.class.getSimpleName();

    public static final String RECIPE_DETAILS = "recipes";

    private RecipeViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);

        populateRecipeDetails(view);

        return view;

    }

    private void populateRecipeDetails(View view) {

        final Recipe recipe = viewModel.getRecipeDetails();
        final RecipeStep step = viewModel.getStepDetails();

        //TextView overviewUrl = view.findViewById(R.id.overview_video_url);
        //overviewUrl.setText(step.getRecipeVideoUrl());

        TextView recipeName = view.findViewById(R.id.overview_recipe_name);
        recipeName.setText(recipe.getRecipeName());

        // For showing serving size
        Resources resources = getResources();
        String servingSize = String.valueOf(recipe.getServingSize());
        String servingSizeText = resources.getString(R.string.summary_serving_size, servingSize);
        TextView servings = view.findViewById(R.id.overview_serving_size);
        servings.setText(servingSizeText);
    }

}
