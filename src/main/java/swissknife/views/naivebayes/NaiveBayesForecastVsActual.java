package swissknife.views.naivebayes;

import swissknife.panels.naivebayes.NaiveBayesPanel;

import javax.swing.*;

public class NaiveBayesForecastVsActual extends JInternalFrame
{
    public NaiveBayesForecastVsActual(String pathCsv)
    {
        new NaiveBayesPanel(pathCsv,1,this);

    }
}
