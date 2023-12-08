package designpatterns;

public class DisplayBoard extends Observer {
    public DisplayBoard(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
        this.weatherStation.registerObserver(this);
    }

    @Override
    public void update() {
        float temp = weatherStation.getTemperature();
        float humidity = weatherStation.getHumidity();
        float pressure = weatherStation.getPressure();
        System.out.println("Updated Weather Measurements: Temp=" + temp + " Humidity=" + humidity + " Pressure=" + pressure);
    }
}
