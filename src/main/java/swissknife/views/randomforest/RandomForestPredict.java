package swissknife.views.randomforest;

import swissknife.modal.classifier.randomforest.RFPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class RandomForestPredict extends JInternalFrame
{
    public RandomForestPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new RFPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
