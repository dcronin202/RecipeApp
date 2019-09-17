package com.example.android.recipeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recipeapp.SummaryActivity;
import com.example.android.recipeapp.R;
import com.example.android.recipeapp.data.Recipe;

import java.util.List;


public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {

    private static final String LOG_TAG = RecipeRecyclerViewAdapter.class.getSimpleName();

    private List<Recipe> mRecipeListDetails;
    private Activity mContext;
    private Recipe recipeDetails;


    public RecipeRecyclerViewAdapter(Activity mContext, List<Recipe> mRecipeDetails) {
        this.mContext = mContext;
        this.mRecipeListDetails = mRecipeDetails;
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
        final Recipe recipeDetails = mRecipeListDetails.get(position);

        viewHolder.recipeName.setText(String.valueOf(recipeDetails.getRecipeName()));
        viewHolder.servingSize.setText(String.valueOf(recipeDetails.getServingSize()));
        viewHolder.ingredientSize.setText(String.valueOf(recipeDetails.getRecipeIngredients().size()));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRecipeDetails(mContext, recipeDetails);
                Toast.makeText(mContext, recipeDetails.getRecipeName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void launchRecipeDetails(Context context, Recipe recipe) {
        Intent intent = new Intent(context, SummaryActivity.class);
        intent.putExtra(SummaryActivity.RECIPE_DETAILS, recipe);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (mRecipeListDetails == null) {
            return 0;
        } else {
            return mRecipeListDetails.size();
        }
    }

    public void updateRecipeList(List<Recipe> recipeContent) {
        this.mRecipeListDetails = recipeContent;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView recipeName;
        TextView servingSize;
        TextView ingredientSize;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeName = itemView.findViewById(R.id.recipe_name);
            servingSize = itemView.findViewById(R.id.serving_size);
            ingredientSize = itemView.findViewById(R.id.ingredients_size);
            parentLayout = itemView.findViewById(R.id.recipe_list_layout);

        }
    }

}
