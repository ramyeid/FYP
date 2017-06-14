package swissknife.views.timeseriesanalysis;

import swissknife.panels.timeseriesanalysis.TimeSeriesAnalysisPanel;
import swissknife.views.MainWindowFrame;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class TimeSeriesContinuousForecast extends JInternalFrame
{
    public TimeSeriesContinuousForecast(String pathCsv,MainWindowFrame mainFrame)
    {
        this.add(new TimeSeriesAnalysisPanel(pathCsv,4,this,mainFrame));
        this.setVisible(true);
        this.pack();

        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                super.internalFrameClosing(e);
                JMenu timeSeries = (JMenu) mainFrame.getJMenuBar().getMenu(2).getMenuComponent(3);
                JMenu continuousForecast = (JMenu) timeSeries.getMenuComponent(2);

                if(continuousForecast.getItem(0).getActionListeners().length!=0) {
                    continuousForecast.getItem(0).removeActionListener(continuousForecast.getItem(0).getActionListeners()[0]);
                }
                if(continuousForecast.getItem(1).getActionListeners().length!=0) {
                    continuousForecast.getItem(1).removeActionListener(continuousForecast.getItem(1).getActionListeners()[0]);
                }

                continuousForecast.setEnabled(false);

            }
        });
    }

}