package swissknife.panels.timeseriesanalysis;

import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.modal.Tool;
import swissknife.modal.timeseriesanalysis.TimeSeriesAnalysis;
import swissknife.modal.timeseriesanalysis.modal.TSAForecastVsActual;

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

    JPanel plotPanel;

    String actionName;
    Tool timeSeriesTool;


    JInternalFrame masterFrame;
    JFrame mainFrame;

    public TimeSeriesAnalysisPanel(String fileName, int action,JInternalFrame masterFrame,JFrame mainFrame) {
        super(new BorderLayout());

        this.inputFile = fileName;
        actionName = Resources.getTimeSeriesAnalysisActionName(action);
        timeSeriesTool = Resources.getTimeSeriesAnalysisTool(action);

        keysXButtonGroup = new ButtonGroup();
        keysYButtonGroup = new ButtonGroup();
        submitButton = new JButton();
        radioButtonsPanelX = new JPanel();
        radioButtonsPanelY = new JPanel();
        radioButtonListX = new ArrayList<>();
        radioButtonListY = new ArrayList<>();
        dateFormatComboBox = new JComboBox(Resources.DATE_FORMAT_LIST);
        averageComboBox = new JComboBox(Resources.AVERAGE_LIST);

        submitPanel = new JPanel();


        actionTimeField = new TextField(3);
        plotPanel = new JPanel();
        centerPanel = new JPanel();




        String[] keysList = CSVReader.getColumnKeys(fileName);

        Resources.createRadioButtons(keysList, keysYButtonGroup, radioButtonsPanelY, radioButtonListY, "Y Axis",this);
        Resources.createRadioButtons(keysList, keysXButtonGroup, radioButtonsPanelX, radioButtonListX, "X Axis",this);


        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(new Label("Date Format"));
        centerPanel.add(dateFormatComboBox);
        centerPanel.add(new Label("Set Average"));
        centerPanel.add(averageComboBox);
        dateFormatComboBox.addActionListener(this);
        averageComboBox.addActionListener(this);

        submitButton.addActionListener(this);


        JPanel radioButtons = new JPanel(new BorderLayout());
        radioButtons.add(radioButtonsPanelX, BorderLayout.WEST);
        radioButtons.add(radioButtonsPanelY, BorderLayout.EAST);


        add(radioButtons, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        submitButton.setText(actionName);
        if (action != 4) {
            submitPanel.add(actionTimeField);
            submitPanel.add(actionTimeLabel);

        }
        submitPanel.add(submitButton);

        centerPanel.add(submitPanel);


        this.masterFrame = masterFrame;
        this.mainFrame = mainFrame;
        this.masterFrame.setTitle(actionName);


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
            if (!actionName.equals(Resources.TSA_CONTINUOUS_FORECAST)) {

                timeSeriesTool.build(inputFile, keyX, keyY, actionTimeField.getText(), average, dateFormat);
                timeSeriesTool.action();

                plotPanel.removeAll();
                this.remove(plotPanel);


                plotPanel = ((TimeSeriesAnalysis)timeSeriesTool).plot();
                if (actionName.equals(Resources.TSA_FORECAST_VS_ACTUAL)){
                    centerPanel.add(new Label("MSE ERROR: "+((TSAForecastVsActual)timeSeriesTool).getError()));
                }
                this.add(plotPanel, BorderLayout.SOUTH);

                masterFrame.revalidate();
                masterFrame.repaint();
                masterFrame.pack();

            }
            else {
                timeSeriesTool.build(inputFile, keyX, keyY, "0", average, dateFormat);

                mainFrame.remove(masterFrame);

                JInternalFrame tmpFrame = new JInternalFrame();

                tmpFrame.add(new ContinuousForcastPanel(timeSeriesTool,tmpFrame));
                tmpFrame.setClosable(true);
                mainFrame.add(tmpFrame);
                tmpFrame.setVisible(true);
                tmpFrame.pack();

            }
        }

    }


}
