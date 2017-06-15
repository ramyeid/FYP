package swissknife.panels.timeseriesanalysis;

import swissknife.Resources;
import swissknife.modal.Tool;
import swissknife.modal.timeseriesanalysis.TimeSeriesAnalysis;
import swissknife.modal.timeseriesanalysis.modal.TSAContinuousForecast;
import swissknife.panels.showvalues.ShowValues;
import swissknife.views.MainWindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by ramyeid on 5/28/17.
 */
public class ContinuousForcastPanel extends JPanel implements ActionListener {

    private TextField actionTime;
    private Button forecastButton;
    private Button addValueButton;
    private TextField addValue;

    TSAContinuousForecast timeSeriesTool;
    String actionName = Resources.TSA_CONTINUOUS_FORECAST;
    private JPanel east;
    private JPanel west;
    private JPanel center; // for errors.
//    private JPanel plotPanel;

    JInternalFrame masterFrame;
    MainWindowFrame mainFrame;

    JInternalFrame plotInternalFrame;


    public ContinuousForcastPanel(Tool timeSeriesTool, JInternalFrame masterFrame, MainWindowFrame mainFrame) {

        this.timeSeriesTool = (TSAContinuousForecast) timeSeriesTool;
        this.timeSeriesTool.setResetCsv(1);

        actionTime = new TextField(3);
        forecastButton = new Button("Forecast");
        addValueButton = new Button("Add Next Value");
        addValue = new TextField(3);
        east = new JPanel();
        west = new JPanel();
//        plotPanel = new JPanel();
        plotInternalFrame = new JInternalFrame();

        center = new JPanel();

        east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
        west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));

        east.add(new Label("Forecast"));
        east.add(actionTime);
        east.add(forecastButton);

        west.add(new Label("Next Value"));
        west.add(addValue);
        west.add(addValueButton);

        forecastButton.addActionListener(this);
        addValueButton.addActionListener(this);

        addValueButton.setEnabled(false);
        addValue.setEnabled(false);

        this.setLayout(null);
        this.add(east, BorderLayout.EAST);
        east.setBounds(15,5,120,80);
        this.add(west, BorderLayout.WEST);
        west.setBounds(160,5,120,80);

        this.setVisible(true);


        this.masterFrame = masterFrame;
        this.masterFrame.setTitle("Continuous Forecast");

        this.mainFrame = mainFrame;

        this.setSize(300,100);
        this.masterFrame.setSize(320,110);
        this.masterFrame.setMaximumSize(new Dimension(320, 110));
        this.masterFrame.setMinimumSize(new Dimension(320, 110));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
