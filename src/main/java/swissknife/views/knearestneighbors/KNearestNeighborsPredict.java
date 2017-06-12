package swissknife.views.knearestneighbors;

import swissknife.modal.classifier.knearestneighbors.KNNPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class KNearestNeighborsPredict extends JInternalFrame
{
    public KNearestNeighborsPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new KNNPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
