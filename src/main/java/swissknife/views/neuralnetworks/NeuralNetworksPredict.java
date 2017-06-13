package swissknife.views.neuralnetworks;

import swissknife.modal.classifier.randomforest.RFPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/13/17.
 */
public class NeuralNetworksPredict extends JInternalFrame
{
    public NeuralNetworksPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new RFPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
