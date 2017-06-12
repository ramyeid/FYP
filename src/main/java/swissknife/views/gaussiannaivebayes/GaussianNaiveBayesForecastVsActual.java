//package swissknife.views.naivebayes;
//
//import swissknife.panels.gaussiannaivebayes.NaiveBayesPanel;
//
//import javax.swing.*;
//
//public class GaussianNaiveBayesForecastVsActual extends JInternalFrame
//{
//    public GaussianNaiveBayesForecastVsActual(String pathCsv)
//    {
//        this.add(new NaiveBayesPanel(pathCsv,1,this));
//        this.setVisible(true);
//        this.pack();
//    }
//}
package swissknife.views.gaussiannaivebayes;

import swissknife.modal.classifier.gaussiannaivebayes.GNBPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

public class GaussianNaiveBayesForecastVsActual extends JInternalFrame
{
    public GaussianNaiveBayesForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new GNBPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
