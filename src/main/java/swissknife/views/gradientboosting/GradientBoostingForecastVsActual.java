package swissknife.views.gradientboosting;

import swissknife.modal.classifier.gradientboosting.GBPredictVsActual;
import swissknife.modal.classifier.gradientboosting.GradientBoosting;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class GradientBoostingForecastVsActual extends JInternalFrame
{
    public GradientBoostingForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new GBPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
