package swissknife.views.timeseriesanalysis;

import swissknife.panels.timeseriesanalysis.TimeSeriesAnalysisPanel;

import javax.swing.*;

public class TimeSeriesContinuousForecast extends JInternalFrame
{
    public TimeSeriesContinuousForecast(String pathCsv,JFrame mainFrame)
    {
        this.add(new TimeSeriesAnalysisPanel(pathCsv,4,this,mainFrame));
        this.setVisible(true);
        this.pack();
    }

}