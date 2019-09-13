package com.example.android.recipeapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


public class RecipeViewModel extends AndroidViewModel {

    private static final String LOG_TAG = RecipeViewModel.class.getSimpleName();

    private RecipeRepository recipeRepository;


    public RecipeViewModel(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);

    }

    public void getRecipeList() {
        recipeRepository.callRecipes();
    }


}
