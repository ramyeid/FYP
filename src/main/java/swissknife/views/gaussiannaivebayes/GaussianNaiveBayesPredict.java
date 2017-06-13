//package swissknife.views.naivebayes;
//
//import swissknife.panels.gaussiannaivebayes.NaiveBayesPanel;
//
//import javax.swing.*;
//
//public class GaussianNaiveBayesPredict extends JInternalFrame
//{
//    public GaussianNaiveBayesPredict(String pathCsv)
//    {
//        this.add(new NaiveBayesPanel(pathCsv,2,this));
//        this.setVisible(true);
//        this.pack();
//    }
//}
//
package swissknife.views.gaussiannaivebayes;

import swissknife.modal.classifier.gaussiannaivebayes.GNBPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class GaussianNaiveBayesPredict extends JInternalFrame
{
    public GaussianNaiveBayesPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new GNBPredict(),pathCsv,this,mainFrame));
        this.setVisible(true);
        this.pack();
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                super.internalFrameClosing(e);
                JMenu predictVsActualMenu = (JMenu) mainFrame.getJMenuBar().getMenu(2).getMenuComponent(2);
                JMenu predictMenu = (JMenu) mainFrame.getJMenuBar().getMenu(2).getMenuComponent(1);

                for (int i = 0; i < predictVsActualMenu.getItemCount(); ++i) {
                    predictVsActualMenu.getItem(i).removeActionListener(predictVsActualMenu.getItem(i).getActionListeners()[0]);
                }

                for (int i = 0; i < predictMenu.getItemCount(); ++i) {
                    predictMenu.getItem(i).removeActionListener(predictMenu.getItem(i).getActionListeners()[0]);
                }

                predictVsActualMenu.setEnabled(false);
                predictMenu.setEnabled(false);
            }
        });
    }
}

