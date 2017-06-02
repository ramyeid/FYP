package swissknife.views.timeseriesanalysis;

import swissknife.panels.timeseriesanalysis.TimeSeriesAnalysisPanel;

import javax.swing.*;

public class TimeSeriesPredict extends JInternalFrame
{
    public TimeSeriesPredict(String pathCsv)
    {
        setBounds(100,100,450,300);
        TimeSeriesAnalysisPanel panel = new TimeSeriesAnalysisPanel(pathCsv,1);
        this.add(panel);

    }
}