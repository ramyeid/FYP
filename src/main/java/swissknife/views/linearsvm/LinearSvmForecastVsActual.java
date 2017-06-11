package swissknife.views.linearsvm;

import swissknife.modal.classifier.linearsvm.LSVCPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class LinearSvmForecastVsActual extends JInternalFrame
{
    public LinearSvmForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new LSVCPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
