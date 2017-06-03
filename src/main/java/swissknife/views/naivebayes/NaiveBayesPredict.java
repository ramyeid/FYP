package swissknife.views.naivebayes;

import swissknife.panels.naivebayes.NaiveBayesPanel;

import javax.swing.*;

public class NaiveBayesPredict extends JInternalFrame
{
    public NaiveBayesPredict(String pathCsv)
    {
        setBounds(100,100,450,300);
        NaiveBayesPanel panel = new NaiveBayesPanel(pathCsv,1);
        this.add(panel);
    }
}