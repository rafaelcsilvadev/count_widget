import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.RemoteViews

class CountWidget : AppWidgetProvider() {

        override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
            super.onUpdate(context, appWidgetManager, appWidgetIds)

           // val views = RemoteViews(context.packageName, R.layout.appwidget_provider_layout) 
           // views.setTextViewText(R.id.appwidget_text, "Widget Inicial")
        }

        override fun onReceive(context: Context, intent: Intent) {}
    
}
