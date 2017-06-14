package swissknife.panels.timeseriesanalysis;

import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.modal.Tool;
import swissknife.modal.timeseriesanalysis.TimeSeriesAnalysis;
import swissknife.modal.timeseriesanalysis.modal.TSAForecastOnce;
import swissknife.modal.timeseriesanalysis.modal.TSAForecastVsActual;
import swissknife.panels.showvalues.ShowValues;
import swissknife.views.MainWindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//TODO ADD MORE AVERAGE
//TODO ADD MORE DATE FORMAT
//TODO all actions on different threads.
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
    MainWindowFrame mainFrame;
    JInternalFrame plotInternalFrame;

    public TimeSeriesAnalysisPanel(String fileName, int action, JInternalFrame masterFrame, MainWindowFrame mainFrame) {
//        super(new BorderLayout());

        this.masterFrame = masterFrame;
        this.mainFrame = mainFrame;
        this.inputFile = fileName;

        this.setLayout(null);
//        this.setSize(600, 400);
//        this.masterFrame.setSize(620,400);
//        this.masterFrame.setMaximumSize(new Dimension(620, 400));
//        this.masterFrame.setMinimumSize(new Dimension(620, 400));


        actionName = Resources.getTimeSeriesAnalysisActionName(action);
        timeSeriesTool = Resources.getTimeSeriesAnalysisTool(action);
        this.masterFrame.setTitle(actionName);

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
        submitPanel.setLayout(null);


        actionTimeField = new TextField(4);
//        plotPanel = new JPanel();
        plotInternalFrame = new JInternalFrame();

        centerPanel = new JPanel();


        String[] keysList = CSVReader.getColumnKeys(fileName);

        Resources.createRadioButtons(keysList, keysYButtonGroup, radioButtonsPanelY, radioButtonListY, "Y Axis", this);
        Resources.createRadioButtons(keysList, keysXButtonGroup, radioButtonsPanelX, radioButtonListX, "X Axis", this);



        JLabel dateFormatLabel = new JLabel("Date Format");
        JLabel averageLabel = new JLabel("Set Average");


//        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(null);

        centerPanel.setLayout(null);
        centerPanel.add(dateFormatLabel);
        dateFormatLabel.setBounds(5,15,80,20);
        centerPanel.add(dateFormatComboBox);
        dateFormatComboBox.setBounds(100,15,130,20);
        centerPanel.add(averageLabel);
        averageLabel.setBounds(5,40,80,20);
        centerPanel.add(averageComboBox);
        averageComboBox.setBounds(100,40,130,20);



        dateFormatComboBox.addActionListener(this);
        averageComboBox.addActionListener(this);

        submitButton.addActionListener(this);


        if (action != 4) {
            submitPanel.add(actionTimeLabel);
            actionTimeLabel.setBounds(5,5,100,20);

            submitPanel.add(actionTimeField);
            actionTimeField.setBounds(105,5,80,20);
        }

//        centerPanel.add(submitPanel);
        submitPanel.add(submitButton);
        submitButton.setBounds(5,35,180,20);

        int numberOfKeys = keysList.length;



        JPanel radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(null);
        radioButtonsPanel.add(radioButtonsPanelX);
        radioButtonsPanelX.setBounds(5,5,150,40+(numberOfKeys+1)*20);
        radioButtonsPanelX.setBorder(BorderFactory.createLineBorder(Color.black));
        radioButtonsPanel.add(radioButtonsPanelY);
        radioButtonsPanelY.setBounds(160,5,150,40+(numberOfKeys+1)*20);
        radioButtonsPanelY.setBorder(BorderFactory.createLineBorder(Color.black));




        add(radioButtonsPanel);
        radioButtonsPanel.setBounds(20,15,320,10+radioButtonsPanelX.getHeight());

        add(centerPanel);
        centerPanel.setBounds(350,15,300,100);
        this.add(submitPanel);
        submitPanel.setBounds(370,110,200,130);


        if(radioButtonsPanel.getHeight()>230)
        {
            this.setSize(600, 20+radioButtonsPanel.getHeight());
            this.masterFrame.setSize(620,20+radioButtonsPanel.getHeight());
            this.masterFrame.setMaximumSize(new Dimension(620, 20+radioButtonsPanel.getHeight()));
            this.masterFrame.setMinimumSize(new Dimension(620, 20+radioButtonsPanel.getHeight()));
        }

        else
        {
            this.setSize(600,200);
            this.masterFrame.setSize(620,210);
            this.masterFrame.setMaximumSize(new Dimension(620, 210));
            this.masterFrame.setMinimumSize(new Dimension(620, 210));
        }

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

                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));

                            iF.setTitle(Resources.TSA_FORECAST_ONCE + " " + inputFile);
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane

                        }
                    });

                }

                if (actionName.equals(Resources.TSA_FORECAST_ONCE)) {
                    timeSeries.getItem(0).setEnabled(true);
                    timeSeries.getItem(0).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {


                            ArrayList<ArrayList<String>> result = ((TSAForecastOnce) timeSeriesTool).getValues();

                            JInternalFrame iF = new JInternalFrame();
                            iF.add(new ShowValues(result, iF, mainFrame));

                            iF.setTitle(Resources.TSA_FORECAST_ONCE + " " + inputFile);
                            iF.setClosable(true);
                            iF.setVisible(true);
                            iF.pack();
                            iF.setClosable(true);
                            mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane



                        }
                    });
                }

                timeSeriesTool.build(inputFile, keyX, keyY, actionTimeField.getText(), average, dateFormat);
                timeSeriesTool.action();

//                plotPanel.removeAll();
//                this.remove(plotPanel);

                mainFrame.getDesktopPanel().remove(plotInternalFrame);
                mainFrame.repaint();
                mainFrame.revalidate();

                plotInternalFrame = new JInternalFrame();
                plotInternalFrame.add(((TimeSeriesAnalysis) timeSeriesTool).plot());
                plotInternalFrame.setTitle(actionName + " " + inputFile);
                plotInternalFrame.setVisible(true);
                plotInternalFrame.setClosable(true);
                plotInternalFrame.pack();


                mainFrame.getDesktopPanel().add(plotInternalFrame);//add internal frame to the desktop pane



                masterFrame.repaint();
                masterFrame.revalidate();


//                plotPanel = ((TimeSeriesAnalysis)timeSeriesTool).plot();
                if (actionName.equals(Resources.TSA_FORECAST_VS_ACTUAL)) {
                    JLabel mse = new JLabel("MSE ERROR: " + ((TSAForecastVsActual) timeSeriesTool).getError());
                    submitPanel.add(mse);
                    mse.setBounds(5,60,180,20);
                }
//                this.add(plotPanel, BorderLayout.SOUTH);


            } else {

                timeSeriesTool.build(inputFile, keyX, keyY, "0", average, dateFormat);

                mainFrame.getDesktopPanel().remove(masterFrame);
                mainFrame.repaint();
                mainFrame.revalidate();

                JInternalFrame iF = new JInternalFrame();
                iF.add(new ContinuousForcastPanel(timeSeriesTool, iF, (MainWindowFrame) mainFrame));
                iF.setClosable(true);
                iF.setVisible(true);
                iF.pack();
                iF.setClosable(true);
                mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane

            }
        }

    }


}
