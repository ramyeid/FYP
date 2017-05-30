package pages;

import javafx.scene.layout.BorderRepeat;
import modal.CSVReader;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import modal.timeseriesanalysis.Action;

/**
 * Created by ramyeid on 4/27/17.
 */
/*

//        System.out.println("predict 2");
//        //ss1 - predict
//       Index -  String inputFile ="/Users/ramyeid/Desktop/Proj/FYP/src/main/resources/AirPassengers.csv";
//       TimeSeries -  String keyX = "Date";
//       TimeSeries - String keyY = "#Passengers";
//       TimeSeries - int action = 3;
//        int actionTime = 2;
//       TimeSeries - String average = "-1";
//       TimeSeries -  String dateFormat ="%Y-%m";
//        int resetCSV = 1;
//        float data = 0.0f;
//        new RunPythonFile(inputFile,keyX,keyY,action,actionTime,average,dateFormat,resetCSV,data).run();
//
 */
//TODO ADD SAVE GRAPH
//TODO ADD ERROR FOR  ACTION. FORECAST VS. ACTUAL, CONTINUOUS FORECAST
    //TODO FORECAST VS. ACTUAL GET THE ERROR FROM PYTHON SCRIPT.
    //TODO CONTINUOUS FORECAST IMPLEMENT FUNCTION PLOT CONTINUOUS FORECASTING.
//TODO maybe put output.txt files in a frame that can be accessed with file->output values.
//TODO Continuous Forecast on a different thread.
//TODO ADD AVERAGE
//TODO ADD ACTIONTIME
//TODO ADD TO SEE HOW ARE THE DATA IN THE FILE BY HOUR OR DAY (FOR AVERAGE).
public class  TimeSeriesAnalysisView  extends JPanel implements ActionListener {

    private String fileName;
    JButton submitButton;
    CSVReader csvReader;

    ButtonGroup keysXButtonGroup ;
    JPanel radioButtonsPanelX;
    List<JRadioButton> radioButtonListX;


    ButtonGroup keysYButtonGroup ;
    JPanel radioButtonsPanelY;
    List<JRadioButton> radioButtonListY;

    ButtonGroup actionButtonGroup;
    List<JRadioButton> actionRadioButtons;
    JPanel actionRadioPanel;


    JComboBox dateFormatComboBox;
    JComboBox averageComboBox;
    JFrame frame ;

    JPanel submitPanel;
    JPanel centerPanel;

    TextField actionTimeField;
    int action;

    JPanel plotPanel;

    private final Vector<String> DATE_FORMAT_LIST = new Vector<>(Arrays.asList("%Y-%m"));
    private final Vector<String> AVERAGE_LIST = new Vector<>(Arrays.asList("No Average"));
    private final String PREDICT = "Predict";
    private final String FORECAST_ONCE = "Forecast Once";
    private final String FORECAST_VS_ACTUAL= "Forecast vs Actual";
    private final String CONTINUOUS_FORECAST = "Continuous Forecast";
//    String dateFormat;
// Done    String inputFile ;
// Done    String keyX;
// Done    String keyY;
// Done    int action ;
// Done    int actionTime ;
// Done   String average;
// Done    final String pythonFile = "/Users/ramyeid/Desktop/Proj/FYP/src/main/java/modal/timeseriesanalysis/script.py";
//

    public void createRadioButtons(String [] keysList, ButtonGroup buttonGroup, JPanel radioButtons,List<JRadioButton> radioButtonList,String axis){
        radioButtons.setLayout(new BorderLayout());
        radioButtons.add(new Label(axis),BorderLayout.NORTH);
        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BoxLayout(tmpPanel,BoxLayout.Y_AXIS));

