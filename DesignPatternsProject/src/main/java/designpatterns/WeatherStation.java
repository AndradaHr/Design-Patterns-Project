package designpatterns;

import chart.WeatherChart;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class WeatherStation {
    private static WeatherStation instance;
    private float temperature;
    private float humidity;
    private float pressure;
    private List<Observer> observers = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(WeatherStation.class.getName());

    // Historical data for chart plotting
    private Queue<Float> temperatureHistory = new LinkedList<>();
    private Queue<Float> humidityHistory = new LinkedList<>();
    private Queue<Float> pressureHistory = new LinkedList<>();

    // Alert thresholds
    private static final float TEMP_ALERT_THRESHOLD = 35.0f;
    private static final float HUMIDITY_ALERT_THRESHOLD = 75.0f;

    // Reference to the WeatherChart
    private WeatherChart weatherChart;

    private WeatherStation() {
        // Private constructor for Singleton pattern
    }

    public static WeatherStation getInstance() {
        if (instance == null) {
            instance = new WeatherStation();
        }
        return instance;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }


    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        logger.info("Weather data updated: Temp=" + temperature + " Humidity=" + humidity + " Pressure=" + pressure);

        // Update historical data
        updateHistory(temperature, humidity, pressure);

        // Check for alerts
        checkForAlerts();

        // Notify observers of the change
        measurementChanged();

        // Update the chart with new measurements
        if (weatherChart != null) {
            weatherChart.updateChart(getTemperatureHistory(), getHumidityHistory(), getPressureHistory());
        }
    }

    private void updateHistory(float temperature, float humidity, float pressure) {
        temperatureHistory.add(temperature);
        humidityHistory.add(humidity);
        pressureHistory.add(pressure);
    }

    private void checkForAlerts() {
        if (temperature > TEMP_ALERT_THRESHOLD) {
            logger.warning("High temperature alert! Current: " + temperature);
        }
        if (humidity > HUMIDITY_ALERT_THRESHOLD) {
            logger.warning("High humidity alert! Current: " + humidity);
        }
    }

    private void measurementChanged() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    // Return a copies to avoid modification
    public Queue<Float> getTemperatureHistory() {return new LinkedList<>(temperatureHistory);}

    public Queue<Float> getHumidityHistory() {
        return new LinkedList<>(humidityHistory);
    }

    public Queue<Float> getPressureHistory() {
        return new LinkedList<>(pressureHistory);
    }

    public void setWeatherChart(WeatherChart chart) {
        this.weatherChart = chart;
    }


    public void startSimulation() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();
                // Simulate random weather data
                float simulatedTemp = random.nextFloat() * 40;
                float simulatedHumidity = random.nextFloat() * 100;
                float simulatedPressure = 950 + random.nextFloat() * 100;
                setMeasurements(simulatedTemp, simulatedHumidity, simulatedPressure);
            }
        }, 0, 2000);
    }
}
