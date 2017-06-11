package swissknife.views.naivebayes;

import swissknife.modal.classifier.naivebayes.NBPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

public class NaiveBayesPredict extends JInternalFrame
{
    public NaiveBayesPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new NBPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}

