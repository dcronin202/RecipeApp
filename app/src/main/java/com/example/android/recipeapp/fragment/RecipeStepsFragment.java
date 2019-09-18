package com.example.android.recipeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.adapter.DetailedStepsRecyclerViewAdapter;
import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.viewmodel.RecipeStepsViewModel;


public class RecipeStepsFragment extends Fragment {

    private static final String LOG_TAG = RecipeStepsFragment.class.getSimpleName();

    private RecipeStepsViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(RecipeStepsViewModel.class);

        populateMovieDetails(view);

        return view;

    }

    private void populateMovieDetails(View view) {

        final Recipe recipe = viewModel.getRecipeStepDetails();

        TextView recipeStepCount = view.findViewById(R.id.details_step_counter);
        recipeStepCount.setText(recipe.getRecipeSteps().get(0).getRecipeStepId());

        TextView videoUrl = view.findViewById(R.id.details_video_url);
        videoUrl.setText(recipe.getRecipeSteps().get(0).getRecipeVideoUrl());

        TextView recipeStepContent = view.findViewById(R.id.details_step_content);
        recipeStepContent.setText(recipe.getRecipeSteps().get(0).getRecipeDescriptionLong());

    }

    /*private void populateStepsDetails(View parentView) {

        final Recipe recipe = viewModel.getRecipeStepDetails();

        recyclerView = parentView.findViewById(R.id.recyclerview_recipe_steps);

        stepsRecyclerViewAdapter = new DetailedStepsRecyclerViewAdapter(getActivity(), recipe.getRecipeSteps());
        recyclerView.setAdapter(stepsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }*/

}
