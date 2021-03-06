package swissknife.panels.classifier;

import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.modal.Tool;
import swissknife.modal.classifier.Classifier;
import swissknife.modal.classifier.neuralnetwork.NeuralNetwork;
import swissknife.panels.showvalues.ShowValues;
import swissknife.views.MainWindowFrame;

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

    JLabel actionTimeLabel;
    JTextField actionTimeField;
    JButton submitButton;
    JPanel southPanel;


    List<JCheckBox> keysToChooseCheckBoxes;
    JPanel checkBoxesPanel;

    Classifier classifierTool;
    Label accuracyLabel;
    JInternalFrame masterFrame;
    MainWindowFrame mainFrame;
    String keyToPredict = null;

    JLabel hiddenLayerLabel;
    JTextField hiddenLayerTextField;
    private volatile boolean running = true;


    public ClassifierPanel(Tool tool, String inputFile, JInternalFrame masterFrame, MainWindowFrame mainFrame) {
        this.setLayout(null);
        this.setSize(450, 300);

        classifierTool = (Classifier) tool;
        classifierName = classifierTool.getAlgorithmName();
        actionName = Resources.getClassifierActionName(classifierTool.getAction());
        this.inputFile = inputFile;


        submitButton = new JButton(actionName);
        keysToPredictButtonGroup = new ButtonGroup();
        radioButtonsPanelKeysToPredict = new JPanel();
        radioButtonListKeysToPredict = new ArrayList<>();
        actionTimeField = new JTextField(3);
        actionTimeLabel = new JLabel("Action Time");
        southPanel = new JPanel();
        accuracyLabel = new Label();
        keysToChooseCheckBoxes = new ArrayList<>();
        checkBoxesPanel = new JPanel();

        String[] keysList = CSVReader.getColumnKeys(inputFile);
        Resources.createRadioButtons(keysList, keysToPredictButtonGroup, radioButtonsPanelKeysToPredict, radioButtonListKeysToPredict, "Choose Key To Predict", this);

        createCheckBoxButtonsForActionKeys(keysList, checkBoxesPanel, keysToChooseCheckBoxes, this);

        radioButtonsPanelKeysToPredict.setBounds(30, 20, 154, (keysList.length + 1) * 21 + 30);
        radioButtonsPanelKeysToPredict.setBorder(BorderFactory.createLineBorder(Color.black));
        checkBoxesPanel.setBounds(230, 20, 196, (keysList.length + 1) * 21 + 30);
        checkBoxesPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        southPanel.setBounds(15, 30 + checkBoxesPanel.getHeight(), 420, 120);

        this.add(checkBoxesPanel);
        this.add(radioButtonsPanelKeysToPredict);


        submitButton.addActionListener(this);


        southPanel.setLayout(null);

        southPanel.add(actionTimeLabel);
        southPanel.add(actionTimeField);
        southPanel.add(submitButton);

        actionTimeLabel.setBounds(17, 5, 150, 20);
        actionTimeField.setBounds(10, 27, 150, 30);
        submitButton.setBounds(10, 62, 150, 30);

        this.add(southPanel);


        this.masterFrame = masterFrame;
        this.masterFrame.setTitle(classifierName);
        this.mainFrame = mainFrame;
        this.masterFrame.setMaximumSize(new Dimension(470, 55 + checkBoxesPanel.getHeight() + southPanel.getHeight()));
        this.masterFrame.setMinimumSize(new Dimension(470, 55 + checkBoxesPanel.getHeight() + southPanel.getHeight()));

        addPredictMenuActionListener();
        addPredictVsActualMenuActionListener();

        if (classifierName.equals(Resources.NEURAL_NETWORK)) {
            hiddenLayerLabel = new JLabel("Depth of hidden layer n,n,...");

            hiddenLayerTextField = new JTextField(10);
            southPanel.add(hiddenLayerLabel);
            hiddenLayerLabel.setBounds(225, 15, 180, 20);
            southPanel.add(hiddenLayerTextField);
            hiddenLayerTextField.setBounds(225, 37, 180, 20);
        }


    }

    public static void createCheckBoxButtonsForActionKeys(String[] keysList, JPanel checkBoxesPanel, List<JCheckBox> keysToChooseCheckBoxes, JPanel mainPanel) {
        checkBoxesPanel.setLayout(new BorderLayout());
        checkBoxesPanel.add(new Label("Choose keys to do action with"), BorderLayout.NORTH);
        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < keysList.length; ++i) {
            JCheckBox tmp = new JCheckBox(keysList[i]);
            tmp.setSelected(true);
            tmp.addActionListener((ActionListener) mainPanel);
            tmpPanel.add(tmp);
            keysToChooseCheckBoxes.add(tmp);

        }
        checkBoxesPanel.add(tmpPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (JRadioButton radioTmp : radioButtonListKeysToPredict) {
            if (radioTmp.isSelected()) {
                keyToPredict = radioTmp.getText();
            }
        }
        if (e.getSource() instanceof JCheckBox || e.getSource() instanceof JRadioButton) {
            if (keyToPredict != null) {
                for (JCheckBox tmp : keysToChooseCheckBoxes) {
                    if (tmp.getText().equals(keyToPredict)) {
                        tmp.setSelected(false);
                    }
                }
            }
        } else if (e.getSource().equals(submitButton)) {


            List<String> keysNotToDrop = new ArrayList<>();
            for (JCheckBox tmp : keysToChooseCheckBoxes) {
                if (tmp.isSelected()) {
                    keysNotToDrop.add(tmp.getText());
                }
            }

            String actionKeys = "";
            for (int i = 0; i < keysNotToDrop.size(); ++i) {
                actionKeys += "/" + keysNotToDrop.get(i);
            }

            String actionTime = actionTimeField.getText();

            classifierTool.build(inputFile, keyToPredict, actionTime, actionKeys);


            if (classifierName.equals(Resources.NEURAL_NETWORK)) {
                String hiddenDepth = hiddenLayerTextField.getText();
                System.out.println("ASDASDASDAS" + hiddenDepth);
                ((NeuralNetwork) classifierTool).setHiddenDepth(hiddenDepth);
            }

            switch (actionName) {
                case Resources.CLASSIFIER_PREDICT:
                    this.mainFrame.getJMenuBar().getMenu(3).getMenuComponent(1).setEnabled(true);

                    southPanel.remove(submitButton);
                    masterFrame.repaint();
                    masterFrame.revalidate();
                    final Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            // Your code
                            classifierTool.action();
                            southPanel.add(submitButton);


                            masterFrame.repaint();
                            masterFrame.revalidate();

                            ArrayList<String> data = classifierTool.getValuesOfPredictedForActionTime_Predict();
                            ArrayList<ArrayList<String>> result = new ArrayList<>();
                            result.add(data);


                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));

                            iF.setTitle(classifierTool.getAlgorithmName() + " - Predict - key to predict values for action time");
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();

                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane

                        }
                    });
                    thread.start();

                    break;
                case Resources.CLASSIFIER_PREDICT_VS_ACTUAL:
                    this.mainFrame.getJMenuBar().getMenu(3).getMenuComponent(2).setEnabled(true);

                    southPanel.remove(submitButton);

                    masterFrame.repaint();
                    masterFrame.revalidate();
                    final Thread thread_2 = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            // Your code
                            classifierTool.action();
                            southPanel.add(submitButton);

                            masterFrame.repaint();
                            masterFrame.revalidate();

                            float accuracy = classifierTool.getAccuracy();
                            accuracy = accuracy * 100f;

                            String text = String.format("Accuracy: %.2f", accuracy);
                            accuracyLabel.setText(text + " %");
                            southPanel.add(accuracyLabel);
                            accuracyLabel.setBounds(225, 60, 150, 20);
                            masterFrame.pack();

                        }
                    });
                    thread_2.start();
                    break;
            }

        }
    }


    private void addPredictVsActualMenuActionListener() {
//        mnSVPredictVsActual.add(new JMenuItem("all values"));
//        mnSVPredictVsActual.add(new JMenuItem("all values for action time"));
//        mnSVPredictVsActual.add(new JMenuItem("Actual and Predicted values for action time"));
//        mnSVPredictVsActual.add(new JMenuItem("action keys columns for action time"));

        /*
    //all columns, all values -- For Predict and Predict Vs Actual
    public ArrayList<ArrayList<String>> getAllValues(String file)
    //all columns, actionTime values -- For Predict and Predict Vs Actual
    public ArrayList<ArrayList<String>> getAllValuesForActionTime(String file)

    //Columns Actual And Predicted, actiontime Values
    public ArrayList<ArrayList<String>> getValuesOfActualAndPredictedForActionTime()

    //actionKeys Columns, actionTime Values
    public ArrayList<ArrayList<String>> getValuesOfActionKeysForActionTime_PredictVsActual()*/
        JMenu predictVsActualMenu = (JMenu) this.mainFrame.getJMenuBar().getMenu(3).getMenuComponent(2);
        for (int i = 0; i < predictVsActualMenu.getItemCount(); ++i) {
            JMenuItem tmp = predictVsActualMenu.getItem(i);
            switch (tmp.getText()) {
                case "All values":
                    tmp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            ArrayList<ArrayList<String>> result = classifierTool.getAllValues(classifierTool.getFileAllResultsPvsA());


                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));
                            iF.setTitle(classifierTool.getAlgorithmName() + " - P vs A - all values");
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane

                        }
                    });
                    break;
                case "All values for action time":
                    tmp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<ArrayList<String>> result = classifierTool.getAllValuesForActionTime(classifierTool.getFileAllResultsPvsA());


                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));
                            iF.setTitle(classifierTool.getAlgorithmName() + " - P vs A - all values for action time");
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane

                        }
                    });
                    break;
                case "Actual and Predicted values for action time":
                    tmp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<ArrayList<String>> result = classifierTool.getValuesOfActualAndPredictedForActionTime();


                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));
                            iF.setTitle(classifierTool.getAlgorithmName() + " - P vs A - values of actual and predicted for action time");
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane

                        }
                    });
                    break;
                case "Action keys columns for action time":
                    tmp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<ArrayList<String>> result = classifierTool.getValuesOfActionKeysForActionTime_PredictVsActual();

                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));
                            iF.setTitle(classifierTool.getAlgorithmName() + " - P vs A - action keys columns for action time");
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane

                        }
                    });
                    break;

            }
        }
    }


    private void addPredictMenuActionListener() {
//        mnSVPredict.add(new JMenuItem("all values"));
//        mnSVPredict.add(new JMenuItem("all values for action time"));
//        mnSVPredict.add(new JMenuItem("action keys values"));
//        mnSVPredict.add(new JMenuItem("action keys values for action time"));
//        mnSVPredict.add(new JMenuItem("key to predict values"));
//        mnSVPredict.add(new JMenuItem("key to predict values for action time"));


//        //actionKeys columns, all values
//        //actionKeys columns, ActionTime values
//        public ArrayList<ArrayList<String>> getValuesOfActionKeysForActiontime_Predict(){
//        //keyToPredict column, all values
//        public ArrayList<String> getValuesOfPredicted_Predict() {
//        //keyToPredict column, actionTime values
//        public ArrayList<String> getValuesOfPredictedForActionTime_Predict() {

//
        System.out.println(mainFrame.getJMenuBar().getMenuCount());
        System.out.println(mainFrame.getJMenuBar().getMenu(3).getItemCount());
        JMenu predictMenu = (JMenu) this.mainFrame.getJMenuBar().getMenu(3).getMenuComponent(1);
        for (int i = 0; i < predictMenu.getItemCount(); ++i) {
            JMenuItem tmp = predictMenu.getItem(i);
            switch (tmp.getText()) {
                case "All values":
                    tmp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<ArrayList<String>> result = classifierTool.getAllValues(classifierTool.getFileToReadPredicted());


                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));
                            iF.setTitle(classifierTool.getAlgorithmName() + " - Predict - all values");
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane


                        }
                    });
                    break;
                case "All values for action time":
                    tmp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<ArrayList<String>> result = classifierTool.getAllValuesForActionTime(classifierTool.getFileToReadPredicted());


                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));
                            iF.setTitle(classifierTool.getAlgorithmName() + " - Predict - all values for action time");
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane


                        }
                    });
                    break;
                case "Action keys values":
                    tmp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<ArrayList<String>> result = classifierTool.getValuesOfActionKeys_Predict();


                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));
                            iF.setTitle(classifierTool.getAlgorithmName() + " - Predict - action keys values");
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane


                        }
                    });
                    break;
                case "Action keys values for action time":
                    tmp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<ArrayList<String>> result = classifierTool.getValuesOfActionKeysForActiontime_Predict();


                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));
                            iF.setTitle(classifierTool.getAlgorithmName() + " - Predict - action keys values for action time");
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane


                        }
                    });
                    break;
                case "Key to predict values":
                    tmp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<String> data = classifierTool.getValuesOfPredicted_Predict();
                            ArrayList<ArrayList<String>> result = new ArrayList<>();
                            result.add(data);


                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));
                            iF.setTitle(classifierTool.getAlgorithmName() + " - Predict - key to predict values");
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane


                        }
                    });
                    break;
                case "Key to predict values for action time":
                    tmp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<String> data = classifierTool.getValuesOfPredictedForActionTime_Predict();
                            ArrayList<ArrayList<String>> result = new ArrayList<>();
                            result.add(data);


                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));
                            iF.setTitle(classifierTool.getAlgorithmName() + " - Predict - key to predict values for action time");
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane

                        }
                    });
                    break;

            }
        }

    }


}
