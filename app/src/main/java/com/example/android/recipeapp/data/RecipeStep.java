package com.example.android.recipeapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class RecipeStep implements Parcelable {

    @SerializedName("id")
    private int recipeStepId;

    @SerializedName("shortDescription")
    private String recipeDescriptionShort;

    @SerializedName("description")
    private String recipeDescriptionLong;

    @SerializedName("videoURL")
    private String recipeVideoUrl;

    @SerializedName("thumbnailURL")
    private String recipeThumbnailUrl;


    // GETTERS
    public int getRecipeStepId() {
        return recipeStepId;
    }

    public String getRecipeDescriptionShort() {
        return recipeDescriptionShort;
    }

    public String getRecipeDescriptionLong() {
        return recipeDescriptionLong;
    }

    public String getRecipeVideoUrl() {
        return recipeVideoUrl;
    }

    public String getRecipeThumbnailUrl() {
        return recipeThumbnailUrl;
    }

    // SETTERS
    public void setRecipeStepId(int recipeStepId) {
        this.recipeStepId = recipeStepId;
    }

    public void setRecipeDescriptionShort(String recipeDescriptionShort) {
        this.recipeDescriptionShort = recipeDescriptionShort;
    }

    public void setRecipeDescriptionLong(String recipeDescriptionLong) {
        this.recipeDescriptionLong = recipeDescriptionLong;
    }

    public void setRecipeVideoUrl(String recipeVideoUrl) {
        this.recipeVideoUrl = recipeVideoUrl;
    }

    public void setRecipeThumbnailUrl(String recipeThumbnailUrl) {
        this.recipeThumbnailUrl = recipeThumbnailUrl;
    }


    // Code for Parcels
    private RecipeStep(Parcel p) {
        recipeStepId = p.readInt();
        recipeDescriptionShort = p.readString();
        recipeDescriptionLong = p.readString();
        recipeVideoUrl = p.readString();
        recipeThumbnailUrl = p.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(recipeStepId);
        parcel.writeString(recipeDescriptionShort);
        parcel.writeString(recipeDescriptionLong);
        parcel.writeString(recipeVideoUrl);
        parcel.writeString(recipeThumbnailUrl);

    }

    public final static Parcelable.Creator<RecipeStep> CREATOR = new Parcelable.Creator<RecipeStep>(){
        @Override
        public RecipeStep createFromParcel(Parcel parcel) {
            return new RecipeStep(parcel);
        }

        @Override
        public RecipeStep[] newArray(int i) {
            return new RecipeStep[i];
        }
    };
}
