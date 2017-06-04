package swissknife.modal.linearregression.modal;

import swissknife.modal.linearregression.RunLRPython;

/**
 * Created by ramyeid on 6/4/17.
 */
public class LRPredict extends LinearRegression {
    public LRPredict(){
        super();
    }
    public LRPredict(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
    }
    @Override
    public void action(){
        new RunLRPython(inputFile,keyToPredict,2,actionTime).run();
    }
}

