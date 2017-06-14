package swissknife.panels.comparetools;

import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.modal.classifier.Classifier;
import swissknife.panels.classifier.ClassifierPanel;
import swissknife.panels.showvalues.ShowValues;
import swissknife.views.MainWindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramyeid on 6/11/17.
 */
public class CompareToolsPanel extends JPanel implements ActionListener {


    String inputFile;
    JPanel comparisonAndSubmitSouth;

    JPanel chooseAlgorithmsActionPanelWEST;
    List<JCheckBox> chooseAlgorithmsList;

    ButtonGroup chooseActionButtonGroup;
    List<JRadioButton> chooseActionList;


    JPanel keysPanelCenter;
    List<JCheckBox> actionKeysCheckBoxList;
    ButtonGroup keysToPredictButtonGroup;
    List<JRadioButton> radioButtonListKeysToPredict;

    List<Classifier> classifiersChosen;
    JTextField actionTimeTextField;

    JButton submitButton;

    JInternalFrame masterFrame;
    MainWindowFrame mainFrame;

    String keyToPredict = "";

    JPanel actionPanel;
    JPanel actionTimePanel;
    JPanel keysCheckBoxPanel;

    public CompareToolsPanel(String inputFile, JInternalFrame masterFrame, MainWindowFrame mainFrame) {
        this.inputFile = inputFile;
        this.masterFrame = masterFrame;
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.setSize(600, 400);
        this.masterFrame.setSize(620,400);
        this.masterFrame.setMaximumSize(new Dimension(620, 400));
        this.masterFrame.setMinimumSize(new Dimension(620, 400));


        comparisonAndSubmitSouth = new JPanel();
        chooseAlgorithmsActionPanelWEST = new JPanel();
        submitButton = new JButton("Generate");
        chooseAlgorithmsList = new ArrayList<>();

        chooseActionButtonGroup = new ButtonGroup();
        chooseActionList = new ArrayList<>();

        keysPanelCenter = new JPanel();
        actionTimePanel = new JPanel();

        actionKeysCheckBoxList = new ArrayList<>();
        keysToPredictButtonGroup = new ButtonGroup();
        radioButtonListKeysToPredict = new ArrayList<>();

        classifiersChosen = new ArrayList<>();

        actionTimeTextField = new JTextField(4);

        addAlgorithmsCheckBox();
        addActionRadioButtonsAndActionTime();

        comparisonAndSubmitSouth.setLayout(new BoxLayout(comparisonAndSubmitSouth, BoxLayout.Y_AXIS));

        addKeyToPredict();
        addKeysForAction();

        JLabel actionTimeLabel = new JLabel("Action Time");

        actionTimePanel.add(actionTimeLabel);
        actionTimePanel.add(actionTimeTextField);

        int numberOfKeys = CSVReader.getColumnKeys(inputFile).length;

        this.masterFrame.setTitle("Comparing Algorithms");
        submitButton.addActionListener(this);
        comparisonAndSubmitSouth.add(submitButton);

        this.add(chooseAlgorithmsActionPanelWEST);
        chooseAlgorithmsActionPanelWEST.setBounds(15,20,250,350);
        chooseAlgorithmsActionPanelWEST.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(keysPanelCenter);
        keysPanelCenter.setBounds(300,20, 150, 40+(numberOfKeys+1)*20);
        keysPanelCenter.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(keysCheckBoxPanel);
        keysCheckBoxPanel.setBounds(500,20,200,40+(numberOfKeys+1)*20);
        keysCheckBoxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(actionPanel);
        actionPanel.setBounds(300,40+keysPanelCenter.getHeight(),200,60);
        this.add(actionTimePanel);
        actionTimePanel.setBounds(480,40+keysPanelCenter.getHeight(),200,30);
        this.add(comparisonAndSubmitSouth);
        comparisonAndSubmitSouth.setBounds(500,80+keysPanelCenter.getHeight(),100,100);


    }

    private void addKeyToPredict() {
        JPanel tmp = new JPanel();
        Resources.createRadioButtons(CSVReader.getColumnKeys(inputFile), keysToPredictButtonGroup, tmp, radioButtonListKeysToPredict, "Choose Key To Predict", this);
        keysPanelCenter.add(tmp);
    }


    private void addKeysForAction() {
        keysCheckBoxPanel = new JPanel();

        ClassifierPanel.createCheckBoxButtonsForActionKeys(CSVReader.getColumnKeys(inputFile), keysCheckBoxPanel, actionKeysCheckBoxList, this);
    }


    private void addActionRadioButtonsAndActionTime() {
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));

        JRadioButton tmp, tmp_2;
        tmp = new JRadioButton("Predict");
        tmp_2 = new JRadioButton("Predict vs Actual");

        tmp.addActionListener(this);
        tmp_2.addActionListener(this);
//
//        JPanel tmpPanel_2 = new JPanel();
//        tmpPanel_2.add(new JLabel("Action Time"));
//        tmpPanel_2.add(actionTimeTextField);
//

        chooseActionList.add(tmp);
        chooseActionList.add(tmp_2);

        chooseActionButtonGroup.add(tmp);
        chooseActionButtonGroup.add(tmp_2);


        actionPanel.add(tmp);
        actionPanel.add(tmp_2);
