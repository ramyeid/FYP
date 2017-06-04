package swissknife.views.naivebayes;

import swissknife.panels.naivebayes.NaiveBayesPanel;

import javax.swing.*;

public class NaiveBayesForecastVsActual extends JInternalFrame
{
    public NaiveBayesForecastVsActual(String pathCsv)
    {
        this.add(new NaiveBayesPanel(pathCsv,1,this));
        this.setVisible(true);
        this.pack();
    }
}
