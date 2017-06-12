package swissknife.views.stochasticgradientdescent;

import swissknife.modal.classifier.stochasticgradientdescent.SGDPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class StochasticGradientDescentPredict extends JInternalFrame
{
    public StochasticGradientDescentPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new SGDPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