//        tmpPanel.add(tmpPanel_2);
//        chooseAlgorithmsActionPanelWEST.add(tmpPanel);

    }

    private void addAlgorithmsCheckBox() {
        chooseAlgorithmsActionPanelWEST = new JPanel();
        chooseAlgorithmsActionPanelWEST.setLayout(new BoxLayout(chooseAlgorithmsActionPanelWEST, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Choose algorithms to compare :");
        chooseAlgorithmsActionPanelWEST.add(title);
        chooseAlgorithmsActionPanelWEST.add(new JLabel(" "));

//        JPanel tmpPanel = new JPanel();
//        tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < Resources.classifierNames.size(); ++i) {
            JCheckBox tmp = new JCheckBox(Resources.classifierNames.get(i));
            tmp.addActionListener(this);
            chooseAlgorithmsList.add(tmp);
            chooseAlgorithmsActionPanelWEST.add(tmp);
        }
//        chooseAlgorithmsActionPanelWEST.add(tmpPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //2 checkbox chooseAlgorithmsList actionKeysCheckBoxList
        //2 radiobuttons chooseActionList radioButtonListKeysToPredict

        if (e.getSource() instanceof JRadioButton) {
            JRadioButton pressed = (JRadioButton) e.getSource();
            if (radioButtonListKeysToPredict.contains(pressed)) {
                for (JCheckBox tmp : actionKeysCheckBoxList) {
                    if (tmp.getText().equals(((JRadioButton) e.getSource()).getText())) {
                        tmp.setSelected(false);
                    }
                }
            }
        } else if (e.getSource().equals(submitButton)) {
            classifiersChosen = new ArrayList<>();

            //keyToPredict.
            keyToPredict = radioButtonListKeysToPredict.stream().filter(s -> s.isSelected()).findFirst().orElse(null).getText();


            //action Keys
            List<String> actionKeysList = new ArrayList<>();
            for (JCheckBox tmp : actionKeysCheckBoxList) {
                if (tmp.isSelected()) {
                    actionKeysList.add(tmp.getText());
                }
            }

            String actionKeys = "";
            for (int i = 0; i < actionKeysList.size(); ++i) {
                actionKeys += "/" + actionKeysList.get(i);
            }


            //actionTime
            String actionTime = actionTimeTextField.getText();


            //#action
            int action;
            String actionName = chooseActionList.stream().filter(s -> s.isSelected()).findFirst().orElse(null).getText();
            if (actionName.equals("Predict")) {
                action = 2;
            } else {
                action = 1;
            }

            //choose algorithms.
            List<String> algorithmNameChosenList = new ArrayList<>();
            for (JCheckBox tmp : chooseAlgorithmsList) {
                if (tmp.isSelected()) {
                    algorithmNameChosenList.add(tmp.getText());
                }
            }

            for (int i = 0; i < algorithmNameChosenList.size(); ++i) {
                Classifier classifier = Resources.getClassifierForNameAndAction(algorithmNameChosenList.get(i), action);
                classifier.build(inputFile, keyToPredict, actionTime, actionKeys);
                classifier.action();
                classifiersChosen.add(classifier);
            }

            comparisonAndSubmitSouth.removeAll();
            this.remove(comparisonAndSubmitSouth);

            switch (action) {
                case 1:
                    showAccuracies();
                    break;
                case 2:
                    showValues();
                    break;
            }

            comparisonAndSubmitSouth.add(submitButton);
            this.add(comparisonAndSubmitSouth, BorderLayout.SOUTH);
            masterFrame.pack();

        }
    }

    private void showValues() {

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        data.get(0).add("Algorithm Name");
        data.get(1).add("Values Predicted");

        for (int i = 0; i < classifiersChosen.size(); ++i) {
            ArrayList<String> result = classifiersChosen.get(i).getValuesOfPredictedForActionTime_Predict();
            String tmpResult = "";
            for (int j = 0; j < result.size(); ++j) {
                tmpResult += result.get(j);
                if (j != result.size() - 1) {
                    tmpResult += ", ";
                }
            }
            data.get(0).add(classifiersChosen.get(i).getAlgorithmName());
            data.get(1).add(tmpResult);
        }




        JInternalFrame iF = new JInternalFrame();
        iF.add(new ShowValues(data,masterFrame,mainFrame));

        iF.setTitle("Algorithms Predicted Values");
        iF.setClosable(true);
        iF.setVisible(true);
        iF.pack();

        mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane


    }


    public void showAccuracies() {

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        data.add(new ArrayList<String>());
        data.add(new ArrayList<String>());

        data.get(0).add("AlgorithmName");
        data.get(1).add("Accuracy");
        for (int i = 0; i < classifiersChosen.size(); ++i) {
            float accuracy = classifiersChosen.get(i).getAccuracy();
            String name = classifiersChosen.get(i).getAlgorithmName();
            data.get(0).add(name);
            data.get(1).add("" + accuracy);
        }







        JInternalFrame iF = new JInternalFrame();
        iF.add(new ShowValues(data, masterFrame, mainFrame));

        iF.setTitle("Algorithms Accuracies");
        iF.setClosable(true);
        iF.setVisible(true);
        iF.pack();

        mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane



    }


}

