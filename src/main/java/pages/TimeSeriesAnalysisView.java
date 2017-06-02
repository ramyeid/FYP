package pages;

import modal.CSVReader;
import modal.Tool;
import modal.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TODO ADD SAVE GRAPH
//TODO ADD ERROR FOR  ACTION. FORECAST VS. ACTUAL, CONTINUOUS FORECAST
//TODO FORECAST VS. ACTUAL GET THE ERROR FROM PYTHON SCRIPT.
//TODO CONTINUOUS FORECAST IMPLEMENT FUNCTION PLOT CONTINUOUS FORECASTING.
//TODO maybe put output.txt files in a frame that can be accessed with file->output values.
//TODO Continuous Forecast on a different thread.
//TODO ADD AVERAGE
//TODO ADD ACTIONTIME
//TODO ADD TO SEE HOW ARE THE DATA IN THE FILE BY HOUR OR DAY (FOR AVERAGE).
public class TimeSeriesAnalysisView extends JPanel implements ActionListener {

    private String inputFile;
    JButton submitButton;
    CSVReader csvReader;

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

    JPanel plotPanel;

    String actionName;
    Tool timeSeriesTool;

    public void createRadioButtons(String[] keysList, ButtonGroup buttonGroup, JPanel radioButtons, List<JRadioButton> radioButtonList, String axis) {
        radioButtons.setLayout(new BorderLayout());
        radioButtons.add(new Label(axis), BorderLayout.NORTH);
        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < keysList.length; ++i) {
            JRadioButton tmp = new JRadioButton(keysList[i]);
            radioButtonList.add(tmp);
            tmp.addActionListener(this);
            buttonGroup.add(tmp);
            tmpPanel.add(tmp);
        }
        radioButtons.add(tmpPanel, BorderLayout.CENTER);
    }


    public TimeSeriesAnalysisView(String fileName, int action) {
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


        actionTimeField = new TextField();
        plotPanel = new JPanel();
        centerPanel = new JPanel();


        actionTimeField.setColumns(3);


        String[] keysList = csvReader.getColumnKeys(fileName);

        createRadioButtons(keysList, keysYButtonGroup, radioButtonsPanelY, radioButtonListY, "Y Axis");
        createRadioButtons(keysList, keysXButtonGroup, radioButtonsPanelX, radioButtonListX, "X Axis");


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
        submitPanel.add(new Label("Action Time"));
        if (action != 4) {
            submitPanel.add(actionTimeField);
        }
        submitPanel.add(submitButton);

        centerPanel.add(submitPanel);


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
            if (!actionName.equals(Resources.CONTINUOUS_FORECAST)) {

                timeSeriesTool.build(inputFile, keyX, keyY, actionTimeField.getText(), average, dateFormat);
                timeSeriesTool.action();

                plotPanel.removeAll();
                this.remove(plotPanel);

                plotPanel = timeSeriesTool.plot();
                this.add(plotPanel, BorderLayout.SOUTH);
                this.revalidate();
                this.repaint();
            }
            else {
                timeSeriesTool.build(inputFile, keyX, keyY, "0", average, dateFormat);

                new ContinuousForcastView(timeSeriesTool);

            }
        }

    }


}
