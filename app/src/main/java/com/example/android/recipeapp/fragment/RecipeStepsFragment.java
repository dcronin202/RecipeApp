package com.example.android.recipeapp.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.android.recipeapp.R;
import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.viewmodel.RecipeStepsViewModel;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class RecipeStepsFragment extends Fragment {

    private static final String LOG_TAG = RecipeStepsFragment.class.getSimpleName();

    private RecipeStepsViewModel viewModel;
    int recipeIndex = 0;

    private SimpleExoPlayer exoPlayer;
    private PlayerView playerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(RecipeStepsViewModel.class);

        if (getArguments() != null) {
            recipeIndex = getArguments().getInt("recipeIndex");
        }

        populateStepDetails(view, recipeIndex);

        initializePlayer(view, recipeIndex);

        return view;

    }

    private void populateStepDetails(View view, int recipeIndex) {

        final Recipe recipe = viewModel.getRecipeStepDetails();

        TextView recipeStepContent = view.findViewById(R.id.details_step_content);
        recipeStepContent.setText(String.valueOf(recipe.getRecipeSteps().get(recipeIndex).getRecipeDescriptionLong()));

    }

    // ExoPlayer Setup
    private void initializePlayer(View view, int recipeIndex) {
        final Recipe recipe = viewModel.getRecipeStepDetails();

        playerView = view.findViewById(R.id.details_step_player_view);
        String videoUrl = recipe.getRecipeSteps().get(recipeIndex).getRecipeVideoUrl();

        Uri videoUri = Uri.parse(videoUrl);
        String applicationName = getResources().getString(R.string.app_name);

        if (exoPlayer == null) {
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity());
            playerView.setPlayer(exoPlayer);

            //exoPlayer.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
            playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);

            DataSource.Factory dataSourceFactory =
                    new DefaultDataSourceFactory(getActivity(), Util.getUserAgent(getActivity(), applicationName));
            MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(videoUri);
            exoPlayer.prepare(videoSource);

        }

        if (videoUrl.length() == 0) {
            playerView.setVisibility(View.GONE);
        }

    }

    private void releasePlayer() {
        exoPlayer.stop();
        exoPlayer.release();
        exoPlayer = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

}
