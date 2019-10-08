package com.example.android.recipeapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.android.recipeapp.R;
import com.example.android.recipeapp.SummaryActivity;

import java.util.Objects;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    private static final String INTENT_UPDATE_ACTION = "android.appwidget.action.APPWIDGET_UPDATE";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews remoteViews = getRecipeGridRemoteView(context, appWidgetId);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent != null) {
            if (Objects.equals(INTENT_UPDATE_ACTION, intent.getAction())) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName thisWidget = new ComponentName(context.getApplicationContext(), RecipeWidgetProvider.class);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

                for (int i = 0; i < appWidgetIds.length; i++) {
                    RemoteViews remoteViews = getRecipeGridRemoteView(context, appWidgetIds[i]);

                    appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.grid_view);
                    onUpdate(context, appWidgetManager, appWidgetIds);
                }
            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static RemoteViews getRecipeGridRemoteView(Context context, int appWidgetId) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_grid_view);

        // Set the GridWidgetService to act as the adapter for the GridView
        Intent intent = new Intent(context, GridWidgetService.class);
        remoteViews.setRemoteAdapter(R.id.grid_view, intent);

        // Set the SummaryActivity to launch when clicked
        Intent appIntent = new Intent(context, SummaryActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.grid_view, pendingIntent);

        // If no recipes are found
        remoteViews.setEmptyView(R.id.grid_view, R.id.empty_view);

        return remoteViews;

    }
}

