package com.example.count_widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

object ClickCounter {
    var clickCount = 0
}

class CountWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        updateAppWidget(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        val appWidgetManager = AppWidgetManager.getInstance(context)
        val views = RemoteViews(context.packageName, R.layout.count_widget)

        when (intent.action) {
            "BUTTON_PLUS_ACTION" -> {
                if (ClickCounter.clickCount < 9) {
                    ClickCounter.clickCount++
                }
            }
            "BUTTON_SUB_ACTION" -> {
                if (ClickCounter.clickCount > 0) {
                    ClickCounter.clickCount--
                }
            }
        }

        // Atualizar o texto do TextView no widget
        views.setTextViewText(R.id.appwidget_text, ClickCounter.clickCount.toString())

        val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context, CountWidget::class.java))
        appWidgetIds.forEach { appWidgetId ->
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { appWidgetId ->
            val views = RemoteViews(context.packageName, R.layout.count_widget)

            val buttonPlusIntent = Intent(context, CountWidget::class.java).apply {
                action = "BUTTON_PLUS_ACTION"
            }
            val buttonPlusPendingIntent = PendingIntent.getBroadcast(
                context, 0, buttonPlusIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.button_plus, buttonPlusPendingIntent)

            val buttonSubIntent = Intent(context, CountWidget::class.java).apply {
                action = "BUTTON_SUB_ACTION"
            }
            val buttonSubPendingIntent = PendingIntent.getBroadcast(
                context, 0, buttonSubIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.button_sub, buttonSubPendingIntent)

            // Atualizar o texto do TextView no widget
            views.setTextViewText(R.id.appwidget_text, ClickCounter.clickCount.toString())

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
