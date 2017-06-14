package swissknife.panels.timeseriesanalysis;

import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.modal.Tool;
import swissknife.modal.timeseriesanalysis.TimeSeriesAnalysis;
import swissknife.modal.timeseriesanalysis.modal.TSAForecastOnce;
import swissknife.modal.timeseriesanalysis.modal.TSAForecastVsActual;
import swissknife.panels.showvalues.ShowValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//TODO ADD MORE AVERAGE
//TODO ADD MORE DATE FORMAT
public class TimeSeriesAnalysisPanel extends JPanel implements ActionListener {

    private String inputFile;
    JButton submitButton;

    ButtonGroup keysXButtonGroup;
    JPanel radioButtonsPanelX;
    List<JRadioButton> radioButtonListX;


    ButtonGroup keysYButtonGroup;
    JPanel radioButtonsPanelY;
    List<JRadioButton> radioButtonListY;

    JComboBox dateFormatComboBox;
    JComboBox averageComboBox;

    JPanel submitPanel;
    JPanel centerPanel;

    TextField actionTimeField;
    Label actionTimeLabel = new Label("Action Time");

//    JPanel plotPanel;

    String actionName;
    Tool timeSeriesTool;


    JInternalFrame masterFrame;
    JFrame mainFrame;
    JInternalFrame plotInternalFrame;

    public TimeSeriesAnalysisPanel(String fileName, int action, JInternalFrame masterFrame, JFrame mainFrame) {
        super(new BorderLayout());

        this.masterFrame = masterFrame;
        this.mainFrame = mainFrame;
        this.masterFrame.setTitle(actionName);
        this.inputFile = fileName;



        this.setLayout(null);
        this.setSize(600, 400);
        this.masterFrame.setSize(620,400);
        this.masterFrame.setMaximumSize(new Dimension(620, 400));
        this.masterFrame.setMinimumSize(new Dimension(620, 400));


        actionName = Resources.getTimeSeriesAnalysisActionName(action);
        timeSeriesTool = Resources.getTimeSeriesAnalysisTool(action);

        keysXButtonGroup = new ButtonGroup();
        keysYButtonGroup = new ButtonGroup();
        submitButton = new JButton(actionName);
        radioButtonsPanelX = new JPanel();
        radioButtonsPanelY = new JPanel();
        radioButtonListX = new ArrayList<>();
        radioButtonListY = new ArrayList<>();
        dateFormatComboBox = new JComboBox(Resources.DATE_FORMAT_LIST);
        averageComboBox = new JComboBox(Resources.AVERAGE_LIST);

        submitPanel = new JPanel();


        actionTimeField = new TextField(3);
//        plotPanel = new JPanel();
        plotInternalFrame = new JInternalFrame();

        centerPanel = new JPanel();


        String[] keysList = CSVReader.getColumnKeys(fileName);

        Resources.createRadioButtons(keysList, keysYButtonGroup, radioButtonsPanelY, radioButtonListY, "Y Axis", this);
        Resources.createRadioButtons(keysList, keysXButtonGroup, radioButtonsPanelX, radioButtonListX, "X Axis", this);


        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(new Label("Date Format"));
        centerPanel.add(dateFormatComboBox);
        centerPanel.add(new Label("Set Average"));
        centerPanel.add(averageComboBox);
        dateFormatComboBox.addActionListener(this);
        averageComboBox.addActionListener(this);

        submitButton.addActionListener(this);

        int numberOfKeys = keysList.length;

        JPanel radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(null);
        radioButtonsPanel.add(radioButtonsPanelX);
        radioButtonsPanelX.setBounds(5,5,150,40+(numberOfKeys+1)*20);
        radioButtonsPanel.add(radioButtonsPanelY);
        radioButtonsPanelY.setBounds(160,5,150,40+(numberOfKeys+1)*20);


        if (action != 4) {
            submitPanel.add(actionTimeField);
            submitPanel.add(actionTimeLabel);
        }

        centerPanel.add(submitPanel);
        submitPanel.add(submitButton);

        add(radioButtonsPanel);
        radioButtonsPanel.setBounds(20,15,350,10+radioButtonsPanelX.getHeight());

        add(centerPanel);
        centerPanel.setBounds(400,15,100,150);
        this.add(submitPanel);
        submitPanel.setBounds(350,200,200,200);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JRadioButton) {
            List<JRadioButton> tmp;
            tmp = radioButtonListY.stream().filter(s -> s.equals((JRadioButton) e.getSource())).collect(Collectors.toList());
            if (tmp.size() != 0) {
                boolean isSelected = radioButtonListX.stream().filter(s -> s.getText().equals(((JRadioButton) e.getSource()).getText())).findFirst().get().isSelected();
                if (isSelected) {
                    radioButtonListX.stream().filter(s -> !s.getText().equals(((JRadioButton) e.getSource()).getText())).findFirst().get().setSelected(true);
                }
            } else {
                boolean isSelected = radioButtonListY.stream().filter(s -> s.getText().equals(((JRadioButton) e.getSource()).getText())).findFirst().get().isSelected();
                if (isSelected) {
                    radioButtonListY.stream().filter(s -> !s.getText().equals(((JRadioButton) e.getSource()).getText())).findFirst().get().setSelected(true);
                }
            }
        }
        if (e.getSource() == submitButton) {
            String keyX = radioButtonListX.stream().filter(s -> s.isSelected()).findFirst().orElse(null).getText();
            String keyY = radioButtonListY.stream().filter(s -> s.isSelected()).findFirst().orElse(null).getText();
            String average = null;
            switch (averageComboBox.getSelectedItem().toString()) {
                case "No Average":
                    average = "-1";
                    break;
                default:
                    average = averageComboBox.getSelectedItem().toString();
                    break;
            }
            String dateFormat = dateFormatComboBox.getSelectedItem().toString();
            JMenu timeSeries = (JMenu) this.mainFrame.getJMenuBar().getMenu(2).getMenuComponent(3);
            if (!actionName.equals(Resources.TSA_CONTINUOUS_FORECAST)) {
                this.mainFrame.getJMenuBar().getMenu(2).setEnabled(true);
                timeSeries.setEnabled(true);
                if (timeSeries.getItem(1).getActionListeners().length != 0) {
                    timeSeries.getItem(1).removeActionListener(timeSeries.getItem(1).getActionListeners()[0]);
                }
                if (timeSeries.getItem(0).getActionListeners().length != 0) {
                    timeSeries.getItem(0).removeActionListener(timeSeries.getItem(0).getActionListeners()[0]);
                }


                if (actionName.equals(Resources.TSA_FORECAST_VS_ACTUAL)) {
                    timeSeries.getItem(1).setEnabled(true);
                    timeSeries.getItem(1).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<ArrayList<String>> result = ((TSAForecastVsActual) timeSeriesTool).getValues();
                            JInternalFrame tmp = new JInternalFrame();
                            tmp.add(new ShowValues(result, tmp, mainFrame));
                            mainFrame.add(tmp);
                            tmp.setTitle(Resources.TSA_FORECAST_ONCE + " " + inputFile);
                            tmp.setVisible(true);
                            tmp.setClosable(true);
                            tmp.pack();
                        }
                    });

                }

                if (actionName.equals(Resources.TSA_FORECAST_ONCE)) {
                    timeSeries.getItem(0).setEnabled(true);
                    timeSeries.getItem(0).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<ArrayList<String>> result = ((TSAForecastOnce) timeSeriesTool).getValues();
                            JInternalFrame tmp = new JInternalFrame();
                            tmp.add(new ShowValues(result, tmp, mainFrame));
                            mainFrame.add(tmp);
                            tmp.setTitle(Resources.TSA_FORECAST_ONCE + " " + inputFile);
                            tmp.setVisible(true);
                            tmp.setClosable(true);
                            tmp.pack();
                        }
                    });
                }

                timeSeriesTool.build(inputFile, keyX, keyY, actionTimeField.getText(), average, dateFormat);
                timeSeriesTool.action();

