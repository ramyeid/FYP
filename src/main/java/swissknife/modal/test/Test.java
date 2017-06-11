package swissknife.modal.test;

import swissknife.CSVReader;
import swissknife.modal.Tool;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class Test implements Tool{
    protected String inputFile;
    protected String keyToPredict;
    protected int actionTime;
    private float mseError;
    protected int action;
    protected String fileToReadError;
    protected String algorithmName;

    public Test(){
    }
    public Test(String inputFile,String keyToPredict,int actionTime){
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

    private void calculateError(){
        mseError = CSVReader.readError(fileToReadError, "ERROR MSE: ");
    }

    public float getMseError() {
        calculateError();
        return mseError;
    }

    public int getAction() {
        return action;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
}
