package swissknife.views.lineardiscriminant;

import swissknife.modal.classifier.lineardiscriminant.LDPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class LinearDiscriminantPredict extends JInternalFrame
{
    public LinearDiscriminantPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new LDPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
