package swissknife.modal.classifier;

import swissknife.CSVReader;
import swissknife.modal.Tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramyeid on 6/7/17.
 */
public abstract class Classifier implements Tool {

    protected String inputFile;
    protected String keyToPredict;
    protected int actionTime;
    private float accuracy;
    protected int action;
    protected String algorithmName;
    protected String actionKeys;

    protected String fileToReadPredicted;
    protected String fileToReadAccuracy;
    protected String fileAllResultsPvsA;

    public Classifier() {

    }

    public Classifier(String inputFile, String keyToPredict, int actionTime) {
        this.inputFile = inputFile;
        this.keyToPredict = keyToPredict;
        this.actionTime = actionTime;
    }

    @Override
    public void build(String... arg) {
        inputFile = arg[0];
        keyToPredict = arg[1];
        actionTime = Integer.valueOf(arg[2]);
        actionKeys = arg[3];
    }

    private void calculateAccuracy() {
        accuracy = CSVReader.readError(fileToReadAccuracy, "ACCURACY: ");

    }

    public float getAccuracy() {
        calculateAccuracy();
        return accuracy;
    }

    public int getAction() {
        return action;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setActionKeys(String actionKeys) {
        this.actionKeys = actionKeys;
    }

    public String getFileToReadPredicted(){
        return fileToReadPredicted;
    }
    public String getFileToReadAccuracy() {
        return fileToReadAccuracy;
    }
    public String getFileAllResultsPvsA(){
        return fileAllResultsPvsA;
    }




//PREDICT & PREDICT VS ACTUAL.
    //all columns, all values -- For Predict and Predict Vs Actual
    public ArrayList<ArrayList<String>> getAllValues(String file) {
        return CSVReader.getDataCSVForKeys(file, CSVReader.getColumnKeys(file));
    }
    //all columns, actionTime values -- For Predict and Predict Vs Actual
    public ArrayList<ArrayList<String>> getAllValuesForActionTime(String file) {
        ArrayList<ArrayList<String>> data = getAllValues(file);
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i = 0; i < data.size(); ++i) {
            result.add(new ArrayList<>());
            for (int j = data.get(i).size() - actionTime; j < data.get(i).size(); ++j) {
                result.get(i).add(data.get(i).get(j));
            }
        }
        return result;
    }
//PREDICT VS ACTUAL.


    //TODO TEST IT
    //Columns Actual And Predicted, actiontime Values
    public ArrayList<ArrayList<String>> getValuesOfActualAndPredictedForActionTime(){
        ArrayList<ArrayList<String>> data = CSVReader.getDataCSVForKeys(this.fileToReadAccuracy,CSVReader.getColumnKeys(fileToReadAccuracy));
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i = 0; i < data.size(); ++i) {
            result.add(new ArrayList<>());
            for (int j = data.get(i).size() - actionTime; j < data.get(i).size(); ++j) {
                result.get(i).add(data.get(i).get(j));
            }
        }
        return result;
    }

    //actionKeys Columns, actionTime Values
    public ArrayList<ArrayList<String>> getValuesOfActionKeysForActionTime_PredictVsActual(){
        String tmpActionKeys = actionKeys;
        tmpActionKeys+="/"+keyToPredict +"/"+keyToPredict+" Actual & Predicted";
        String []action_Keys = tmpActionKeys.substring(1).split("/");

        ArrayList<ArrayList<String>> data = CSVReader.getDataCSVForKeys(fileAllResultsPvsA,action_Keys);
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        for (int i = 0; i < data.size(); ++i) {
            result.add(new ArrayList<>());
            for (int j = data.get(i).size() - actionTime; j < data.get(i).size(); ++j) {
                result.get(i).add(data.get(i).get(j));
            }
        }
        return result;
    }


//PREDICT
    //actionKeys columns, all values
    public ArrayList<ArrayList<String>> getValuesOfActionKeys_Predict(){
        String tmpActionKeys = actionKeys;
        tmpActionKeys+="/"+keyToPredict+"Predict & Actual";
        String []action_Keys = tmpActionKeys.substring(1).split("/");
        return CSVReader.getDataCSVForKeys(fileToReadPredicted,action_Keys);
    }

    //actionKeys columns, ActionTime values
    public ArrayList<ArrayList<String>> getValuesOfActionKeysForActiontime_Predict(){
        ArrayList<ArrayList<String>> data = getValuesOfActionKeys_Predict();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i = 0; i < data.size(); ++i) {
            result.add(new ArrayList<>());
            for (int j = data.get(i).size() - actionTime; j < data.get(i).size(); ++j) {
                result.get(i).add(data.get(i).get(j));
            }
        }
        return result;
    }

    //keyToPredict column, all values
    public ArrayList<String> getValuesOfPredicted_Predict() {
        return CSVReader.getDataCSVForKeys(fileToReadPredicted, this.keyToPredict + "Predict & Actual").get(0);
    }

    //keyToPredict column, actionTime values
    public ArrayList<String> getValuesOfPredictedForActionTime_Predict() {
        List<String> data = getValuesOfPredicted_Predict();
        ArrayList<String> result = new ArrayList<>();
        for (int i = data.size() - this.actionTime; i < data.size(); ++i) {
            result.add(data.get(i));
        }
        return result;

    }




}
