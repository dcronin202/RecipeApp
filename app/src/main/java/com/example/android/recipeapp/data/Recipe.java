package com.example.android.recipeapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Recipe implements Parcelable {

    @SerializedName("id")
    private int recipeId;

    @SerializedName("name")
    private String recipeName;

    @SerializedName("ingredients")
    public List<Ingredient> recipeIngredients;

    @SerializedName("steps")
    public List<RecipeStep> recipeSteps;

    @SerializedName("servings")
    private int servingSize;


    // GETTERS
    public int getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public List<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public List<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    public int getServingSize() {
        return servingSize;
    }


    // SETTERS
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setRecipeIngredients(List<Ingredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public void setRecipeSteps(List<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }


    // Code for Parcels
    private Recipe(Parcel p) {
        recipeId = p.readInt();
        recipeName = p.readString();
        recipeIngredients = new ArrayList<Ingredient>();
        p.readList(recipeIngredients, Ingredient.class.getClassLoader());
        recipeSteps = new ArrayList<RecipeStep>();
        p.readList(recipeSteps, RecipeStep.class.getClassLoader());
        servingSize = p.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(recipeId);
        parcel.writeString(recipeName);
        parcel.writeList(recipeIngredients);
        parcel.writeList(recipeSteps);
        parcel.writeInt(servingSize);

    }

    public final static Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>(){
        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int i) {
            return new Recipe[i];
        }
    };

}

