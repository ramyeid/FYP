package swissknife.views.naivebayes;

import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.modal.Tool;
import swissknife.modal.naivebayes.modal.NBPredictVsActual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramyeid on 6/2/17.
 */
//TODO GEt error of action 1. Write it the last line of .csv.
public class Bla extends JInternalFrame
{

    //action = 1 - Predict And
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


    Tool nbTool;
    Label accuracyLabel;

    JPanel pan;

    public void resi (int h, int w)
    {
        this.resize(h,w);
    }


    public Bla(String inputFile,int action)
    {
        this.inputFile = inputFile;
        actionName = Resources.getNaiveBayesActionName(action);
        nbTool = Resources.getNaiveBayesTool(action);

        submitButton = new JButton(actionName);
        keysToPredictButtonGroup = new ButtonGroup();
        radioButtonsPanelKeysToPredict = new JPanel();
        radioButtonListKeysToPredict = new ArrayList<>();
        actionTimeField = new TextField(3);
        southPanel = new JPanel();
        accuracyLabel = new Label();
        pan = new JPanel();
//        frame = new JFrame();

        String[] keysList = CSVReader.getColumnKeys(inputFile);
        Resources.createRadioButtons(keysList, keysToPredictButtonGroup, radioButtonsPanelKeysToPredict, radioButtonListKeysToPredict, "Choose Key To Predict",pan);
        pan.add(radioButtonsPanelKeysToPredict,BorderLayout.WEST);

        submitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                    resi (500,500);

                String keyToPredict = radioButtonListKeysToPredict.stream().filter(s -> s.isSelected()).findFirst().orElse(null).getText();
                    String actionTime = actionTimeField.getText();
                    nbTool.build(inputFile, keyToPredict, actionTime);

                    switch (actionName)
                    {
                        case Resources.NB_PREDICT:
                            nbTool.action();
                            break;

                        case Resources.NB_PREDICT_VS_ACTUAL:
                            nbTool.action();
                            float accuracy = ((NBPredictVsActual) nbTool).getAccuracy();
                            accuracyLabel.setText("Accuracy :" + accuracy);
                            southPanel.add(accuracyLabel);
                            break;
                    }
            }
        });

        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

        southPanel.add(new Label ("Action Time"));
        southPanel.add(actionTimeField);
        southPanel.add(submitButton);

        pan.add(southPanel, BorderLayout.SOUTH);
    }
}