//                plotPanel.removeAll();
//                this.remove(plotPanel);

                mainFrame.remove(plotInternalFrame);
                plotInternalFrame = new JInternalFrame();
                plotInternalFrame.add(((TimeSeriesAnalysis) timeSeriesTool).plot());
                mainFrame.add(plotInternalFrame);
                plotInternalFrame.setTitle(actionName + " " + inputFile);
                plotInternalFrame.setVisible(true);
                plotInternalFrame.setClosable(true);
                plotInternalFrame.pack();


//                plotPanel = ((TimeSeriesAnalysis)timeSeriesTool).plot();
                if (actionName.equals(Resources.TSA_FORECAST_VS_ACTUAL)) {
                    centerPanel.add(new Label("MSE ERROR: " + ((TSAForecastVsActual) timeSeriesTool).getError()));
                }
//                this.add(plotPanel, BorderLayout.SOUTH);

                masterFrame.revalidate();
                masterFrame.repaint();
                masterFrame.pack();

            } else {

                timeSeriesTool.build(inputFile, keyX, keyY, "0", average, dateFormat);

                mainFrame.remove(masterFrame);

                JInternalFrame tmpFrame = new JInternalFrame();

                tmpFrame.add(new ContinuousForcastPanel(timeSeriesTool, tmpFrame, mainFrame));
                tmpFrame.setClosable(true);
                mainFrame.add(tmpFrame);
                tmpFrame.setVisible(true);
                tmpFrame.pack();

            }
        }

    }


}
