package swissknife.views.decisiontree;

import swissknife.modal.classifier.decisiontree.DTPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class DecisionTreeForecastVsActual extends JInternalFrame
{
    public DecisionTreeForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new DTPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
