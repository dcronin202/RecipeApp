package com.example.android.recipeapp.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.RecipeStepsActivity;
import com.example.android.recipeapp.adapter.IngredientsRecyclerViewAdapter;
import com.example.android.recipeapp.adapter.SimpleStepsRecyclerViewAdapter;
import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.viewmodel.RecipeDetailViewModel;


public class SummaryActivityFragment extends Fragment {

    private RecipeDetailViewModel viewModel;
    private Button button;

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

        setButton(view);

        return view;

    }

    private void populateRecipeDetails(View view) {
        final Recipe recipe = viewModel.getRecipeDetails();

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

        // Divider between ingredients
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        recyclerView.addItemDecoration(itemDecoration);

    }

    private void populateRecipeSteps(View parentView) {
        final Recipe recipe = viewModel.getRecipeDetails();

        recyclerView = parentView.findViewById(R.id.recyclerview_simple_steps);

        simpleStepsRecyclerViewAdapter = new SimpleStepsRecyclerViewAdapter(getActivity(), recipe.getRecipeSteps());
        recyclerView.setAdapter(simpleStepsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setButton(View view) {
        final Recipe recipe = viewModel.getRecipeDetails();

        button = view.findViewById(R.id.button_recipe_steps);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RecipeStepsActivity.class);
                intent.putExtra(RecipeStepsActivity.RECIPE_STEPS, recipe);
                startActivity(intent);
            }
        });
    }

}
