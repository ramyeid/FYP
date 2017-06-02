package pages;

import modal.timeseriesanalysis.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ramyeid on 5/28/17.
 */
public class ContinuousForcastView extends JPanel implements ActionListener {


    String inputFile;
    String keyX;
    String keyY;
    String average;

    JFrame frame ;
    String dateFormat;

    int resetcsv = 1;

    private TextField actionTime;
    private Button forecastButton;
    private Button addValueButton;
    private TextField addValue;

    private JPanel east;
    private JPanel west;
    private JPanel plotPanel;

    public ContinuousForcastView(String inputFile,String keyX,String keyY,String average,String dateFormat){

        this.inputFile = inputFile;
        this.keyX = keyX;
        this.keyY = keyY;
        this.average = average;
        this.dateFormat = dateFormat;

        frame = new JFrame("Continunous Forecast");
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




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        plotPanel.removeAll();
        this.remove(plotPanel);

        if(e.getSource()==addValueButton){
            float value = Float.valueOf(addValue.getText());
            Action.addValue(inputFile,keyX,keyY,average,dateFormat,value);
            plotPanel = Action.plotcontinuousforecast(dateFormat);
            this.add(plotPanel,BorderLayout.SOUTH);
            frame.pack();
            this.revalidate();
            this.repaint();
        }
        else if (e.getSource()==forecastButton){

            addValue.setEnabled(true);
            addValueButton.setEnabled(true);

            int aTime = Integer.valueOf(actionTime.getText());

            Action.continuousforecast(inputFile,keyX,keyY,aTime,average,dateFormat,resetcsv);
            plotPanel = Action.plotcontinuousforecast(dateFormat);
            this.add(plotPanel,BorderLayout.SOUTH);
            frame.pack();
            this.revalidate();
            this.repaint();
            resetcsv = 0;


        }

    }
}
