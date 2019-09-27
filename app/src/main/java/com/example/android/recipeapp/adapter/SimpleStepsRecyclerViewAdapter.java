package com.example.android.recipeapp.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.data.RecipeStep;

import java.util.List;

public class SimpleStepsRecyclerViewAdapter extends RecyclerView.Adapter<SimpleStepsRecyclerViewAdapter.ViewHolder> {

    private final static String LOG_TAG = SimpleStepsRecyclerViewAdapter.class.getSimpleName();

    private List<RecipeStep> mRecipeStepDetails;
    private Activity mContext;

    public SimpleStepsRecyclerViewAdapter(Activity mContext, List<RecipeStep> mRecipeStepDetails) {
        this.mContext = mContext;
        this.mRecipeStepDetails = mRecipeStepDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_simple_steps, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        Log.d(LOG_TAG, "Simple Steps onBindViewHolder called.");
        final RecipeStep simpleStepsDetails = mRecipeStepDetails.get(position);

        //viewHolder.stepId.setText(String.valueOf(simpleStepsDetails.getRecipeStepId() + 1));

        viewHolder.stepContent.setText(simpleStepsDetails.getRecipeDescriptionLong());

        if (simpleStepsDetails.getRecipeStepId() == 0) {
            viewHolder.stepContent.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (mRecipeStepDetails == null) {
            return 0;
        } else {
            return mRecipeStepDetails.size();
        }
    }

    public void updateSimpleStepList(List<RecipeStep> simpleStepsContent) {
        this.mRecipeStepDetails = simpleStepsContent;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //TextView stepId;
        TextView stepContent;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //stepId = itemView.findViewById(R.id.simple_step_id_list_item);
            stepContent = itemView.findViewById(R.id.simple_step_text_list_item);
            parentLayout = itemView.findViewById(R.id.simple_steps_list_layout);
        }
    }
}
