package swissknife.views.linearregression;

import swissknife.modal.classifier.linearregression.LinRPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;
import swissknife.views.MainWindowFrame;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * Created by joeabdelnour on 6/6/17.
 */
//public class LinearRegressionForecastVsActual extends JInternalFrame
//{
//    public LinearRegressionForecastVsActual(String pathCsv)
//    {
//        this.add(new LinearRegressionPanel(new LRPredictVsActual(),pathCsv,this));
//        this.setVisible(true);
//        this.pack();
//
//    }
//}


public class LinearRegressionForecastVsActual extends JInternalFrame
{
    public LinearRegressionForecastVsActual(String pathCsv, MainWindowFrame mainFrame)
    {
        this.add(new ClassifierPanel(new LinRPredictVsActual(),pathCsv,this,mainFrame));
        this.setVisible(true);
        this.pack();
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                super.internalFrameClosing(e);
                JMenu predictVsActualMenu = (JMenu) mainFrame.getJMenuBar().getMenu(2).getMenuComponent(2);
                JMenu predictMenu = (JMenu) mainFrame.getJMenuBar().getMenu(2).getMenuComponent(1);


                for(int i=0;i<predictVsActualMenu.getItemCount();++i){
                    if (predictVsActualMenu.getItem(i).getActionListeners().length!=0) {
                        predictVsActualMenu.getItem(i).removeActionListener(predictVsActualMenu.getItem(i).getActionListeners()[0]);
                    }
                }

                for(int i=0;i<predictMenu.getItemCount();++i) {
                    if (predictMenu.getItem(i).getActionListeners().length != 0) {
                        predictMenu.getItem(i).removeActionListener(predictMenu.getItem(i).getActionListeners()[0]);
                    }
                }

                predictVsActualMenu.setEnabled(false);
                predictMenu.setEnabled(false);

            }
        });
    }
}