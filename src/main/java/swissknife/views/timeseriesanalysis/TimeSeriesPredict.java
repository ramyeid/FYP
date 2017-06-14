package swissknife.views.timeseriesanalysis;

import swissknife.panels.timeseriesanalysis.TimeSeriesAnalysisPanel;
import swissknife.views.MainWindowFrame;

import javax.swing.*;

public class TimeSeriesPredict extends JInternalFrame
{
    public TimeSeriesPredict(String pathCsv,MainWindowFrame mainFrame)
    {
        this.add(new TimeSeriesAnalysisPanel(pathCsv,1,this,mainFrame));
        this.setVisible(true);
        this.pack();

    }
}