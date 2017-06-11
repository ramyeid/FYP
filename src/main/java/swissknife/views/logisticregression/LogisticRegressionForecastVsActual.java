package swissknife.views.logisticregression;

import swissknife.modal.classifier.logisticregression.LogRPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class LogisticRegressionForecastVsActual extends JInternalFrame
{
    public LogisticRegressionForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new LogRPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
