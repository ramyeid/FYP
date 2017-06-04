package swissknife.views.timeseriesanalysis;

import swissknife.panels.timeseriesanalysis.TimeSeriesAnalysisPanel;

import javax.swing.*;

public class TimeSeriesPredict extends JInternalFrame
{
    public TimeSeriesPredict(String pathCsv,JFrame mainFrame)
    {
        new TimeSeriesAnalysisPanel(pathCsv,1,this,mainFrame);

    }
}