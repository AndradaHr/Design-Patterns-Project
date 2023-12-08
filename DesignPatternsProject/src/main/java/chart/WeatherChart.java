package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.Queue;

import javax.swing.*;

public class WeatherChart extends JFrame {
    private XYSeries temperatureSeries;
    private XYSeries humiditySeries;
    private XYSeries pressureSeries;

    public WeatherChart(String title) {
        super(title);

        this.temperatureSeries = new XYSeries("Temperature");
        this.humiditySeries = new XYSeries("Humidity");
        this.pressureSeries = new XYSeries("Pressure");

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(temperatureSeries);
        dataset.addSeries(humiditySeries);
        dataset.addSeries(pressureSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Weather Data",
                "Time",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
        pack();
        setResizable(false);
    }

    public void updateChart(Queue<Float> temperatureHistory, Queue<Float> humidityHistory, Queue<Float> pressureHistory) {
        temperatureSeries.clear();
        humiditySeries.clear();
        pressureSeries.clear();

        int time1 = 0;
        for (Float temp : temperatureHistory) {
            temperatureSeries.add(time1, temp);
            time1++;
        }

        int time2 = 0;
        for (Float humidity : humidityHistory) {
             humiditySeries.add(time2, humidity);
             time2++;
        }

        float time3 = 0;
        for (Float preasure : pressureHistory) {
            pressureSeries.add(time3,preasure);
            time3++;
        }

    }
}
