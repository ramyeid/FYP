package swissknife.panels.naivebayes;

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
public class NaiveBayesPanel extends JPanel implements ActionListener  {

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


    public NaiveBayesPanel(String inputFile,int action){
        super(new BorderLayout());
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




//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(this);
//        frame.pack();
//        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(submitButton)) {
            String keyToPredict = radioButtonListKeysToPredict.stream().filter(s -> s.isSelected()).findFirst().orElse(null).getText();
            String actionTime = actionTimeField.getText();
            nbTool.build(inputFile, keyToPredict, actionTime);
            switch (actionName) {
                case Resources.NB_PREDICT:
                    nbTool.action();
                    break;
                case Resources.NB_PREDICT_VS_ACTUAL:
                    nbTool.action();
                    float accuracy = ((NBPredictVsActual) nbTool).getAccuracy();
                    accuracyLabel.setText("Accuracy :"+accuracy);
                    southPanel.add(accuracyLabel);
//                    frame.pack();
                    break;
            }

        }
    }
}
