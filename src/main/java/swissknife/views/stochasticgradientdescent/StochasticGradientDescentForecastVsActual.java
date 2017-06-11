package swissknife.views.stochasticgradientdescent;

import swissknife.modal.classifier.stochasticgradientdescent.SGDPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class StochasticGradientDescentForecastVsActual extends JInternalFrame
{
    public StochasticGradientDescentForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new SGDPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
