package swissknife.views.timeseriesanalysis;

import swissknife.panels.timeseriesanalysis.TimeSeriesAnalysisPanel;
import swissknife.views.MainWindowFrame;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class TimeSeriesForecastVsActual extends JInternalFrame
{
    public TimeSeriesForecastVsActual(String pathCsv,MainWindowFrame mainFrame)
    {
        this.add(new TimeSeriesAnalysisPanel(pathCsv,2,this,mainFrame));
        this.setVisible(true);
        this.pack();
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                super.internalFrameClosing(e);
                JMenu timeSeries = (JMenu) mainFrame.getJMenuBar().getMenu(3).getMenuComponent(3);
                JMenuItem forecastVsActual = (JMenuItem) timeSeries.getMenuComponent(1);

                if(forecastVsActual.getActionListeners().length!=0) {
                    forecastVsActual.removeActionListener(forecastVsActual.getActionListeners()[0]);
                }
                timeSeries.setEnabled(false);
                forecastVsActual.setEnabled(false);
            }
        });
    }

}