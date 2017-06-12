//package swissknife.views.naivebayes;
//
//import swissknife.panels.gaussiannaivebayes.NaiveBayesPanel;
//
//import javax.swing.*;
//
//public class NaiveBayesPredict extends JInternalFrame
//{
//    public NaiveBayesPredict(String pathCsv)
//    {
//        this.add(new NaiveBayesPanel(pathCsv,2,this));
//        this.setVisible(true);
//        this.pack();
//    }
//}
//
package swissknife.views.naivebayes;

import swissknife.modal.classifier.gaussiannaivebayes.GNBPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

public class NaiveBayesPredict extends JInternalFrame
{
    public NaiveBayesPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new GNBPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}

