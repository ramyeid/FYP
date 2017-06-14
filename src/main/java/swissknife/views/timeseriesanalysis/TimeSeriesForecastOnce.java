package swissknife.views.timeseriesanalysis;

import swissknife.panels.timeseriesanalysis.TimeSeriesAnalysisPanel;
import swissknife.views.MainWindowFrame;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class TimeSeriesForecastOnce extends JInternalFrame
{
    public TimeSeriesForecastOnce(String pathCsv,MainWindowFrame mainFrame)
    {
        this.add(new TimeSeriesAnalysisPanel(pathCsv,3,this,mainFrame));
        this.setVisible(true);
        this.pack();
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                super.internalFrameClosing(e);
                JMenu timeSeries = (JMenu) mainFrame.getJMenuBar().getMenu(2).getMenuComponent(3);
                JMenuItem forecastOnce = (JMenuItem) timeSeries.getMenuComponent(0);

                if(forecastOnce.getActionListeners().length!=0) {
                    forecastOnce.removeActionListener(forecastOnce.getActionListeners()[0]);
                }
                forecastOnce.setEnabled(false);
            }
        });
    }

}