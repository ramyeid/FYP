package modal;


import modal.timeseriesanalysis.ContinuousForecast;
import modal.timeseriesanalysis.ForecastOnce;
import modal.timeseriesanalysis.ForecastVsActual;
import modal.timeseriesanalysis.Predict;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by ramyeid on 5/28/17.
 */
public class Resources {

    public static final String OUTPUT_FILE = System.getProperty("user.dir")+"/src/main/resources/output.csv";
    public static final Map<String,String> DATE_FORMAT_MAP = new HashMap<String,String>(){{
        put("%Y-%m","yyyy-MM");
    }};
    public static final String RESOURCES = System.getProperty("user.dir")+"/src/main/resources/ContinuousForecast/";


    public static final Vector<String> DATE_FORMAT_LIST = new Vector<>(Arrays.asList("%Y-%m"));
    public static final Vector<String> AVERAGE_LIST = new Vector<>(Arrays.asList("No Average"));
    public static final String PREDICT = "Predict";
    public static final String FORECAST_ONCE = "Forecast Once";
    public static final String FORECAST_VS_ACTUAL = "Forecast vs Actual";
    public static final String CONTINUOUS_FORECAST = "Continuous Forecast";


    public static final String Time_Series_Python_File = System.getProperty("user.dir")+"/src/main/java/modal/timeseriesanalysis/script.py";

    public static String  getTimeSeriesAnalysisActionName(int action){
        String result = null;
        switch(action) {
            case 1:
                result = PREDICT;
                break;
            case 2:
                result = FORECAST_VS_ACTUAL;
                break;
            case 3:
                result = FORECAST_ONCE;
                break;
            case 4:
                result = CONTINUOUS_FORECAST;
        }
        return result;
    }

    public static Tool getTimeSeriesAnalysisTool(int action) {
        String result = null;
        switch(action) {
            case 1:
                return new Predict();
            case 2:
                return new ForecastVsActual();
            case 3:
                return new ForecastOnce();
            case 4:
                return new ContinuousForecast();
        }
        return null;
    }
}

