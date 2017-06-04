package swissknife.modal.linearregression.modal;

import swissknife.modal.Tool;

/**
 * Created by ramyeid on 6/4/17.
 */
public abstract class LinearRegression  implements Tool{

    protected String inputFile;
    protected String keyToPredict;
    protected int actionTime;

    public LinearRegression(){

    }
    public LinearRegression(String inputFile,String keyToPredict,int actionTime){
        this.inputFile = inputFile;
        this.keyToPredict = keyToPredict;
        this.actionTime = actionTime;
    }


    public void build(String ...arg){
        inputFile = arg[0];
        keyToPredict = arg[1];
        actionTime = Integer.valueOf(arg[2]);
    }

}


