package com.example.android.recipeapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.data.Recipe;
import com.example.android.recipeapp.viewmodel.RecipeRepository;

import java.util.List;


public class RecipeIntentService extends IntentService implements LifecycleOwner {

    public static final String ACTION_UPDATE_RECIPE_WIDGET = "update_recipe_widget";
    private RecipeRepository recipeRepository;
    private Recipe recipeDetails;


    public RecipeIntentService() {
        super("RecipeIntentService");
    }

    public static void startActionUpdateRecipeWidget(Context context) {
        Intent intent = new Intent(context, RecipeIntentService.class);
        intent.setAction(ACTION_UPDATE_RECIPE_WIDGET);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_RECIPE_WIDGET.equals(action)) {
                handleActionUpdateRecipeWidget();
            }

        }
    }

    // TODO: ??
    private void handleActionUpdateRecipeWidget() {

        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        final int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidgetProvider.class));

        getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.grid_view);
                RecipeWidgetProvider.updateAppWidget(getApplicationContext(), appWidgetManager, appWidgetIds);
            }
        });

    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipeRepository.getRecipeDetails();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
