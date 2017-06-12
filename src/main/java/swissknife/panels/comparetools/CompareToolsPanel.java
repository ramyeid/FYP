package swissknife.panels.comparetools;

import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.modal.classifier.Classifier;
import swissknife.panels.classifier.ClassifierPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramyeid on 6/11/17.
 */
//TODO if predict vs actual - just a table comparing the accuracy
//TODO if predict open a new Panel with excel like layout showing values of prediction.
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
    JFrame mainFrame;

    String keyToPredict = "";

    public CompareToolsPanel(String inputFile, JInternalFrame masterFrame,JFrame mainFrame) {
        this.inputFile = inputFile;
        this.masterFrame = masterFrame;
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        comparisonAndSubmitSouth = new JPanel();
        chooseAlgorithmsActionPanelWEST = new JPanel();
        submitButton = new JButton("Generate");
        chooseAlgorithmsList = new ArrayList<>();

        chooseActionButtonGroup = new ButtonGroup();
        chooseActionList = new ArrayList<>();

        keysPanelCenter = new JPanel();
        actionKeysCheckBoxList = new ArrayList<>();
        keysToPredictButtonGroup = new ButtonGroup();
        radioButtonListKeysToPredict = new ArrayList<>();

        classifiersChosen = new ArrayList<>();

        actionTimeTextField = new JTextField(3);
        addAlgorithmsCheckBox();
        addActionRadioButtonsAndActionTime();

        comparisonAndSubmitSouth.setLayout(new BoxLayout(comparisonAndSubmitSouth, BoxLayout.Y_AXIS));

        addKeyToPredict();
        addKeysForAction();

        this.masterFrame.setTitle("Comparing Algorithms");
        submitButton.addActionListener(this);
        comparisonAndSubmitSouth.add(submitButton);

        this.add(chooseAlgorithmsActionPanelWEST, BorderLayout.WEST);
        this.add(comparisonAndSubmitSouth, BorderLayout.SOUTH);
        this.add(keysPanelCenter, BorderLayout.CENTER);


    }

    private void addKeyToPredict() {
        JPanel tmp = new JPanel();
        Resources.createRadioButtons(CSVReader.getColumnKeys(inputFile), keysToPredictButtonGroup, tmp, radioButtonListKeysToPredict, "Choose Key To Predict", this);
        keysPanelCenter.add(tmp);
    }


    private void addKeysForAction() {
        JPanel tmp = new JPanel();

        ClassifierPanel.createCheckBoxButtonsForActionKeys(CSVReader.getColumnKeys(inputFile), tmp, actionKeysCheckBoxList, this);
        keysPanelCenter.add(tmp);
    }


    private void addActionRadioButtonsAndActionTime() {
        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));

        JRadioButton tmp, tmp_2;
        tmp = new JRadioButton("Predict");
        tmp_2 = new JRadioButton("Predict vs Actual");

        tmp.addActionListener(this);
        tmp_2.addActionListener(this);

        JPanel tmpPanel_2 = new JPanel();
        tmpPanel_2.add(new JLabel("Action Time"));
        tmpPanel_2.add(actionTimeTextField);


        chooseActionList.add(tmp);
        chooseActionList.add(tmp_2);

        chooseActionButtonGroup.add(tmp);
        chooseActionButtonGroup.add(tmp_2);


        tmpPanel.add(tmp);
        tmpPanel.add(tmp_2);
        tmpPanel.add(tmpPanel_2);
        chooseAlgorithmsActionPanelWEST.add(tmpPanel);

    }

    private void addAlgorithmsCheckBox() {
        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < Resources.classifierNames.size(); ++i) {
            JCheckBox tmp = new JCheckBox(Resources.classifierNames.get(i));
            tmp.addActionListener(this);
            chooseAlgorithmsList.add(tmp);
            tmpPanel.add(tmp);
        }
        chooseAlgorithmsActionPanelWEST.add(tmpPanel);
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

            for(int i=0;i<algorithmNameChosenList.size();++i){
                Classifier classifier = Resources.getClassifierForNameAndAction(algorithmNameChosenList.get(i),action);
                classifier.build(inputFile,keyToPredict,actionTime,actionKeys);
                classifier.action();
                classifiersChosen.add(classifier);
            }

            comparisonAndSubmitSouth.removeAll();
            this.remove(comparisonAndSubmitSouth);
            switch(action){
                case 1:
                    addAccuracy();
                    comparisonAndSubmitSouth.add(submitButton);
                    this.add(comparisonAndSubmitSouth,BorderLayout.SOUTH);
                    this.revalidate();
                    this.repaint();
                    break;
                case 2:
                    //TODO FILL HERE.
                    break;
            }
            masterFrame.revalidate();
            masterFrame.repaint();
            masterFrame.pack();

        }
    }



    public void addAccuracy() {
//        String col[] = {"Pos","Team","P", "W", "L", "D", "MP", "GF", "GA", "GD"};

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Algorithm Name","Accuracy"}, 0);
        // The 0 argument is number rows.
        JTable table = new JTable(tableModel);
;
        for (int i=0;i<classifiersChosen.size();++i){
            float accuracy = classifiersChosen.get(i).getAccuracy();
            String name = classifiersChosen.get(i).getAlgorithmName();
            Object []data ={name,accuracy};
            tableModel.addRow(data);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(table.getSize());

//        mainFrame.remove(masterFrame);
        JInternalFrame tmpFrame = new JInternalFrame();

        tmpFrame.add(scrollPane);
        tmpFrame.setClosable(true);
        mainFrame.add(tmpFrame);
        tmpFrame.setVisible(true);
        tmpFrame.setSize(table.getSize());
        tmpFrame.pack();

//        comparisonAndSubmitSouth.add(scrollPane);

//        JPanel mainTmp = new JPanel();
//        JPanel tmpAlgorithmNames = new JPanel();
//        JPanel tmpAccuracy = new JPanel();
//        tmpAlgorithmNames.setLayout(new BoxLayout(tmpAlgorithmNames, BoxLayout.Y_AXIS));
//        tmpAccuracy.setLayout(new BoxLayout(tmpAccuracy, BoxLayout.Y_AXIS));
//        tmpAccuracy.add(new JLabel("Accuracy"));
//        tmpAlgorithmNames.add(new JLabel("Algorithms"));
//
//        for(int i=0;i<classifiersChosen.size();++i){
//            tmpAccuracy.add(new JLabel(""+classifiersChosen.get(i).getAccuracy()));
//            tmpAlgorithmNames.add(new JLabel(classifiersChosen.get(i).getAlgorithmName()));
//        }
//        mainTmp.add(tmpAlgorithmNames);
//        mainTmp.add(tmpAccuracy);
//        comparisonAndSubmitSouth.add(mainTmp);
    }


}

