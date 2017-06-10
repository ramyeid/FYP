package swissknife.views.linearregression;

import swissknife.modal.test.linearregression.LRPredict;
import swissknife.panels.linearregression.LinearRegressionPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/6/17.
 */
public class LinearRegressionPredict extends JInternalFrame
{
    public LinearRegressionPredict(String pathCsv)
    {
        this.add(new LinearRegressionPanel(new LRPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
