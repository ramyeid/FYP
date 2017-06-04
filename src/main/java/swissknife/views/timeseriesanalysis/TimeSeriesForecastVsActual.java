package swissknife.views.timeseriesanalysis;

import swissknife.panels.timeseriesanalysis.TimeSeriesAnalysisPanel;

import javax.swing.*;

public class TimeSeriesForecastVsActual extends JInternalFrame
{
    public TimeSeriesForecastVsActual(String pathCsv,JFrame mainFrame)
    {
        new TimeSeriesAnalysisPanel(pathCsv,2,this,mainFrame);
    }

}