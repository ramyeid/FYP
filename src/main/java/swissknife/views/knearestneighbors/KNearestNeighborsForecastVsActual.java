package swissknife.views.knearestneighbors;

import swissknife.modal.classifier.knearestneighbors.KNNPredictVsActual;
import swissknife.modal.classifier.knearestneighbors.KNearestNeighbors;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class KNearestNeighborsForecastVsActual extends JInternalFrame
{
    public KNearestNeighborsForecastVsActual(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new KNNPredictVsActual(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