        for (int i=0;i<keysList.length;++i){
            JRadioButton tmp = new JRadioButton(keysList[i]);
            radioButtonList.add(tmp);
            tmp.addActionListener(this);
            buttonGroup.add(tmp);
            tmpPanel.add(tmp);
        }
        radioButtons.add(tmpPanel,BorderLayout.CENTER);
    }

    public void createSingleActionRadioButton(String name,JPanel panel,List<JRadioButton> actionRadioButtons,ButtonGroup buttonGroup){
        JRadioButton tmp = new JRadioButton(name);
        actionRadioButtons.add(tmp);
        tmp.addActionListener(this);
        buttonGroup.add(tmp);
        panel.add(tmp);
    }

    public void createActionRadioButtons(List<JRadioButton> actionRadioButtons,JPanel actionRadioPanel,ButtonGroup actionButtonGroup) {
        createSingleActionRadioButton(PREDICT, actionRadioPanel, actionRadioButtons,actionButtonGroup);
        createSingleActionRadioButton(FORECAST_ONCE, actionRadioPanel, actionRadioButtons,actionButtonGroup);
        createSingleActionRadioButton(FORECAST_VS_ACTUAL, actionRadioPanel, actionRadioButtons,actionButtonGroup);
        createSingleActionRadioButton(CONTINUOUS_FORECAST, actionRadioPanel, actionRadioButtons,actionButtonGroup);
    }

    public TimeSeriesAnalysisView(String fileName) {
        super(new BorderLayout());
        this.fileName = fileName;
        keysXButtonGroup = new ButtonGroup();
        keysYButtonGroup = new ButtonGroup();
        submitButton = new JButton();
        radioButtonsPanelX = new JPanel();
        radioButtonsPanelY = new JPanel();
        radioButtonListX = new ArrayList<>();
        radioButtonListY = new ArrayList<>();
        actionRadioButtons = new ArrayList<>();
        actionRadioPanel = new JPanel();
        actionButtonGroup = new ButtonGroup();
        dateFormatComboBox = new JComboBox(DATE_FORMAT_LIST);
        averageComboBox = new JComboBox(AVERAGE_LIST);
        frame = new JFrame("Time Series Analysis");
        submitPanel = new JPanel();
        csvReader = new CSVReader(fileName);
        actionTimeField = new TextField();
        plotPanel = new JPanel();
        centerPanel = new JPanel();


        actionTimeField.setColumns(3);


        String [] keysList = csvReader.getColumnKeys();

        createRadioButtons(keysList,keysYButtonGroup,radioButtonsPanelY,radioButtonListY,"Y Axis");
        createRadioButtons(keysList,keysXButtonGroup,radioButtonsPanelX,radioButtonListX,"X Axis");

        actionRadioPanel.setLayout(new BoxLayout(actionRadioPanel,BoxLayout.Y_AXIS));
        createActionRadioButtons(actionRadioButtons,actionRadioPanel,actionButtonGroup);


        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        centerPanel.add(new Label("Date Format"));
        centerPanel.add(dateFormatComboBox);
        centerPanel.add(new Label("Set Average"));
        centerPanel.add(averageComboBox);
        dateFormatComboBox.addActionListener(this);
        averageComboBox.addActionListener(this);

        submitButton.addActionListener(this);



        JPanel radioButtons = new JPanel(new BorderLayout());
        radioButtons.add(radioButtonsPanelX, BorderLayout.WEST);
        radioButtons.add(radioButtonsPanelY,BorderLayout.EAST);


        add(radioButtons,BorderLayout.WEST);
        add(actionRadioPanel,BorderLayout.EAST);
        add(centerPanel,BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);

    }





    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(submitButton)) {

            plotPanel.removeAll();
            this.remove(plotPanel);
            String keyX, keyY, average, dateFormat;
            int actionTime;
            keyX = radioButtonListX.stream().filter(s -> s.isSelected() == true).findFirst().get().getText();
            keyY = radioButtonListY.stream().filter(s -> s.isSelected() == true).findFirst().get().getText();
            actionTime = Integer.valueOf(actionTimeField.getText());
            dateFormat = dateFormatComboBox.getSelectedItem().toString();
            String selectedAverage = averageComboBox.getSelectedItem().toString();
            switch (selectedAverage) {
                case "No Average":
                    average = "-1";
                    break;
                default:
                    average = selectedAverage;
                    break;
            }
            switch (action) {
                case 1:
                    Action.predict(fileName,keyX,keyY,actionTime,average,dateFormat);
                    plotPanel = Action.plotpredict(dateFormat);
                    this.add(plotPanel,BorderLayout.SOUTH);
                    frame.pack();
                    this.revalidate();
                    this.repaint();

                    break;
                case 2:
                    Action.forecastvsactual(fileName,keyX,keyY,actionTime,average,dateFormat);
                    plotPanel = Action.plotforecastvsactual(dateFormat);
                    this.add(plotPanel,BorderLayout.SOUTH);
                    frame.pack();
                    this.revalidate();
                    this.repaint();
                    break;

                case 3:
                    Action.forecastonce(fileName,keyX,keyY,actionTime,average,dateFormat);
                    plotPanel = Action.plotforecastonce(dateFormat,actionTime);
                    this.add(plotPanel,BorderLayout.SOUTH);
                    frame.pack();
                    this.revalidate();
                    this.repaint();
                    break;
                case 4:
                    new ContinuousForcastView(fileName,keyX,keyY,average,dateFormat);
                    break;
            }
        }


        else if (e.getSource() instanceof JRadioButton) {

            boolean isAction = false;
            List<JRadioButton> tmp ;
            tmp = radioButtonListY.stream().filter(s->s.equals((JRadioButton)e.getSource())).collect(Collectors.toList());
            if (tmp.size()==0) {
                List<JRadioButton> tmp_2 = radioButtonListX.stream().filter(s->s.equals((JRadioButton)e.getSource())).collect(Collectors.toList());
                if (tmp_2.size() == 0) {
                    isAction = true;
                }
                else{
                    isAction = false;
                }
            }
            if(!isAction) {
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
            else{
                String text = ((JRadioButton) e.getSource()).getText();
                switch(text){
                    case PREDICT:
                        action = 1;
                        addSubmitPanel(PREDICT);

                        break;

                    case FORECAST_VS_ACTUAL:
                        action =2;
                        addSubmitPanel(FORECAST_VS_ACTUAL);

                        break;

                    case FORECAST_ONCE:
                        action =3;

                        addSubmitPanel(FORECAST_ONCE);

                        break;
                    case CONTINUOUS_FORECAST:
                        action=4;

                        this.remove(plotPanel);
                        centerPanel.remove(submitPanel);
                        break;
                }
                frame.pack();
                this.repaint();
                this.revalidate();
            }
        }
    }



    public void addSubmitPanel(String buttonText){
        centerPanel.remove(submitPanel);
        this.remove(plotPanel);
        submitPanel.removeAll();

        submitButton.setText(buttonText);
        submitPanel.add(new Label("Action Time"));
        submitPanel.add(actionTimeField);
        submitPanel.add(submitButton);

        centerPanel.add(submitPanel);

    }

}
