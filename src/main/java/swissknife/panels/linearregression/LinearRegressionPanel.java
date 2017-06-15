package swissknife.panels.linearregression;

import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.modal.Tool;
import swissknife.modal.test.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramyeid on 6/4/17.
 */
public class LinearRegressionPanel extends JPanel implements ActionListener {


    //action = 1 - Predict vs actual
    //action = 2 - Predict Only


    String inputFile;
    String actionName;


    ButtonGroup keysToPredictButtonGroup;
    JPanel radioButtonsPanelKeysToPredict;
    List<JRadioButton> radioButtonListKeysToPredict;

//    JFrame frame;

    TextField actionTimeField;
    JButton submitButton;
    JPanel southPanel;


    Test testTool;
    Label mseErrorLabel;

    JInternalFrame masterFrame;
    String algorithmName;


    public LinearRegressionPanel(Tool tool,String inputFile,JInternalFrame masterFrame){
//        super(new BorderLayout());

        testTool = (Test) tool;
        algorithmName = testTool.getAlgorithmName();
        actionName = Resources.getClassifierActionName(testTool.getAction());

        this.inputFile = inputFile;
        submitButton = new JButton(actionName);
        keysToPredictButtonGroup = new ButtonGroup();
        radioButtonsPanelKeysToPredict = new JPanel();
        radioButtonListKeysToPredict = new ArrayList<>();
        actionTimeField = new TextField(3);
        southPanel = new JPanel();
        mseErrorLabel = new Label();
//        frame = new JFrame();

        String[] keysList = CSVReader.getColumnKeys(inputFile);
        Resources.createRadioButtons(keysList, keysToPredictButtonGroup, radioButtonsPanelKeysToPredict, radioButtonListKeysToPredict, "Choose Key To Predict",this);

        this.add(radioButtonsPanelKeysToPredict,BorderLayout.WEST);

        submitButton.addActionListener(this);


        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

        southPanel.add(new Label ("Action Time"));
        southPanel.add(actionTimeField);
        southPanel.add(submitButton);

        this.add(southPanel, BorderLayout.SOUTH);

        this.masterFrame = masterFrame;
        this.masterFrame.add(this);
        this.masterFrame.setVisible(true);
        this.masterFrame.pack();

        masterFrame.setTitle(algorithmName);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(submitButton)) {
            String keyToPredict = radioButtonListKeysToPredict.stream().filter(s -> s.isSelected()).findFirst().orElse(null).getText();
            String actionTime = actionTimeField.getText();
            testTool.build(inputFile, keyToPredict, actionTime);
            switch (actionName) {
                case Resources.LR_PREDICT:
                    testTool.action();
                    break;
                case Resources.LR_PREDICT_VS_ACTUAL:
                    testTool.action();
                    float error =  ((Test)testTool).getAccuracy();
                    mseErrorLabel.setText("MSE ERROR: "+error);
                    southPanel.add(mseErrorLabel);
                    masterFrame.revalidate();
                    masterFrame.repaint();
                    masterFrame.pack();
                    break;
            }

        }
    }

}
