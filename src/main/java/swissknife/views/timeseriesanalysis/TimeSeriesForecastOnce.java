package swissknife.views.timeseriesanalysis;

import swissknife.panels.timeseriesanalysis.TimeSeriesAnalysisPanel;

import javax.swing.*;

public class TimeSeriesForecastOnce extends JInternalFrame
{
    public TimeSeriesForecastOnce(String pathCsv,JFrame mainFrame)
    {
        new TimeSeriesAnalysisPanel(pathCsv,3,this,mainFrame);
    }

}