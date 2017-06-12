package swissknife.views.gradientboosting;

import swissknife.modal.classifier.gradientboosting.GBPredict;
import swissknife.modal.classifier.gradientboosting.GradientBoosting;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class GradientBoostingPredict extends JInternalFrame
{
    public GradientBoostingPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new GBPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
