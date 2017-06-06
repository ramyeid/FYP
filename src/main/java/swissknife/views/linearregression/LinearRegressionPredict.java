package swissknife.views.linearregression;

import swissknife.panels.linearregression.LinearRegressionPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/6/17.
 */
public class LinearRegressionPredict extends JInternalFrame
{
    public LinearRegressionPredict(String pathCsv)
    {
        this.add(new LinearRegressionPanel(pathCsv,2,this));
        this.setVisible(true);
        this.pack();
    }
}
