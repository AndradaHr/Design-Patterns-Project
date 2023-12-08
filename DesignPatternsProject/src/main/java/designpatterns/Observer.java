package designpatterns;

public abstract class Observer {
    protected WeatherStation weatherStation;
    public abstract void update();
}