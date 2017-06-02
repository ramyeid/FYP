package pages;

import javax.swing.*;

/**
 * Created by joeabdelnour on 6/2/17.
 */
public class TimeSeriesPredict extends JInternalFrame
{
    public TimeSeriesPredict(String pathCsv)
    {
        setBounds(100,100,450,300);
        this.add(new TimeSeriesAnalysisView(pathCsv,1));
    }

}
