package com.example.android.recipeapp.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.data.Ingredient;

import java.util.List;

public class IngredientsRecyclerViewAdapter extends RecyclerView.Adapter<IngredientsRecyclerViewAdapter.ViewHolder> {

    private static final String LOG_TAG = IngredientsRecyclerViewAdapter.class.getSimpleName();

    private List<Ingredient> mIngredientDetails;
    private Activity mContext;


    public IngredientsRecyclerViewAdapter(Activity mContext, List<Ingredient> mIngredientDetails) {
        this.mContext = mContext;
        this.mIngredientDetails = mIngredientDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_ingredients, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        Log.d(LOG_TAG, "Ingredients onBindViewHolder called.");
        final Ingredient ingredientDetails = mIngredientDetails.get(position);

        viewHolder.ingredientName.setText(ingredientDetails.getIngredientName());
        viewHolder.ingredientMeasure.setText(ingredientDetails.getIngredientMeasure());
        viewHolder.ingredientQuantity.setText(String.valueOf(ingredientDetails.getIngredientQuantity()));

    }

    @Override
    public int getItemCount() {
        if (mIngredientDetails == null) {
            return 0;
        } else {
            return mIngredientDetails.size();
        }
    }

    public void updateIngredientList(List<Ingredient> ingredientContent) {
        this.mIngredientDetails = ingredientContent;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientName;
        TextView ingredientMeasure;
        TextView ingredientQuantity;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.ingredient_name_list_item);
            ingredientMeasure = itemView.findViewById(R.id.ingredient_measure_list_item);
            ingredientQuantity = itemView.findViewById(R.id.ingredient_quantity_list_item);
            parentLayout = itemView.findViewById(R.id.ingredients_list_layout);

        }

    }

}
