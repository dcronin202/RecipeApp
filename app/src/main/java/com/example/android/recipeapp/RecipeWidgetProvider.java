package com.example.android.recipeapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    public static final String EXTRA_ITEM = "extra_item";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views;

        /* Intent to launch MainActivity when clicked
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Launch pending intent
        views.setOnClickPendingIntent(R.id.appwidget_recipe_name, pendingIntent);*/

        views = getRecipeGridRemoteView(context);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static RemoteViews getRecipeGridRemoteView(Context context) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_grid_view);

        // Set the GridWidgetService to act as the adapter for the GridView
        Intent intent = new Intent(context, GridWidgetService.class);
        remoteViews.setRemoteAdapter(R.id.widget_grid_view, intent);

        // Set the SummaryActivity to launch when clicked
        Intent appIntent = new Intent(context, SummaryActivity.class);
        PendingIntent appPendingIntent =
                PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.widget_grid_view, appPendingIntent);

        return remoteViews;

    }
}

