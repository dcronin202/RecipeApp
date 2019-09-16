package com.example.android.recipeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.recipeapp.data.Recipe;

import java.util.List;


public class RecipeViewModel extends AndroidViewModel {

    private static final String LOG_TAG = RecipeViewModel.class.getSimpleName();

    private RecipeRepository recipeRepository;
    private Recipe recipeDetails;


    public RecipeViewModel(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        Log.d(LOG_TAG, "Actively retrieving recipes.");

    }

    public Recipe getRecipeDetails(){
        return recipeDetails;
    }

    public void setRecipeDetails(Recipe recipe) {
        this.recipeDetails = recipe;
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipeRepository.getRecipeDetails();
    }

    public void getRecipeList() {
        recipeRepository.callRecipes();
    }


}
