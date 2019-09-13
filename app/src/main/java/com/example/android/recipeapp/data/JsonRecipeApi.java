package com.example.android.recipeapp.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonRecipeApi {

    @GET("baking.json")
    Call<List<Recipe>> getRecipes();

}
