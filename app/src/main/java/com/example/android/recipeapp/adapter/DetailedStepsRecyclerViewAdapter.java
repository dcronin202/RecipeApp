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
import com.example.android.recipeapp.data.RecipeStep;

import java.util.List;


public class DetailedStepsRecyclerViewAdapter extends RecyclerView.Adapter<DetailedStepsRecyclerViewAdapter.ViewHolder> {

    private final static String LOG_TAG = DetailedStepsRecyclerViewAdapter.class.getSimpleName();

    private List<RecipeStep> mRecipeStepDetails;
    private Activity mContext;

    public DetailedStepsRecyclerViewAdapter(Activity mContext, List<RecipeStep> mRecipeStepDetails) {
        this.mContext = mContext;
        this.mRecipeStepDetails = mRecipeStepDetails;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_detailed_steps, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Log.d(LOG_TAG, "Detailed Steps onBindViewHolder called.");
        final RecipeStep stepDetails = mRecipeStepDetails.get(position);

        viewHolder.stepId.setText(String.valueOf(stepDetails.getRecipeStepId() + 1));
        viewHolder.videoUrl.setText(stepDetails.getRecipeVideoUrl());
        viewHolder.stepContent.setText(stepDetails.getRecipeDescriptionLong());

    }

    @Override
    public int getItemCount() {
        if (mRecipeStepDetails == null) {
            return 0;
        } else {
            return mRecipeStepDetails.size();
        }
    }

    public void updateDetailedStepList(List<RecipeStep> recipeStepsContent) {
        this.mRecipeStepDetails = recipeStepsContent;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stepId;
        TextView videoUrl;
        TextView stepContent;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stepId = itemView.findViewById(R.id.detailed_step_counter_list_item);
            videoUrl = itemView.findViewById(R.id.video_url_list_item);
            stepContent = itemView.findViewById(R.id.detailed_step_text_list_item);
            parentLayout = itemView.findViewById(R.id.detailed_steps_list_layout);

        }

    }
}
