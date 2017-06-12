package swissknife.views.extratree;

import swissknife.modal.classifier.extratreeclassifier.ETPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class ExtraTreeForecastVsActual extends JInternalFrame
{
    public ExtraTreeForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new ETPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
