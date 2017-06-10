package swissknife.modal.classifier;

import swissknife.CSVReader;
import swissknife.modal.Tool;

/**
 * Created by ramyeid on 6/7/17.
 */
public abstract class Classifier implements Tool {

    protected String inputFile;
    protected String keyToPredict;
    protected int actionTime;
    private float accuracy;
    protected int action;

    public Classifier(){

    }
    public Classifier(String inputFile,String keyToPredict,int actionTime){
        this.inputFile = inputFile;
        this.keyToPredict = keyToPredict;
        this.actionTime = actionTime;
    }

    @Override
    public void build(String... arg) {
        inputFile = arg[0];
        keyToPredict = arg[1];
        actionTime = Integer.valueOf(arg[2]);
    }

    private void calculateAccuracy(String fileToRead){
        accuracy = CSVReader.readError(fileToRead, "ACCURACY: ");

    }

    public float getAccuracy(String fileToRead) {
        calculateAccuracy(fileToRead);
        return accuracy;
    }

    public int getAction() {
        return action;
    }
}
