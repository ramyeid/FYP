package swissknife.views.lineardiscriminant;

import swissknife.modal.classifier.lineardiscriminant.LDPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class LinearDiscriminantForecastVsActual extends JInternalFrame
{
    public LinearDiscriminantForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new LDPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
