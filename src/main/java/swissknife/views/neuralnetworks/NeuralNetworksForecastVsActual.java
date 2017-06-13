package swissknife.views.neuralnetworks;

import swissknife.modal.classifier.randomforest.RFPredictVsActual;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/13/17.
 */
public class NeuralNetworksForecastVsActual extends JInternalFrame
{
    public NeuralNetworksForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new RFPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
