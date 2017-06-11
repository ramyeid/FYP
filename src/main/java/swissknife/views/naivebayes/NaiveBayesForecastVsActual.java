package swissknife.views.naivebayes;

import swissknife.modal.classifier.naivebayes.NBPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

public class NaiveBayesForecastVsActual extends JInternalFrame
{
    public NaiveBayesForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new NBPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
