package com.example.android.recipeapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Ingredient implements Parcelable {

    @SerializedName("quantity")
    private double ingredientQuantity;

    @SerializedName("measure")
    private String ingredientMeasure;

    @SerializedName("ingredient")
    private String ingredientName;


    // GETTERS
    public double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public String getIngredientMeasure() {
        return ingredientMeasure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    // SETTERS
    public void setIngredientQuantity(double ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public void setIngredientMeasure(String ingredientMeasure) {
        this.ingredientMeasure = ingredientMeasure;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    // Code for Parcels
    private Ingredient(Parcel p) {
        ingredientQuantity = p.readDouble();
        ingredientMeasure = p.readString();
        ingredientName = p.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(ingredientQuantity);
        parcel.writeString(ingredientMeasure);
        parcel.writeString(ingredientName);

    }

    public final static Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>(){
        @Override
        public Ingredient createFromParcel(Parcel parcel) {
            return new Ingredient(parcel);
        }

        @Override
        public Ingredient[] newArray(int i) {
            return new Ingredient[i];
        }
    };

}