//        plotPanel.removeAll();
//        this.remove(plotPanel);
        JMenu timeSeries = (JMenu) this.mainFrame.getJMenuBar().getMenu(3).getMenuComponent(3);
        JMenu timeSeriesContinuousForecast = (JMenu) timeSeries.getMenuComponent(2);

        if (e.getSource() == addValueButton) {
            float value = Float.valueOf(addValue.getText());
            timeSeriesTool.addValue(value);
//            plotPanel = timeSeriesTool.plot();
//            this.add(plotPanel, BorderLayout.SOUTH);
            timeSeries.setEnabled(true);

            if (timeSeriesContinuousForecast.getItem(0).getActionListeners().length!=0) {
                timeSeriesContinuousForecast.getItem(0).removeActionListener(timeSeriesContinuousForecast.getItem(0).getActionListeners()[0]);
            }
            if (timeSeriesContinuousForecast.getItem(1).getActionListeners().length!=0) {
                timeSeriesContinuousForecast.getItem(1).removeActionListener(timeSeriesContinuousForecast.getItem(1).getActionListeners()[0]);
            }
        } else if (e.getSource() == forecastButton) {
            addValue.setEnabled(true);
            addValueButton.setEnabled(true);

            int aTime = Integer.valueOf(actionTime.getText());
            timeSeriesTool.setActionTime(aTime);
            timeSeriesTool.action();
            timeSeriesTool.setResetCsv(0);
//            plotPanel = timeSeriesTool.plot();
//            this.add(plotPanel, BorderLayout.SOUTH);
            timeSeries.setEnabled(true);

            if (timeSeriesContinuousForecast.getItem(0).getActionListeners().length!=0) {
                timeSeriesContinuousForecast.getItem(0).removeActionListener(timeSeriesContinuousForecast.getItem(0).getActionListeners()[0]);
            }
            if (timeSeriesContinuousForecast.getItem(1).getActionListeners().length!=0) {
                timeSeriesContinuousForecast.getItem(1).removeActionListener(timeSeriesContinuousForecast.getItem(1).getActionListeners()[0]);
            }
        }

        mainFrame.getDesktopPanel().remove(plotInternalFrame);
        mainFrame.repaint();
        mainFrame.revalidate();

        plotInternalFrame = new JInternalFrame();
        plotInternalFrame.add(((TimeSeriesAnalysis)timeSeriesTool).plot());
        plotInternalFrame.setTitle(Resources.TSA_CONTINUOUS_FORECAST+" "+timeSeriesTool.getInputFile());
        plotInternalFrame.setVisible(true);
        plotInternalFrame.setClosable(true);
        plotInternalFrame.pack();
        mainFrame.getDesktopPanel().add(plotInternalFrame);//add internal frame to the desktop pane




        //populateCenterPanel();

        timeSeriesContinuousForecast.setEnabled(true);
        timeSeriesContinuousForecast.getItem(0).setEnabled(true);
        timeSeriesContinuousForecast.getItem(1).setEnabled(true);

        timeSeriesContinuousForecast.getItem(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> result =((TSAContinuousForecast) timeSeriesTool).getValues();
                JInternalFrame iF = new JInternalFrame();
                iF.add(new ShowValues(result, iF, mainFrame));

                iF.setTitle(Resources.TSA_CONTINUOUS_FORECAST + " - all values - " + timeSeriesTool.getInputFile());
                iF.setClosable(true);
                iF.setVisible(true);
                iF.pack();

                mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane




            }
        });

        timeSeriesContinuousForecast.getItem(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Float> values = Resources.calculateAbsoluteError(Resources.actualTimeSeries, Resources.predictedTimeSeriesCollection);
                ArrayList<String> forecastNumber = new ArrayList<String>();
                ArrayList<String> valuesFinal = new ArrayList<String>();
                for(int i=0;i<values.size();++i){
                    forecastNumber.add("forecast #"+i);
                    valuesFinal.add(""+values.get(i));
                }
                ArrayList<ArrayList<String>> result = new ArrayList<>();
                forecastNumber.add(0,"Forecasts numbers");
                valuesFinal.add(0,"Absolute error");
                result.add(forecastNumber);
                result.add(valuesFinal);




                JInternalFrame iF = new JInternalFrame();
                iF.add(new ShowValues(result, iF, mainFrame));

                iF.setTitle(Resources.TSA_CONTINUOUS_FORECAST + "- errors - " + timeSeriesTool.getInputFile());
                iF.setClosable(true);
                iF.setVisible(true);
                iF.pack();

                mainFrame.getDesktopPanel().add(iF);//add internal frame to the desktop pane

            }
        });



    }










    private void populateCenterPanel() {
        center.removeAll();
        JPanel panelText = new JPanel();
        JPanel panelErrorValue = new JPanel();
        panelText.setLayout(new BoxLayout(panelText, BoxLayout.Y_AXIS));
        panelErrorValue.setLayout(new BoxLayout(panelErrorValue, BoxLayout.Y_AXIS));
        ArrayList<Float> values = Resources.calculateAbsoluteError(Resources.actualTimeSeries, Resources.predictedTimeSeriesCollection);
        for (int i = 0; i < values.size(); ++i) {
            if (i == 0) {
                JLabel mainText = new JLabel("Forecast #");
                panelText.add(mainText);
                JLabel mainError = new JLabel("Absolute Error");
                panelErrorValue.add(mainError);
            }
            JLabel error = new JLabel("" + values.get(i));
            JLabel forecastNumber = new JLabel("" + i);
            panelErrorValue.add(error);
            panelText.add(forecastNumber);
        }
        center.add(panelText);
        center.add(panelErrorValue);
        this.add(center, BorderLayout.CENTER);
    }

}
