package swissknife.views.extratree;

import swissknife.modal.classifier.extratreeclassifier.ETPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class ExtraTreePredict extends JInternalFrame
{
    public ExtraTreePredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new ETPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
