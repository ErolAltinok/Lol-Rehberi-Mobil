package com.example.ErolMobil;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_REFRESH_CLICK = "com.example.ErolMobil.WIDGET_REFRESH";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        for (int appWidgetId : appWidgetIds) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            String randomTactic = dbHelper.getRandomTactic();
            views.setTextViewText(R.id.tvWidgetCharacterName, randomTactic);

            Intent intent = new Intent(context, MyWidgetProvider.class);
            intent.setAction(ACTION_REFRESH_CLICK);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            views.setOnClickPendingIntent(R.id.btnWidgetRefresh, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        // Gereksiz kod tekrarını önlemek için buton tıklamasını ve genel güncellemeyi birleştirdik
        if (ACTION_REFRESH_CLICK.equals(intent.getAction()) || AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisWidget = new ComponentName(context, MyWidgetProvider.class);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

            onUpdate(context, appWidgetManager, appWidgetIds);
        }
    }
}