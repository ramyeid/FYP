package swissknife.modal.test.linearregression;

import swissknife.modal.test.RunTestPython;

/**
 * Created by ramyeid on 6/4/17.
 */
public class LRPredict extends LinearRegression {
    public LRPredict(){
        super();
        this.action = 2;
    }
    public LRPredict(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        this.action = 2;
    }
    @Override
    public void action(){
        new RunTestPython(inputFile,keyToPredict,action,actionTime,"LR").run();
    }
}


