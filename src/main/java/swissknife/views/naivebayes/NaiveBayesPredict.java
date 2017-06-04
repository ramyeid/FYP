package swissknife.views.naivebayes;

import swissknife.panels.naivebayes.NaiveBayesPanel;

import javax.swing.*;

public class NaiveBayesPredict extends JInternalFrame
{
    public NaiveBayesPredict(String pathCsv)
    {
        new NaiveBayesPanel(pathCsv,2,this);
    }
}

