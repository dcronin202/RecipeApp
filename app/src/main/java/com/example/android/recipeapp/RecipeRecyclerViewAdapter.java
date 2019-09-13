package com.example.android.recipeapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recipeapp.data.Recipe;

import java.util.ArrayList;


public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {

    private static final String LOG_TAG = RecipeRecyclerViewAdapter.class.getSimpleName();

    private ArrayList<Recipe> mRecipeDetails;
    private Activity mContext;

    public RecipeRecyclerViewAdapter(Activity mContext, ArrayList<Recipe> mRecipeDetails) {
        this.mContext = mContext;
        this.mRecipeDetails = mRecipeDetails;
    }


    @NonNull
    @Override
    public RecipeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recipes, viewGroup, false);
        RecipeRecyclerViewAdapter.ViewHolder viewHolder = new RecipeRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeRecyclerViewAdapter.ViewHolder viewHolder, final int position) {

        final Recipe recipeDetails = mRecipeDetails.get(position);

        viewHolder.recipeName.setText(recipeDetails.getRecipeName());
        viewHolder.servingSize.setText(recipeDetails.getServingSize());

    }

    @Override
    public int getItemCount() {
        if (mRecipeDetails == null) {
            return 0;
        } else {
            return mRecipeDetails.size();
        }
    }

    public void updateRecipeList(ArrayList<Recipe> recipeContent) {
        this.mRecipeDetails = recipeContent;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView recipeName;
        TextView servingSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeName = itemView.findViewById(R.id.recipe_name);
            servingSize = itemView.findViewById(R.id.serving_size);

        }
    }

}
