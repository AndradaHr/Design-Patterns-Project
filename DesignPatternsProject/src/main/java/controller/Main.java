package controller;

import chart.WeatherChart;
import designpatterns.DisplayBoard;
import designpatterns.WeatherStation;

public class Main {
    public static void main(String[] args) {
        WeatherStation station = WeatherStation.getInstance();
        DisplayBoard board = new DisplayBoard(station);
        WeatherChart chart = new WeatherChart("Weather Chart");
        station.setWeatherChart(chart);

        station.startSimulation();
        chart.setVisible(true);
    }
}
