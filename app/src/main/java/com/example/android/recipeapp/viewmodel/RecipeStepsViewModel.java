package com.example.android.recipeapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.android.recipeapp.data.Recipe;


public class RecipeStepsViewModel extends AndroidViewModel {

    private Recipe recipeStepDetails;

    public RecipeStepsViewModel(@NonNull Application application) {
        super(application);
    }

    public Recipe getRecipeStepDetails() {
        return recipeStepDetails;
    }

    public void setRecipeStepDetails(Recipe recipe) {
        this.recipeStepDetails = recipe;
    }

}
