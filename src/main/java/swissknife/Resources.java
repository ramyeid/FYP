package swissknife;


import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import swissknife.modal.Tool;
import swissknife.modal.classifier.Classifier;
import swissknife.modal.classifier.decisiontree.DTPredict;
import swissknife.modal.classifier.decisiontree.DTPredictVsActual;
import swissknife.modal.classifier.knearestneighbors.KNNPredict;
import swissknife.modal.classifier.knearestneighbors.KNNPredictVsActual;
import swissknife.modal.classifier.lineardiscriminant.LDPredict;
import swissknife.modal.classifier.lineardiscriminant.LDPredictVsActual;
import swissknife.modal.classifier.logisticregression.LogRPredict;
import swissknife.modal.classifier.logisticregression.LogRPredictVsActual;
import swissknife.modal.classifier.naivebayes.NBPredict;
import swissknife.modal.classifier.naivebayes.NBPredictVsActual;
import swissknife.modal.classifier.svm.SVMPredict;
import swissknife.modal.classifier.svm.SVMPredictVsActual;
import swissknife.modal.linearregression.modal.LRPredict;
import swissknife.modal.linearregression.modal.LRPredictVsActual;
import swissknife.modal.timeseriesanalysis.modal.TSAContinuousForecast;
import swissknife.modal.timeseriesanalysis.modal.TSAForecastOnce;
import swissknife.modal.timeseriesanalysis.modal.TSAForecastVsActual;
import swissknife.modal.timeseriesanalysis.modal.TSAPredict;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by ramyeid on 5/28/17.
 */
public class Resources {

    public static final Map<String, String> DATE_FORMAT_MAP = new HashMap<String, String>() {{
        put("%Y-%m", "yyyy-MM");
    }};


    private static final String BASE_FILE_TSA = System.getProperty("user.dir") + "/src/main/resources/TimeSeriesAnalysis/";
    private static final String BASE_FILE_NB = System.getProperty("user.dir") + "/src/main/resources/NaiveBayes/";
    private static final String BASE_FILE_LR = System.getProperty("user.dir") + "/src/main/resources/LinearRegression/";
    private static final String BASE_FILE_KNN = System.getProperty("user.dir") + "/src/main/resources/KNearestNeighbors/";
    private static final String BASE_FILE_SVM = System.getProperty("user.dir") + "/src/main/resources/SVM/";
    private static final String BASE_FILE_DT = System.getProperty("user.dir") + "/src/main/resources/DecisionTree/";
    private static final String BASE_FILE_LOGR = System.getProperty("user.dir") + "/src/main/resources/LogisticRegression/";
    private static final String BASE_FILE_LD = System.getProperty("user.dir") + "/src/main/resources/LinearDiscriminant/";

    public static final String TSA_FORECAST_ONCE_OUTPUT_FILE = BASE_FILE_TSA + "/Forecast_Once_Output.csv";

    public static final String TSA_PREDICT_OUTPUT_FILE = BASE_FILE_TSA + "Predict_Output.csv";
    public static final String TSA_FORECAST_VS_ACTUAL_OUTPUT_FILE = BASE_FILE_TSA + "Forecast_vs_Actual_Output.csv";
    public static final String CONTINUOS_FORECAST_OUTPUT_FILE = BASE_FILE_TSA + "ContinuousForecast/";


    public static final String KNN_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_KNN + "Predicted_Actual_Only.csv";
    public static final String NB_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_NB + "Predicted_Actual_Only.csv";
    public static final String LR_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_LR + "Predicted_Actual_Only.csv";
    public static final String SVM_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_SVM + "Predicted_Actual_Only.csv";
    public static final String DT_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_DT + "Predicted_Actual_Only.csv";
    public static final String LOGR_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_LOGR + "Predicted_Actual_Only.csv";
    public static final String LD_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_LD + "Predicted_Actual_Only.csv";


    public static final Vector<String> DATE_FORMAT_LIST = new Vector<>(Arrays.asList("%Y-%m"));
    public static final Vector<String> AVERAGE_LIST = new Vector<>(Arrays.asList("No Average"));
    public static final String TSA_PREDICT = "Predict";
    public static final String TSA_FORECAST_ONCE = "Forecast Once";
    public static final String TSA_FORECAST_VS_ACTUAL = "Forecast vs Actual";
    public static final String TSA_CONTINUOUS_FORECAST = "Continuous Forecast";


    public static final String CLASSIFIER_PREDICT = "Predict";
    public static final String CLASSIFIER_PREDICT_VS_ACTUAL = "Predict Vs. Actual";

    public static final String LR_PREDICT = CLASSIFIER_PREDICT;
    public static final String LR_PREDICT_VS_ACTUAL = CLASSIFIER_PREDICT_VS_ACTUAL;


