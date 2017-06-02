package views.timeseriesanalysis;

import panels.timeseriesanalysis.TimeSeriesAnalysisPanel;

import javax.swing.*;

public class TimeSeriesContinuousForecast extends JInternalFrame
{
    public TimeSeriesContinuousForecast(String pathCsv)
    {
        setBounds(100,100,450,300);
        this.add(new TimeSeriesAnalysisPanel(pathCsv,4));
    }

}