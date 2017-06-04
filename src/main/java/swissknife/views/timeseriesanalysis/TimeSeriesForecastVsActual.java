package swissknife.views.timeseriesanalysis;

import swissknife.panels.timeseriesanalysis.TimeSeriesAnalysisPanel;

import javax.swing.*;

public class TimeSeriesForecastVsActual extends JInternalFrame
{
    public TimeSeriesForecastVsActual(String pathCsv,JFrame mainFrame)
    {
        this.add(new TimeSeriesAnalysisPanel(pathCsv,2,this,mainFrame));
        this.setVisible(true);
        this.pack();
    }

}