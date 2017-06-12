package swissknife.views.linearregression;

import swissknife.modal.test.linearregression.LRPredictVsActual;
import swissknife.panels.linearregression.LinearRegressionPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/6/17.
 */
public class LinearRegressionForecastVsActual extends JInternalFrame
{
    public LinearRegressionForecastVsActual(String pathCsv)
    {
        this.add(new LinearRegressionPanel(new LRPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
