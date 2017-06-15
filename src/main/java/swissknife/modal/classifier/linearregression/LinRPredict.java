package swissknife.modal.classifier.linearregression;

import swissknife.modal.test.RunTestPython;

/**
 * Created by ramyeid on 6/15/17.
 */
public class LinRPredict extends LinearRegression{


    public LinRPredict(){
        super();
        this.action = 2;
    }
    public LinRPredict(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        this.action = 2;
    }
    @Override
    public void action(){
        new RunTestPython(inputFile,keyToPredict,action,actionTime,"LR").run();
    }
}

