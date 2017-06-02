package modal.timeseriesanalysis;


import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ramyeid on 5/28/17.
 */
public class Action {

    private static final String OUTPUT_FILE = System.getProperty("user.dir")+"/src/main/resources/output.csv";
    private static final Map<String,String> DATE_FORMAT_MAP = new HashMap<String,String>(){{
        put("%Y-%m","yyyy-MM");
    }};
    public static final String RESOURCES = System.getProperty("user.dir")+"/src/main/resources/ContinuousForecast/";



    public static void predict(String inputFile,String keyX,String keyY,int actionTime,String average,String dateFormat){
        new RunPythonFile(inputFile,keyX,keyY,1,actionTime,average,dateFormat,0,0).run();
    }

    public static JPanel plotpredict(String dateFormat){
        return  PlotPredictionForecastOnce.plotPrediction(OUTPUT_FILE,DATE_FORMAT_MAP.get(dateFormat));
    }


    public static void forecastvsactual(String inputFile,String keyX,String keyY,int actionTime,String average,String dateFormat){
        new RunPythonFile(inputFile,keyX,keyY,2,actionTime,average,dateFormat,0,0).run();

    }

    public static JPanel plotforecastvsactual(String dateFormat){
        return PlotForecastingVsActual.plotForecasting(OUTPUT_FILE,DATE_FORMAT_MAP.get(dateFormat));
    }

    public static void forecastonce(String inputFile, String keyX, String keyY, int actionTime, String average, String dateFormat) {
        new RunPythonFile(inputFile,keyX,keyY,3,actionTime,average,dateFormat,0,0).run();

    }

    public static JPanel plotforecastonce(String dateFormat,int actionTime) {
        return PlotPredictionForecastOnce.plotForecastOnce(OUTPUT_FILE,DATE_FORMAT_MAP.get(dateFormat),actionTime);
    }

    public static void continuousforecast(String inputFile,String keyX,String keyY,int actionTime,String average,String dateFormat,int resetcsv){
        new RunPythonFile(inputFile,keyX,keyY,4,actionTime,average,dateFormat,resetcsv,0).run();
    }

    public static void addValue(String inputFile,String keyX,String keyY,String average,String dateFormat,float value) {
        new RunPythonFile(inputFile,keyX,keyY,5,0,average,dateFormat,0,value).run();
    }

    public static JPanel plotcontinuousforecast(String dateFormat) {
        return PlotContinuousForecasting.plotContinuousForecasting(RESOURCES+"Forecasts.txt",RESOURCES+"Continuous_output.csv",DATE_FORMAT_MAP.get(dateFormat));
    }
}

