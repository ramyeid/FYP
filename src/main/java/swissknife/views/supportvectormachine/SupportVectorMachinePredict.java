package swissknife.views.supportvectormachine;

import swissknife.modal.classifier.svm.SVMPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class SupportVectorMachinePredict extends JInternalFrame
{
    public SupportVectorMachinePredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new SVMPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
