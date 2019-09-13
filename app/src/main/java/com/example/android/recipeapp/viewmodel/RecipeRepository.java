package com.example.android.recipeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import com.example.android.recipeapp.data.JsonRecipeApi;
import com.example.android.recipeapp.data.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRepository {

    private static final String LOG_TAG = RecipeRepository.class.getSimpleName();

    private static final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    private JsonRecipeApi jsonRecipeApi;
    private ArrayList<Recipe> recipes;


    RecipeRepository(Application application) {
        recipes = new ArrayList<Recipe>();
    }

    public ArrayList<Recipe> getRecipeDetails() {
        return recipes;
    }


    // RETROFIT Methods

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RECIPE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private void getJsonRecipeApi() {
        jsonRecipeApi = retrofit.create(JsonRecipeApi.class);
    }

    public void callRecipes() {
        if (jsonRecipeApi == null) {
            getJsonRecipeApi();
        }

        Call<List<Recipe>> call = jsonRecipeApi.getRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                onRecipeResponseReceived(response);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
            }
        });

    }

    private void onRecipeResponseReceived(Response<List<Recipe>> response) {

        if (response.isSuccessful()) {

            List<Recipe> recipeList = response.body();
            //ArrayList<Recipe> recipeDetails = (ArrayList<Recipe>) recipeResponse.getRecipeIngredients();
            recipes.addAll(recipeList);

        } else {
            Log.e(LOG_TAG, "Code: " + response.code());

        }
    }

}
