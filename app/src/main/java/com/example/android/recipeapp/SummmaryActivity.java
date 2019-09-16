package com.example.android.recipeapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.fragment.SummaryActivityFragment;
import com.example.android.recipeapp.viewmodel.RecipeViewModel;

public class SummmaryActivity extends AppCompatActivity {

    private static final String LOG_TAG = SummmaryActivity.class.getSimpleName();

    public static final String RECIPE_DETAILS = "recipes";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_overview);

        Intent intent = getIntent();
        Recipe recipeDetails = intent.getParcelableExtra(RECIPE_DETAILS);

        RecipeViewModel viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        //TODO: Fix this so it isn't returning null
        viewModel.setRecipeDetails(recipeDetails);

        SummaryActivityFragment overviewFragment = new SummaryActivityFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.overview_activity, overviewFragment)
                .commit();
    }

}
