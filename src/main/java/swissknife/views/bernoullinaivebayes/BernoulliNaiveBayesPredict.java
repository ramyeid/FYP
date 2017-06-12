package swissknife.views.bernoullinaivebayes;

import swissknife.modal.classifier.bernoullinaivebayes.BNBPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by ramyeid on 6/12/17.
 */
public class BernoulliNaiveBayesPredict extends JInternalFrame{
    public BernoulliNaiveBayesPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new BNBPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}


