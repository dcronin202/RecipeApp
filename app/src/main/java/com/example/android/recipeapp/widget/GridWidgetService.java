package com.example.android.recipeapp.widget;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.SummaryActivity;
import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.viewmodel.RecipeRepository;

import java.util.ArrayList;
import java.util.List;


public class GridWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext());
    }

}

class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory, LifecycleOwner {


    private Context mContext;
    private RecipeRepository recipeRepository;
    private List<Recipe> mRecipeListDetails;


    public GridRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;
        mRecipeListDetails = new ArrayList<>();
    }

    @Override
    public void onCreate() {
        if (recipeRepository == null) {
            recipeRepository = new RecipeRepository((Application)mContext);
        }
        recipeRepository.callRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipeRepository.getRecipeDetails();
    }

    @Override
    public void onDataSetChanged() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                getRecipes().observeForever(new Observer<List<Recipe>>() {
                    @Override
                    public void onChanged(List<Recipe> recipes) {
                        int previousCount = getCount();
                        mRecipeListDetails = recipes;
                        if (mRecipeListDetails.size() != previousCount) {
                            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
                            ComponentName thisWidget = new ComponentName(mContext.getApplicationContext(), RecipeWidgetProvider.class);
                            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
                            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.grid_view);
                        }
                    }
                });
            }
        });

    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_recipe_item);
        remoteViews.setTextViewText(R.id.appwidget_recipe_name, mRecipeListDetails.get(position).getRecipeName());

        Bundle extras = new Bundle();
        extras.putParcelable(SummaryActivity.RECIPE_DETAILS,  mRecipeListDetails.get(position));
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        remoteViews.setOnClickFillInIntent(R.id.appwidget_recipe_name, fillInIntent);

        return remoteViews;

    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        if (mRecipeListDetails == null) {
            return 0;
        }
        return mRecipeListDetails.size();
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1; // Treat all items in the GridView the same
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
