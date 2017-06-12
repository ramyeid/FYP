package swissknife.views.decisiontree;

import swissknife.modal.classifier.decisiontree.DTPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class DecisionTreePredict extends JInternalFrame
{
    public DecisionTreePredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new DTPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
