package com.example.android.recipeapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.android.recipeapp.adapter.StepsPagerAdapter;
import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.fragment.RecipeStepsFragment;
import com.example.android.recipeapp.fragment.SummaryActivityFragment;
import com.example.android.recipeapp.viewmodel.RecipeStepsViewModel;
import com.google.android.material.tabs.TabLayout;


public class RecipeStepsActivity extends AppCompatActivity {

    private static final String LOG_TAG = RecipeStepsActivity.class.getSimpleName();

    public static final String RECIPE_STEPS = "recipe_steps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        Intent intent = getIntent();
        Recipe recipeStepDetails = intent.getParcelableExtra(RECIPE_STEPS);

        RecipeStepsViewModel viewModel = ViewModelProviders.of(this).get(RecipeStepsViewModel.class);

        viewModel.setRecipeStepDetails(recipeStepDetails);

        SummaryActivityFragment stepsFragment = new SummaryActivityFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.recipe_steps_activity_content, stepsFragment)
                .commit();

        //ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        //setupViewPager(viewPager);

        //TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        //tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {

        final StepsPagerAdapter adapter = new StepsPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new RecipeStepsFragment(), "Step 1");

        viewPager.setAdapter(adapter);

    }

}
