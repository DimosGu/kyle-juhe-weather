package com.kyletung.kylejuheweather;

/**
 * Description:
 * <br>Created on 15-8-24.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.2
 */
public class ForecastDetail {

    private String forecastDay;
    private String forecastDayWeather;
    private String forecastDayTemperatureTop;
    private String forecastDayTemperatureBottom;

    public String getForecastDay() {
        return forecastDay;
    }

    public void setForecastDay(String forecastDay) {
        this.forecastDay = forecastDay;
    }

    public String getForecastDayWeather() {
        return forecastDayWeather;
    }

    public void setForecastDayWeather(String forecastDayWeather) {
        this.forecastDayWeather = forecastDayWeather;
    }

    public String getForecastDayTemperatureTop() {
        return forecastDayTemperatureTop;
    }

    public void setForecastDayTemperatureTop(String forecastDayTemperatureTop) {
        this.forecastDayTemperatureTop = forecastDayTemperatureTop;
    }

    public String getForecastDayTemperatureBottom() {
        return forecastDayTemperatureBottom;
    }

    public void setForecastDayTemperatureBottom(String forecastDayTemperatureBottom) {
        this.forecastDayTemperatureBottom = forecastDayTemperatureBottom;
    }
}
