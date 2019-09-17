package com.example.android.recipeapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.fragment.SummaryActivityFragment;
import com.example.android.recipeapp.viewmodel.RecipeViewModel;

public class SummaryActivity extends AppCompatActivity {

    private static final String LOG_TAG = SummaryActivity.class.getSimpleName();

    public static final String RECIPE_DETAILS = "recipes";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();
        Recipe recipeDetails = intent.getParcelableExtra(RECIPE_DETAILS);

        RecipeViewModel viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        viewModel.setRecipeDetails(recipeDetails);

        SummaryActivityFragment overviewFragment = new SummaryActivityFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.summary_activity_content, overviewFragment)
                .commit();
    }

}
