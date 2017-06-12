package swissknife.views.bernoullinaivebayes;

import swissknife.modal.classifier.bernoullinaivebayes.BNBPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by ramyeid on 6/12/17.
 */
public class BernoulliNaiveBayesForecastVsActual extends JInternalFrame{
    public BernoulliNaiveBayesForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new BNBPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}

