package swissknife.panels.classifier;

import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.modal.Tool;
import swissknife.modal.classifier.Classifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramyeid on 6/10/17.
 */
public class ClassifierPanel extends JPanel implements ActionListener {
    //action = 1 - Predict And
    //action = 2 - Predict Only

    String inputFile;
    String actionName;
    String classifierName;

    ButtonGroup keysToPredictButtonGroup;
    JPanel radioButtonsPanelKeysToPredict;
    List<JRadioButton> radioButtonListKeysToPredict;

    TextField actionTimeField;
    JButton submitButton;
    JPanel southPanel;


    List<JCheckBox> keysToChooseCheckBoxes;
    JPanel checkBoxesPanel;

    Classifier classifierTool;
    Label accuracyLabel;
    JInternalFrame masterFrame;
    String keyToPredict = null;


    public ClassifierPanel(Tool tool, String inputFile, JInternalFrame masterFrame){
        this.setLayout(new BorderLayout());
        classifierTool = (Classifier) tool;
        classifierName = classifierTool.getAlgorithmName();
        actionName = Resources.getClassifierActionName(classifierTool.getAction());
        this.inputFile = inputFile;

        this.masterFrame = masterFrame;
        this.masterFrame.setTitle(classifierName);


        submitButton = new JButton(actionName);
        keysToPredictButtonGroup = new ButtonGroup();
        radioButtonsPanelKeysToPredict = new JPanel();
        radioButtonListKeysToPredict = new ArrayList<>();
        actionTimeField = new TextField(3);
        southPanel = new JPanel();
        accuracyLabel = new Label();

        keysToChooseCheckBoxes = new ArrayList<>();
        checkBoxesPanel = new JPanel();

        String[] keysList = CSVReader.getColumnKeys(inputFile);
        Resources.createRadioButtons(keysList, keysToPredictButtonGroup, radioButtonsPanelKeysToPredict, radioButtonListKeysToPredict, "Choose Key To Predict",this);

        createCheckBoxButtons(keysList);
        this.add(checkBoxesPanel,BorderLayout.EAST);

        this.add(radioButtonsPanelKeysToPredict,BorderLayout.WEST);

        submitButton.addActionListener(this);


        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

        southPanel.add(new Label ("Action Time"));
        southPanel.add(actionTimeField);
        southPanel.add(submitButton);

        this.add(southPanel, BorderLayout.SOUTH);



    }

    private void createCheckBoxButtons(String[] keysList) {
        checkBoxesPanel.setLayout(new BorderLayout());
        checkBoxesPanel.add(new Label("Choose keys to do action with"), BorderLayout.NORTH);
        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < keysList.length; ++i) {
            JCheckBox tmp = new JCheckBox(keysList[i]);
            tmp.setSelected(true);
            tmp.addActionListener(this);
            tmpPanel.add(tmp);
            keysToChooseCheckBoxes.add(tmp);

        }
        checkBoxesPanel.add(tmpPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (JRadioButton radioTmp:radioButtonListKeysToPredict){
            if (radioTmp.isSelected()){
                keyToPredict=radioTmp.getText();
            }
        }
        if (e.getSource() instanceof JCheckBox || e.getSource() instanceof JRadioButton){
            if (keyToPredict!=null) {
                for (JCheckBox tmp:keysToChooseCheckBoxes){
                    if (tmp.getText().equals(keyToPredict)){
                        tmp.setSelected(false);
                    }
                }
            }
        }
        else if (e.getSource().equals(submitButton)) {


            List<String> keysNotToDrop = new ArrayList<>();
            for(JCheckBox tmp:keysToChooseCheckBoxes){
                if (tmp.isSelected()){
                    keysNotToDrop.add(tmp.getText());
                }
            }

            String actionKeys ="";
            for (int i=0;i<keysNotToDrop.size();++i){
                actionKeys += "/"+keysNotToDrop.get(i);
            }
            System.out.println(actionKeys);

            String actionTime = actionTimeField.getText();

            classifierTool.build(inputFile, keyToPredict, actionTime,actionKeys);
            switch (actionName) {
                case Resources.CLASSIFIER_PREDICT:
                    classifierTool.action();
                    break;
                case Resources.CLASSIFIER_PREDICT_VS_ACTUAL:
                    classifierTool.action();
                    float accuracy = classifierTool.getAccuracy();
                    accuracyLabel.setText("Accuracy :"+accuracy);
                    southPanel.add(accuracyLabel);
                    masterFrame.revalidate();
                    masterFrame.repaint();
                    masterFrame.pack();
                    break;
            }

        }
    }
}
