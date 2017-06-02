package views.timeseriesanalysis;

import panels.timeseriesanalysis.TimeSeriesAnalysisPanel;

import javax.swing.*;

public class TimeSeriesForecastOnce extends JInternalFrame
{
    public TimeSeriesForecastOnce(String pathCsv)
    {
        setBounds(100,100,450,300);
        this.add(new TimeSeriesAnalysisPanel(pathCsv,3));
    }

}