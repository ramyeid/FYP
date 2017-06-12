package swissknife.views.linearsvm;

import swissknife.modal.classifier.linearsvm.LSVCPredict;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/11/17.
 */
public class LinearSvmPredict extends JInternalFrame
{
    public LinearSvmPredict(String pathCsv, JFrame mainFrame)
    {
        this.add(new ClassifierPanel(new LSVCPredict(),pathCsv,this));
        this.setVisible(true);
        this.pack();
    }
}
