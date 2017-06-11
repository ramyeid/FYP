package swissknife.views.ridge;

import swissknife.modal.classifier.ridge.RCPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class RidgePredict extends JInternalFrame
{
    public RidgePredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new RCPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
