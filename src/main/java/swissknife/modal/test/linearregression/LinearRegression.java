package swissknife.modal.test.linearregression;

import swissknife.Resources;
import swissknife.modal.test.Test;

/**
 * Created by ramyeid on 6/4/17.
 */
public abstract class LinearRegression  extends Test {


    public LinearRegression(){
        super.fileToReadError = Resources.LR_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = "Linear Regression";
    }
    public LinearRegression(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadError = Resources.LR_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = "Linear Regression";
    }


    public void build(String ...arg){
        inputFile = arg[0];
        keyToPredict = arg[1];
        actionTime = Integer.valueOf(arg[2]);
    }

}