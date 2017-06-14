package swissknife;


import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import swissknife.modal.Tool;
import swissknife.modal.classifier.Classifier;
import swissknife.modal.classifier.bernoullinaivebayes.BNBPredict;
import swissknife.modal.classifier.bernoullinaivebayes.BNBPredictVsActual;
import swissknife.modal.classifier.decisiontree.DTPredict;
import swissknife.modal.classifier.decisiontree.DTPredictVsActual;
import swissknife.modal.classifier.extratreeclassifier.ETPredict;
import swissknife.modal.classifier.extratreeclassifier.ETPredictVsActual;
import swissknife.modal.classifier.gaussiannaivebayes.GNBPredict;
import swissknife.modal.classifier.gaussiannaivebayes.GNBPredictVsActual;
import swissknife.modal.classifier.gradientboosting.GBPredict;
import swissknife.modal.classifier.gradientboosting.GBPredictVsActual;
import swissknife.modal.classifier.knearestneighbors.KNNPredict;
import swissknife.modal.classifier.knearestneighbors.KNNPredictVsActual;
import swissknife.modal.classifier.lineardiscriminant.LDPredict;
import swissknife.modal.classifier.lineardiscriminant.LDPredictVsActual;
import swissknife.modal.classifier.linearsvm.LSVCPredict;
import swissknife.modal.classifier.linearsvm.LSVCPredictVsActual;
import swissknife.modal.classifier.logisticregression.LogRPredict;
import swissknife.modal.classifier.logisticregression.LogRPredictVsActual;
import swissknife.modal.classifier.randomforest.RFPredict;
import swissknife.modal.classifier.randomforest.RFPredictVsActual;
import swissknife.modal.classifier.ridge.RCPredict;
import swissknife.modal.classifier.ridge.RCPredictVsActual;
import swissknife.modal.classifier.stochasticgradientdescent.SGDPredict;
import swissknife.modal.classifier.stochasticgradientdescent.SGDPredictVsActual;
import swissknife.modal.classifier.svm.SVMPredict;
import swissknife.modal.classifier.svm.SVMPredictVsActual;
import swissknife.modal.test.linearregression.LRPredict;
import swissknife.modal.test.linearregression.LRPredictVsActual;
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
    private static final String BASE_FILE_GNB = System.getProperty("user.dir") + "/src/main/resources/GaussianNaiveBayes/";
    private static final String BASE_FILE_KNN = System.getProperty("user.dir") + "/src/main/resources/KNearestNeighbors/";
    private static final String BASE_FILE_SVM = System.getProperty("user.dir") + "/src/main/resources/SVM/";
    private static final String BASE_FILE_DT = System.getProperty("user.dir") + "/src/main/resources/DecisionTree/";
    private static final String BASE_FILE_LOGR = System.getProperty("user.dir") + "/src/main/resources/LogisticRegression/";
    private static final String BASE_FILE_LD = System.getProperty("user.dir") + "/src/main/resources/LinearDiscriminant/";
    private static final String BASE_FILE_RF = System.getProperty("user.dir") + "/src/main/resources/RandomForest/";
    private static final String BASE_FILE_GB = System.getProperty("user.dir") + "/src/main/resources/GradientBoosting/";
    private static final String BASE_FILE_LSVC = System.getProperty("user.dir") + "/src/main/resources/LinearSVC/";
    private static final String BASE_FILE_SGD = System.getProperty("user.dir") + "/src/main/resources/StochasticGradientDescent/";
    private static final String BASE_FILE_ETC = System.getProperty("user.dir") + "/src/main/resources/ExtraTreeClassifier/";
    private static final String BASE_FILE_RC = System.getProperty("user.dir") + "/src/main/resources/RidgeClassifier/";
    private static final String BASE_FILE_BNB = System.getProperty("user.dir") + "/src/main/resources/BernoulliNaiveBayes/";


    private static final String BASE_FILE_LR = System.getProperty("user.dir") + "/src/main/resources/LinearRegression/";


    public static final String TSA_FORECAST_ONCE_OUTPUT_FILE = BASE_FILE_TSA + "/Forecast_Once_Output.csv";

    public static final String TSA_PREDICT_OUTPUT_FILE = BASE_FILE_TSA + "Predict_Output.csv";
    public static final String TSA_FORECAST_VS_ACTUAL_OUTPUT_FILE = BASE_FILE_TSA + "Forecast_vs_Actual_Output.csv";
    public static final String CONTINUOS_FORECAST_OUTPUT_FILE = BASE_FILE_TSA + "ContinuousForecast/";


    public static final String KNN_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_KNN + "Predicted_Actual_Only.csv";
    public static final String GNB_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_GNB + "Predicted_Actual_Only.csv";
    public static final String SVM_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_SVM + "Predicted_Actual_Only.csv";
    public static final String DT_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_DT + "Predicted_Actual_Only.csv";
    public static final String LOGR_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_LOGR + "Predicted_Actual_Only.csv";
    public static final String LD_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_LD + "Predicted_Actual_Only.csv";
    public static final String RF_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_RF + "Predicted_Actual_Only.csv";
    public static final String GB_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_GB + "Predicted_Actual_Only.csv";
    public static final String LSVC_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_LSVC + "Predicted_Actual_Only.csv";
    public static final String SGD_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_SGD + "Predicted_Actual_Only.csv";
    public static final String ET_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_ETC + "Predicted_Actual_Only.csv";
    public static final String RC_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_RC + "Predicted_Actual_Only.csv";
    public static final String BNB_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_BNB + "Predicted_Actual_Only.csv";


    public static final String LR_PREDICTED_ACTUAL_ONLY_FILE = BASE_FILE_LR + "Predicted_Actual_Only.csv";







    public static final String KNN_PREDICTED_RESULT_FILE = BASE_FILE_KNN + "Predicted_result.csv";
    public static final String GNB_PREDICTED_RESULT_FILE = BASE_FILE_GNB + "Predicted_result.csv";
    public static final String SVM_PREDICTED_RESULT_FILE = BASE_FILE_SVM + "Predicted_result.csv";
    public static final String DT_PREDICTED_RESULT_FILE = BASE_FILE_DT + "Predicted_result.csv";
    public static final String LOGR_PREDICTED_RESULT_FILE = BASE_FILE_LOGR + "Predicted_result.csv";
    public static final String LD_PREDICTED_RESULT_FILE = BASE_FILE_LD + "Predicted_result.csv";
    public static final String RF_PREDICTED_RESULT_FILE = BASE_FILE_RF + "Predicted_result.csv";
    public static final String GB_PREDICTED_RESULT_FILE = BASE_FILE_GB + "Predicted_result.csv";
    public static final String LSVC_PREDICTED_RESULT_FILE = BASE_FILE_LSVC + "Predicted_result.csv";
    public static final String SGD_PREDICTED_RESULT_FILE = BASE_FILE_SGD + "Predicted_result.csv";
    public static final String ET_PREDICTED_RESULT_FILE = BASE_FILE_ETC + "Predicted_result.csv";
    public static final String RC_PREDICTED_RESULT_FILE = BASE_FILE_RC + "Predicted_result.csv";
    public static final String BNB_PREDICTED_RESULT_FILE = BASE_FILE_BNB + "Predicted_result.csv";

    public static final String LR_PREDICTED_RESULT_FILE = BASE_FILE_LR + "Predicted_result.csv";




    public static final String KNN_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_KNN + "Predicted_Actual_Result.csv";
    public static final String GNB_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_GNB + "Predicted_Actual_Result.csv";
    public static final String SVM_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_SVM + "Predicted_Actual_Result.csv";
    public static final String DT_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_DT + "Predicted_Actual_Result.csv";
    public static final String LOGR_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_LOGR + "Predicted_Actual_Result.csv";
    public static final String LD_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_LD + "Predicted_Actual_Result.csv";
    public static final String RF_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_RF + "Predicted_Actual_Result.csv";
    public static final String GB_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_GB + "Predicted_Actual_Result.csv";
    public static final String LSVC_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_LSVC + "Predicted_Actual_Result.csv";
    public static final String SGD_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_SGD + "Predicted_Actual_Result.csv";
    public static final String ET_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_ETC + "Predicted_Actual_Result.csv";
    public static final String RC_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_RC + "Predicted_Actual_Result.csv";
    public static final String BNB_PREDICTED_ACTUAL_RESULT_FILE = BASE_FILE_BNB + "Predicted_Actual_Result.csv";

    public static final String LR_PREDICTED_ACTUAL_FILE = BASE_FILE_LR + "Predicted_Actual_Result.csv";














    public static final Vector<String> DATE_FORMAT_LIST = new Vector<>(Arrays.asList("%Y-%m"));
    public static final Vector<String> AVERAGE_LIST = new Vector<>(Arrays.asList("No Average"));
    public static final String TSA_PREDICT = "Predict";
    public static final String TSA_FORECAST_ONCE = "Forecast Once";
    public static final String TSA_FORECAST_VS_ACTUAL = "Predict Vs Actual";
    public static final String TSA_CONTINUOUS_FORECAST = "Continuous Forecast";


    public static final String CLASSIFIER_PREDICT = "Predict";
    public static final String CLASSIFIER_PREDICT_VS_ACTUAL = "Predict Vs. Actual";

    public static final String LR_PREDICT = CLASSIFIER_PREDICT;
    public static final String LR_PREDICT_VS_ACTUAL = CLASSIFIER_PREDICT_VS_ACTUAL;


    public static final String TIME_SERIES_PYTHON_FILE = System.getProperty("user.dir") + "/src/main/java/swissknife/modal/timeseriesanalysis/TimeSeriesAnalysis.py";
    public static final String LINEAR_REGRESSION_PYTHON_FILE = System.getProperty("user.dir") + "/src/main/java/swissknife/modal/test/Test.py";
    public static final String CLASSIFIER_PYTHON_FILE = System.getProperty("user.dir") + "/src/main/java/swissknife/modal/classifier/Classifier.py";


    public static final String BERNOULLI_NAIVE_BAYES = "Bernoulli Naive Bayes";
    public static final String DECISION_TREE = "Decision Tree";
    public static final String EXTRA_TREE_CLASSIFIER = "Extra Tree Classifier";
    public static final String GRADIENT_BOOSTING_CLASSIFIER = "Gradient Boosting Classifier";
    public static final String K_NEAREST_NEIGHBORS = "K Nearest Neighbors";
    public static final String LINEAR_DISCRIMINANT_ANALYSIS = "Linear Discriminant Analysis";
    public static final String LINEAR_SVC = "Linear SVC";
    public static final String LOGISTIC_REGRESSION = "Logistic Regression";
    public static final String GAUSSIAN_NAIVE_BAYES = "Gaussian Naive Bayes";
    public static final String RANDOM_FOREST = "Random Forest";
    public static final String RIDGE_CLASSIFIER = "Ridge Classifier";
    public static final String STOCHASTIC_GRADIENT_DESCENT = "Stochastic Gradient Descent";
    public static final String SUPPORT_VECTOR_MACHINE = "Support Vector Machine";


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



    //Continuous Forecast function.
    public static ArrayList<Float> calculateAbsoluteError(TimeSeries actual, TimeSeriesCollection predicted) {
        ArrayList<Float> result = new ArrayList<Float>();
        for (int i = 0; i < predicted.getSeriesCount(); ++i) {
            TimeSeries tmp = predicted.getSeries(i);
            boolean added = false;
            float error = 0;
            for (int j = 0; j < tmp.getItemCount(); ++j) {
                RegularTimePeriod timePeriod;
                timePeriod = tmp.getTimePeriod(j);
//                System.out.println("Predicted #"+i+" item #"+j+" Value: "+tmp.getTimePeriod(j) );


                TimeSeriesDataItem singleActual = actual.getDataItem(timePeriod);
                if (singleActual != null) {
                    added = true;
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

    public static ArrayList<String> classifierNames = new ArrayList<>(
            Arrays.asList(GAUSSIAN_NAIVE_BAYES, DECISION_TREE, EXTRA_TREE_CLASSIFIER,
                    GRADIENT_BOOSTING_CLASSIFIER, K_NEAREST_NEIGHBORS, LINEAR_DISCRIMINANT_ANALYSIS,
                    LINEAR_SVC, LOGISTIC_REGRESSION, BERNOULLI_NAIVE_BAYES, RANDOM_FOREST, RIDGE_CLASSIFIER,
                    STOCHASTIC_GRADIENT_DESCENT, SUPPORT_VECTOR_MACHINE));


    public static Classifier getClassifierForNameAndAction(String s, int action) {
        String classifierName = s + " " + action;
        return classifiers.get(classifierName);
    }

    public static HashMap<String, Classifier> classifiers = (HashMap<String, Classifier>) createMap();

    private static Map<String, Classifier> createMap() {
        Map<String, Classifier> myMap = new HashMap<>();
        myMap.put(GAUSSIAN_NAIVE_BAYES + " 1", new GNBPredictVsActual());
        myMap.put(GAUSSIAN_NAIVE_BAYES + " 2", new GNBPredict());

        myMap.put(K_NEAREST_NEIGHBORS + " 1", new KNNPredictVsActual());
        myMap.put(K_NEAREST_NEIGHBORS + " 2", new KNNPredict());

        myMap.put(LOGISTIC_REGRESSION + " 1", new LogRPredictVsActual());
        myMap.put(LOGISTIC_REGRESSION + " 2", new LogRPredict());

        myMap.put(LINEAR_DISCRIMINANT_ANALYSIS + " 1", new LDPredictVsActual());
        myMap.put(LINEAR_DISCRIMINANT_ANALYSIS + " 2", new LDPredict());

        myMap.put(DECISION_TREE + " 1", new DTPredictVsActual());
        myMap.put(DECISION_TREE + " 2", new DTPredict());

        myMap.put(SUPPORT_VECTOR_MACHINE + " 1", new SVMPredictVsActual());
        myMap.put(SUPPORT_VECTOR_MACHINE + " 2", new SVMPredict());

        myMap.put(RANDOM_FOREST + " 1", new RFPredictVsActual());
        myMap.put(RANDOM_FOREST + " 2", new RFPredict());

        myMap.put(GRADIENT_BOOSTING_CLASSIFIER + " 1", new GBPredictVsActual());
        myMap.put(GRADIENT_BOOSTING_CLASSIFIER + " 2", new GBPredict());

        myMap.put(LINEAR_SVC + " 1", new LSVCPredictVsActual());
        myMap.put(LINEAR_SVC + " 2", new LSVCPredict());

        myMap.put(STOCHASTIC_GRADIENT_DESCENT + " 1", new SGDPredictVsActual());
        myMap.put(STOCHASTIC_GRADIENT_DESCENT + " 2", new SGDPredict());

        myMap.put(EXTRA_TREE_CLASSIFIER + " 1", new ETPredictVsActual());
        myMap.put(EXTRA_TREE_CLASSIFIER + " 2", new ETPredict());

        myMap.put(RIDGE_CLASSIFIER+" 1", new RCPredictVsActual());
        myMap.put(RIDGE_CLASSIFIER+" 2", new RCPredict());

        myMap.put(BERNOULLI_NAIVE_BAYES+" 1", new BNBPredictVsActual());
        myMap.put(BERNOULLI_NAIVE_BAYES+" 2", new BNBPredict());

        return myMap;
    }
}