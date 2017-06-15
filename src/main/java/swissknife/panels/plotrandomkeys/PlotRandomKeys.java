package swissknife.panels.plotrandomkeys;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.views.MainWindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ramyeid on 6/15/17.
 */
public class PlotRandomKeys extends JPanel implements ActionListener {

    ButtonGroup keysYButtonGroup;
    JPanel radioButtonsPanelY;
    List<JRadioButton> radioButtonListY;

    ButtonGroup keysXButtonGroup;
    JPanel radioButtonsPanelX;
    List<JRadioButton> radioButtonListX;


    JButton plotButton;

    JInternalFrame masterFrame;
    MainWindowFrame mainFrame;

    String inputFile;
    String keyX, keyY;

    JPanel radioButtonsPanel;
    JInternalFrame plotPanel;

    public PlotRandomKeys(String fileName, JInternalFrame masterFrame, MainWindowFrame mainFrame) {

        inputFile = fileName;
        this.masterFrame = masterFrame;
        this.mainFrame = mainFrame;

        keysYButtonGroup = new ButtonGroup();
        radioButtonsPanelY = new JPanel();
        radioButtonListY = new ArrayList<>();

        keysXButtonGroup = new ButtonGroup();
        radioButtonsPanelX = new JPanel();
        radioButtonListX = new ArrayList<>();

        plotButton = new JButton("Plot");

        radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(null);

        plotPanel = new JInternalFrame();

        plotButton.addActionListener(this);
        String[] keysList = CSVReader.getColumnKeys(fileName);

        int numberOfKeys = keysList.length;

        Resources.createRadioButtons(keysList, keysYButtonGroup, radioButtonsPanelY, radioButtonListY, "Y Axis", this);
        Resources.createRadioButtons(keysList, keysXButtonGroup, radioButtonsPanelX, radioButtonListX, "X Axis", this);

        this.setLayout(null);
        radioButtonsPanel.add(radioButtonsPanelX);
        radioButtonsPanelX.setBounds(5,5,150,40+(numberOfKeys+1)*20);
        radioButtonsPanelX.setBorder(BorderFactory.createLineBorder(Color.black));
        radioButtonsPanel.add(radioButtonsPanelY);
        radioButtonsPanelY.setBounds(160,5,150,40+(numberOfKeys+1)*20);
        radioButtonsPanelY.setBorder(BorderFactory.createLineBorder(Color.black));

        add(radioButtonsPanel);
        radioButtonsPanel.setBounds(20,15,320,10+radioButtonsPanelX.getHeight());

        add(plotButton);
        plotButton.setBounds(130, 30+radioButtonsPanel.getHeight(),100,20);


        this.setSize(360, 60+radioButtonsPanel.getHeight());
        this.masterFrame.setSize(370,60+radioButtonsPanel.getHeight());
        this.masterFrame.setMaximumSize(new Dimension(370, 60+radioButtonsPanel.getHeight()));
        this.masterFrame.setMinimumSize(new Dimension(370, 60+radioButtonsPanel.getHeight()));

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
        if (e.getSource().equals(plotButton)) {
            keyX = radioButtonListX.stream().filter(s -> s.isSelected()).findFirst().orElse(null).getText();
            keyY = radioButtonListY.stream().filter(s -> s.isSelected()).findFirst().orElse(null).getText();


            ArrayList<ArrayList<String>> result = CSVReader.getDataCSVForKeys(inputFile, keyX, keyY);

            result.get(0).remove(0);
            result.get(1).remove(0);
            XYSeries timeSeries = new XYSeries(keyX + " vs " + keyY);
            for (int i = 0; i < result.get(0).size(); ++i) {
                timeSeries.add(Double.valueOf(result.get(0).get(i)), Double.valueOf(result.get(1).get(i)));
            }

            mainFrame.getDesktopPanel().remove(plotPanel);
            mainFrame.repaint();
            mainFrame.revalidate();
            plotPanel = new JInternalFrame();

            plotPanel.add(new ChartPanel(createChart(timeSeries,keyX,keyY)));


            plotPanel.setTitle(keyX + " vs " + keyY);
            plotPanel.setClosable(true);
            plotPanel.setVisible(true);
            plotPanel.pack();
            plotPanel.setClosable(true);
            mainFrame.getDesktopPanel().add(plotPanel);//add internal frame to the desktop pane


        }
    }

    public static JFreeChart createChart(XYSeries xySeries,String keyX, String keyY) {


        JFreeChart chart = ChartFactory.createXYLineChart(keyX + " vs " + keyY, keyX, keyY, new XYSeriesCollection(xySeries), PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();


        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);

        plot.setRenderer(renderer);

        return chart;
    }
}