    public static final String TIME_SERIES_PYTHON_FILE = System.getProperty("user.dir") + "/src/main/java/swissknife/modal/timeseriesanalysis/TimeSeriesAnalysis.py";
    public static final String LINEAR_REGRESSION_PYTHON_FILE = System.getProperty("user.dir") + "/src/main/java/swissknife/modal/linearregression/LinearRegression.py";
    public static final String CLASSIFIER_PYTHON_FILE = System.getProperty("user.dir") + "/src/main/java/swissknife/modal/classifier/Classifier.py";



    public static String getTimeSeriesAnalysisActionName(int action) {
        switch (action) {
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

    public static String getClassifierActionName(int action) {
        String result = null;
        switch (action) {
            case 1:
                return CLASSIFIER_PREDICT_VS_ACTUAL;
            case 2:
                return CLASSIFIER_PREDICT;
        }
        return null;
    }

    public static String getClassifierName(Classifier classifier) {
        if (classifier instanceof SVMPredict || classifier instanceof SVMPredictVsActual) {
            return "Support Vector Machine";
        } else if (classifier instanceof DTPredict || classifier instanceof DTPredictVsActual) {
            return "Decision Tree";
        } else if (classifier instanceof KNNPredict || classifier instanceof KNNPredictVsActual) {
            return "K Nearest Neighbors";
        } else if (classifier instanceof LDPredict || classifier instanceof LDPredictVsActual) {
            return "Linear Discriminant Analysis";
        } else if (classifier instanceof LogRPredict || classifier instanceof LogRPredictVsActual) {
            return "Logistic Regression";
        } else if (classifier instanceof NBPredict || classifier instanceof NBPredictVsActual) {
            return "Naive Bayes";
        }
        return null;
    }



    public static String getAccuracyFile(String classifierName) {
        switch(classifierName){
            case "Support Vector Machine":
                return SVM_PREDICTED_ACTUAL_ONLY_FILE;
            case "Decision Tree":
                return DT_PREDICTED_ACTUAL_ONLY_FILE;
            case "K Nearest Neighbors":
                return KNN_PREDICTED_ACTUAL_ONLY_FILE;
            case "Linear Discriminant Analysis":
                return LD_PREDICTED_ACTUAL_ONLY_FILE;
            case "Logistic Regression":
                return LOGR_PREDICTED_ACTUAL_ONLY_FILE;
            case "Naive Bayes":
                return NB_PREDICTED_ACTUAL_ONLY_FILE;
        }
        return null;
    }

    public static Tool getTimeSeriesAnalysisTool(int action) {
        switch (action) {
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

    public static Tool getNaiveBayesTool(int action) {
        switch (action) {
            case 1:
                return new NBPredictVsActual();
            case 2:
                return new NBPredict();
        }
        return null;
    }


    public static void createRadioButtons(String[] keysList, ButtonGroup buttonGroup, JPanel radioButtons, List<JRadioButton> radioButtonList, String axis, JPanel panel) {
        radioButtons.setLayout(new BorderLayout());
        radioButtons.add(new Label(axis), BorderLayout.NORTH);
        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < keysList.length; ++i) {
            JRadioButton tmp = new JRadioButton(keysList[i]);
            radioButtonList.add(tmp);
            tmp.addActionListener((ActionListener) panel);
            buttonGroup.add(tmp);
            tmpPanel.add(tmp);
        }
        radioButtons.add(tmpPanel, BorderLayout.CENTER);
    }

    public static String getLinearRegressionName(int action) {
        switch (action) {
            case 1:
                return LR_PREDICT_VS_ACTUAL;
            case 2:
                return LR_PREDICT;
        }
        return null;
    }

    public static Tool getLinearRegressionTool(int action) {
        switch (action) {
            case 1:
                return new LRPredictVsActual();
            case 2:
                return new LRPredict();
        }
        return null;
    }



    public static TimeSeries actualTimeSeries;
    public static TimeSeriesCollection predictedTimeSeriesCollection;



    public static ArrayList<Float> calculateAbsoluteError (TimeSeries actual,TimeSeriesCollection predicted){
        ArrayList<Float> result = new ArrayList<Float>();
        for (int i=0;i<predicted.getSeriesCount();++i){
            TimeSeries tmp = predicted.getSeries(i);
            boolean added = false;
            float error = 0;
            for (int j=0;j<tmp.getItemCount();++j){
                RegularTimePeriod timePeriod;
                timePeriod = tmp.getTimePeriod(j);
//                System.out.println("Predicted #"+i+" item #"+j+" Value: "+tmp.getTimePeriod(j) );


                TimeSeriesDataItem singleActual = actual.getDataItem(timePeriod);
                if (singleActual!=null) {
                    added =true;
                    TimeSeriesDataItem singlePredicted = tmp.getDataItem(j);
                    float actualValue = singleActual.getValue().floatValue();
                    float predictedValue = singlePredicted.getValue().floatValue();
                    error += actualValue - predictedValue;
                }
            }
            if (added)
                result.add(Float.valueOf(error));
        }
        return result;
    }

}

