package pages; /**
 * Created by ramyeid on 4/26/17.
 */

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//TODO for not continuous show the value immediatly and draw the plot with a point for the forecasted
//TODO for the continuous draw plot and point of each one and put a text "forecast 1" when they hover over it
//TODO show the value of forecasted.

public class Index extends JPanel implements ActionListener {

    private JButton openButton;
    private JFileChooser fc;
    private JButton submitButton;

    private String fileName;

    private JRadioButton optionNeural;
    private JRadioButton optionTimeS;


    public Index() {
        super(new BorderLayout());
        fc = new JFileChooser();
        fc.setFileFilter(new FileFilter(){
            @Override
            public boolean accept(File f){
                if (f.isDirectory()){
                    return true;
                }
                return f.getName().endsWith(".csv");
            }
            @Override
            public String getDescription(){
                return "*.csv";
            }
        });
        fc.setAcceptAllFileFilterUsed(false);


        openButton = new JButton("Open a file");
        optionNeural = new JRadioButton("Neural Network");
        optionTimeS = new JRadioButton("Time Series Analysis");
        submitButton = new JButton("Submit");
        ButtonGroup radioGroup = new ButtonGroup();

        optionNeural.setSelected(true);
        openButton.addActionListener(this);
        submitButton.addActionListener(this);


        JPanel radioPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel submitPanel = new JPanel();


        radioGroup.add(optionNeural);
        radioGroup.add(optionTimeS);

        radioPanel.add(optionNeural);
        radioPanel.add(optionTimeS);

        buttonPanel.add(openButton);

        submitPanel.add(submitButton);

        add(buttonPanel, BorderLayout.PAGE_START);
        add(radioPanel,BorderLayout.CENTER);
        add(submitPanel,BorderLayout.PAGE_END);

        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton){
            int returnVal = fc.showOpenDialog(Index.this);

            if (returnVal == JFileChooser.APPROVE_OPTION){
                String fileName = fc.getSelectedFile().getAbsolutePath();
                this.fileName = fileName;

            }
        }
        if(e.getSource() == submitButton){
            if (fileName != null){
                if (optionTimeS.isSelected()){
                    System.out.println("Time Series Analysis\n"+fileName);
                    this.setVisible(false);
                    new TimeSeriesAnalysisView(fileName);

                }
                else{
                    System.out.println("Neural Network\n"+fileName);
                    this.setVisible(false);
                    new NeuralNetworkView(fileName);
                }
            }
            else{
                System.out.println("PLEASE SELECT A FILE");
            }
        }

    }




}