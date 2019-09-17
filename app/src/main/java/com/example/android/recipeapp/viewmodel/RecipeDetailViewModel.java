package com.example.android.recipeapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.android.recipeapp.data.Recipe;


public class RecipeDetailViewModel extends AndroidViewModel {

    private Recipe recipeDetails;


    public RecipeDetailViewModel(@NonNull Application application) {
        super(application);
    }


    public Recipe getRecipeDetails(){
        return recipeDetails;
    }

    public void setRecipeDetails(Recipe recipe) {
        this.recipeDetails = recipe;
    }

}
