package com.kyletung.kylejuheweather;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

import org.json.JSONException;
import org.json.JSONObject;

public class DesktopWidget extends AppWidgetProvider {

    public static String city;

    RemoteViews view;

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        view = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        view.setImageViewResource(R.id.widget_background, R.drawable.widget_background);

        //set onclick
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.widget, pendingIntent);

        //update data
        Parameters parameters = new Parameters();
        //set city
        if (city == null) {
            city = "杭州";
        }
        parameters.add("cityname", city);
        JuheData.executeWithAPI(context, 39, "http://v.juhe.cn/weather/index", JuheData.GET, parameters, new DataCallBack() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    JSONObject result = new JSONObject(s).getJSONObject("result");
                    //today
                    JSONObject today = result.getJSONObject("today");
                    view.setTextViewText(R.id.widget_city, today.getString("city"));
                    view.setTextViewText(R.id.widget_temperature, today.getString("temperature"));
                    view.setTextViewText(R.id.widget_weather, today.getString("weather"));
                    view.setTextViewText(R.id.widget_wind, today.getString("wind"));
                    view.setTextViewText(R.id.widget_date_week, today.getString("date_y") + " " + today.getString("week"));
                    //weather image
                    view.setImageViewResource(R.id.widget_weather_image, setWeatherImage(today.getString("weather")));
                    //sk
                    JSONObject sk = result.getJSONObject("sk");
                    view.setTextViewText(R.id.widget_update_time, "更新于" + sk.getString("time"));
                    //commit
                    ComponentName componentName = new ComponentName(context, DesktopWidget.class);
                    appWidgetManager.updateAppWidget(componentName, view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                Toast.makeText(context, "天气组件更新完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {
                Toast.makeText(context, "天气组件更新失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals("com.kyletung.kylejuheweather.UPDATE_WIDGET")) {
            view = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            view.setImageViewResource(R.id.widget_background, R.drawable.widget_background);
            //update data
            Parameters parameters = new Parameters();
            //set city
            if (city == null) {
                city = "杭州";
            }
            parameters.add("cityname", city);
            JuheData.executeWithAPI(context, 39, "http://v.juhe.cn/weather/index", JuheData.GET, parameters, new DataCallBack() {
                @Override
                public void onSuccess(int i, String s) {
                    try {
                        JSONObject result = new JSONObject(s).getJSONObject("result");
                        //today
                        JSONObject today = result.getJSONObject("today");
                        view.setTextViewText(R.id.widget_city, today.getString("city"));
                        view.setTextViewText(R.id.widget_temperature, today.getString("temperature"));
                        view.setTextViewText(R.id.widget_weather, today.getString("weather"));
                        view.setTextViewText(R.id.widget_wind, today.getString("wind"));
                        view.setTextViewText(R.id.widget_date_week, today.getString("date_y") + " " + today.getString("week"));
                        //weather image
                        view.setImageViewResource(R.id.widget_weather_image, setWeatherImage(today.getString("weather")));
                        //sk
                        JSONObject sk = result.getJSONObject("sk");
                        view.setTextViewText(R.id.widget_update_time, "更新于" + sk.getString("time"));
                        //commit
                        ComponentName componentName = new ComponentName(context, DesktopWidget.class);
                        AppWidgetManager.getInstance(context).updateAppWidget(componentName, view);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    Toast.makeText(context, "天气组件更新完成", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int i, String s, Throwable throwable) {
                    Toast.makeText(context, "天气组件更新失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
        super.onReceive(context, intent);
    }

    public int setWeatherImage(String weather) {
        if (weather.equals("晴")) {
            return R.drawable.ic_weather_sunny_grey600_24dp;
        } else if (weather.equals("多云") || weather.equals("多云转晴")) {
            return R.drawable.ic_weather_partlycloudy_grey600_24dp;
        } else if (weather.equals("阴") || weather.equals("阵雨转阴") || weather.equals("阴转多云")) {
            return R.drawable.ic_weather_cloudy_grey600_24dp;
        } else if (weather.equals("阵雨") || weather.equals("雨夹雪") || weather.equals("小雨") || weather.equals("中雨") || weather.equals("冻雨") || weather.equals("小雨-中雨")) {
            return R.drawable.ic_weather_rainy_grey600_24dp;
        } else if (weather.equals("雷阵雨伴有冰雹")) {
            return R.drawable.ic_weather_hail_grey600_24dp;
        } else if (weather.equals("大雨") || weather.equals("暴雨") || weather.equals("大暴雨") || weather.equals("特大暴雨") || weather.equals("中雨-大雨") || weather.equals("大雨-暴雨") || weather.equals("暴雨-大暴雨") || weather.equals("大暴雨-特大暴雨")) {
            return R.drawable.ic_weather_pouring_grey600_24dp;
        } else if (weather.equals("阵雪") || weather.equals("小雪") || weather.equals("中雪") || weather.equals("大雪") || weather.equals("暴雪") || weather.equals("小雪-中雪") || weather.equals("中雪-大雪") || weather.equals("大雪-暴雪")) {
            return R.drawable.ic_weather_snowy_grey600_24dp;
        } else if (weather.equals("雾") || weather.equals("霾")) {
            return R.drawable.ic_weather_fog_grey600_24dp;
        } else if (weather.equals("沙尘暴") || weather.equals("扬沙") || weather.equals("强沙尘暴")) {
            return R.drawable.ic_weather_windy_grey600_24dp;
        } else if (weather.equals("浮尘")) {
            return R.drawable.ic_weather_windy_variant_grey600_24dp;
        } else if (weather.equals("雷阵雨")) {
            return R.drawable.ic_weather_lightning_grey600_24dp;
        } else {
            return R.drawable.ic_weather_cloudy_grey600_24dp;
        }
    }
}
