package swissknife.views.linearregression;

import swissknife.panels.linearregression.LinearRegressionPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/6/17.
 */
public class LinearRegressionForecastVsActual extends JInternalFrame
{
    public LinearRegressionForecastVsActual(String pathCsv)
    {
        this.add(new LinearRegressionPanel(pathCsv,1,this));
        this.setVisible(true);
        this.pack();
    }
}
