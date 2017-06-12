package swissknife.views.supportvectormachine;

import swissknife.modal.classifier.svm.SVMPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class SupportVectorMachineForecastVsActual extends JInternalFrame
{
    public SupportVectorMachineForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new SVMPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
