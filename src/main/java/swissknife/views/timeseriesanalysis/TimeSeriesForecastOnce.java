package swissknife.views.timeseriesanalysis;

import swissknife.panels.timeseriesanalysis.TimeSeriesAnalysisPanel;

import javax.swing.*;

public class TimeSeriesForecastOnce extends JInternalFrame
{
    public TimeSeriesForecastOnce(String pathCsv,JFrame mainFrame)
    {
        this.add(new TimeSeriesAnalysisPanel(pathCsv,3,this,mainFrame));
        this.setVisible(true);
        this.pack();
    }

}