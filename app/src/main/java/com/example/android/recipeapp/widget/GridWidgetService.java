package com.example.android.recipeapp.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.android.recipeapp.R;
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
    private Cursor mCursor;
    private List<Recipe> mRecipeItem = new ArrayList<Recipe>();
    private RecipeRepository recipeRepository;
    private List<Recipe> mRecipeListDetails;


    public GridRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipeRepository.getRecipeDetails();
    }

    @Override
    public void onDataSetChanged() {
        getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                // TODO:  ???
            }
        });
    }

    // TODO: ??
    @Override
    public RemoteViews getViewAt(int position) {

        if (mCursor == null || mCursor.getCount() == 0) {
            return null;
        }

        mCursor.moveToPosition(position);

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_recipe_item);
        remoteViews.setTextViewText(R.id.appwidget_recipe_name, mRecipeItem.get(position).getRecipeName());

        Bundle extras = new Bundle();
        extras.putInt(RecipeWidgetProvider.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        remoteViews.setOnClickFillInIntent(R.id.appwidget_recipe_name, fillInIntent);

        return remoteViews;

    }

    @Override
    public void onDestroy() {
        mCursor.close();
    }

    @Override
    public int getCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
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
