package swissknife;


import swissknife.modal.Tool;
import swissknife.modal.timeseriesanalysis.modal.TSAContinuousForecast;
import swissknife.modal.timeseriesanalysis.modal.TSAForecastOnce;
import swissknife.modal.timeseriesanalysis.modal.TSAForecastVsActual;
import swissknife.modal.timeseriesanalysis.modal.TSAPredict;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by ramyeid on 5/28/17.
 */
public class Resources {

    public static final String OUTPUT_FILE = System.getProperty("user.dir")+"/src/main/resources/TimeSeriesAnalysis/output.csv";
    public static final Map<String,String> DATE_FORMAT_MAP = new HashMap<String,String>(){{
        put("%Y-%m","yyyy-MM");
    }};
    public static final String RESOURCES = System.getProperty("user.dir")+"/src/main/resources/TimeSeriesAnalysis/ContinuousForecast/";


    public static final Vector<String> DATE_FORMAT_LIST = new Vector<>(Arrays.asList("%Y-%m"));
    public static final Vector<String> AVERAGE_LIST = new Vector<>(Arrays.asList("No Average"));
    public static final String TSA_PREDICT = "Predict";
    public static final String TSA_FORECAST_ONCE = "Forecast Once";
    public static final String TSA_FORECAST_VS_ACTUAL = "Forecast vs Actual";
    public static final String TSA_CONTINUOUS_FORECAST = "Continuous Forecast";


    public static final String NB_PREDICT = "Predict";
    public static final String NB_PREDICT_VS_ACTUAL = "Predict Vs. Actual";

    public static final String TIME_SERIES_PYTHON_FILE = System.getProperty("user.dir")+"/src/main/java/swissknife/modal/timeseriesanalysis/TimeSeriesAnalysis.py";
    public static final String NAIVE_BAYES_PYTHON_FILE = System.getProperty("user.dir")+"/src/main/java/swissknife/modal/naivebayes/NaiveBayes.py";
    public static String  getTimeSeriesAnalysisActionName(int action){
        switch(action) {
            case 1:
                return TSA_PREDICT;
            case 2:
                return TSA_FORECAST_VS_ACTUAL;
            case 3:
                return TSA_FORECAST_ONCE;
            case 4:
                return TSA_CONTINUOUS_FORECAST;
        }
        return null;
    }

    public static String getNaiveBayesActionName(int action){
        String result =null;
        switch(action) {
            case 1:
                return NB_PREDICT_VS_ACTUAL;
            case 2:
                return NB_PREDICT;
        }
        return null;
    }


    public static Tool getTimeSeriesAnalysisTool(int action) {
        String result = null;
        switch(action) {
            case 1:
                return new TSAPredict();
            case 2:
                return new TSAForecastVsActual();
            case 3:
                return new TSAForecastOnce();
            case 4:
                return new TSAContinuousForecast();
        }
        return null;
    }
}

