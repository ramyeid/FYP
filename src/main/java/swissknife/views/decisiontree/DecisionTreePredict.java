package swissknife.views.decisiontree;

import swissknife.modal.classifier.decisiontree.DTPredict;
import swissknife.panels.classifier.ClassifierPanel;
import swissknife.views.MainWindowFrame;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class DecisionTreePredict extends JInternalFrame
{
    public DecisionTreePredict(String pathCsv, MainWindowFrame mainFrame)
    {
        this.add(new ClassifierPanel(new DTPredict(),pathCsv,this,mainFrame));
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
