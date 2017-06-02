import modal.CSVReader;
import modal.timeseriesanalysis.Plot.PlotPredictionForecastOnce;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.jfree.data.time.TimeSeries;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by ramyeid on 6/1/17.
 */
public class CorrelationTest {


    public double[]  toPrimitive(ArrayList<Double> tmp){
        double [] result = new double [tmp.size()];
        for(int i=0;i<tmp.size();++i){
            result[i]=tmp.get(i).doubleValue();
        }
        return result;
    }

    @Test
    public void AutoCorrelationTest()  {
        String input= System.getProperty("user.dir")+"/src/main/resources/AirPassengers.csv";
        TimeSeries tmp = PlotPredictionForecastOnce.addSingleValueFromcsv(input,"yyyy-MM","test");
        double[] t = new double [100];
        double[] t2 = new double [100];
        for (int i=0;i<100;++i){
            t[i] = (tmp.getDataItem(i).getValue().doubleValue());
        }
        for (int i=50;i< 100;++i){
            t2[i] = (t[i]);
        }
        double corr = new PearsonsCorrelation().correlation(t, t2);
        System.out.println(corr);
    }




    @Test
    public void CorrelationTestBayesData(){

        String input= System.getProperty("user.dir")+"/src/main/resources/BayesData_2.csv";
        ArrayList<ArrayList<Double>> tmp = CSVReader.readDataFromCSV(input,9);

        int size = tmp.get(0).size();
//
//        for(int i=0;i<size;++i){
//            for(int j=0;j<size;j++) {
//                if (i!=j) {
//                    tmp_1[i] = (tmp.get(6).get(i));
//                    tmp_2[i] = (tmp.get(7).get(i));
//                }
//            }
//        }


        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if(i!=j && j>i) {
                    double[] tmp_1 = new double[size];
                    double[] tmp_2 = new double[size];
                    tmp_1 = toPrimitive(tmp.get(i));
                    tmp_2 = toPrimitive(tmp.get(j));
                    double corr = new PearsonsCorrelation().correlation(tmp_1,tmp_2);
                    System.out.println(i + " " + j + "  :" + corr);

                }
            }
        }
    }
}
