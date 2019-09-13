package com.example.android.recipeapp;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recipeapp.data.Recipe;

import java.util.ArrayList;
import java.util.List;


public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {

    private static final String LOG_TAG = RecipeRecyclerViewAdapter.class.getSimpleName();

    private List<Recipe> mRecipeDetails;
    private Activity mContext;

    public RecipeRecyclerViewAdapter(Activity mContext, List<Recipe> mRecipeDetails) {
        this.mContext = mContext;
        this.mRecipeDetails = mRecipeDetails;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recipes, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Log.d(LOG_TAG, "onBindViewHolder called.");
        final Recipe recipeDetails = mRecipeDetails.get(position);

        viewHolder.recipeName.setText(String.valueOf(recipeDetails.getRecipeName()));
        viewHolder.servingSize.setText(String.valueOf(recipeDetails.getServingSize()));

    }

    @Override
    public int getItemCount() {
        if (mRecipeDetails == null) {
            return 0;
        } else {
            return mRecipeDetails.size();
        }
    }

    public void updateRecipeList(List<Recipe> recipeContent) {
        this.mRecipeDetails = recipeContent;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView recipeName;
        TextView servingSize;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeName = itemView.findViewById(R.id.recipe_name);
            servingSize = itemView.findViewById(R.id.serving_size);
            parentLayout = itemView.findViewById(R.id.recipe_list_layout);

        }
    }

}
