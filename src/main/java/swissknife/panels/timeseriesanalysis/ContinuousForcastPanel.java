package swissknife.panels.timeseriesanalysis;

import swissknife.Resources;
import swissknife.modal.Tool;
import swissknife.modal.timeseriesanalysis.modal.TSAContinuousForecast;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel plotPanel;
//    JFrame frame;

    public ContinuousForcastPanel(Tool timeSeriesTool){

        this.timeSeriesTool = (TSAContinuousForecast)timeSeriesTool;
        this.timeSeriesTool.setResetCsv(1);

//        frame = new JFrame(actionName);
        actionTime = new TextField(3);
        forecastButton = new Button("Forecast");
        addValueButton = new Button("Add Next Value");
        addValue = new TextField(3);
        east = new JPanel();
        west = new JPanel();
        plotPanel = new JPanel();

        east.setLayout(new BoxLayout(east,BoxLayout.Y_AXIS));
        west.setLayout(new BoxLayout(west,BoxLayout.Y_AXIS));

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

        this.setLayout(new BorderLayout());
        this.add(east,BorderLayout.EAST);
        this.add(west, BorderLayout.WEST);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        plotPanel.removeAll();
        this.remove(plotPanel);

        if(e.getSource()==addValueButton){
            float value = Float.valueOf(addValue.getText());
            timeSeriesTool.addValue(value);
            plotPanel = timeSeriesTool.plot();
            this.add(plotPanel,BorderLayout.SOUTH);
//            frame.pack();
            this.revalidate();
            this.repaint();
        }

        else if (e.getSource()==forecastButton){

            addValue.setEnabled(true);
            addValueButton.setEnabled(true);

            int aTime = Integer.valueOf(actionTime.getText());
            timeSeriesTool.setActionTime(aTime);
            timeSeriesTool.action();
            timeSeriesTool.setResetCsv(0);
            plotPanel = timeSeriesTool.plot();
            this.add(plotPanel,BorderLayout.SOUTH);
//            frame.pack();
            this.revalidate();
            this.repaint();


        }

    }
}
