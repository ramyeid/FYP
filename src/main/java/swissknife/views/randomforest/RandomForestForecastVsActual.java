package swissknife.views.randomforest;

import swissknife.modal.classifier.randomforest.RFPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class RandomForestForecastVsActual extends JInternalFrame
{
    public RandomForestForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new RFPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
