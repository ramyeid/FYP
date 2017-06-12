package swissknife.views.logisticregression;

import swissknife.modal.classifier.logisticregression.LogRPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class LogisticRegressionPredict extends JInternalFrame
{
    public LogisticRegressionPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new LogRPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
