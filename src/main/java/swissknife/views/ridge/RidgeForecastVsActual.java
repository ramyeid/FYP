package swissknife.views.ridge;

import swissknife.modal.classifier.ridge.RCPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class RidgeForecastVsActual extends JInternalFrame
{
    public RidgeForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new RCPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
