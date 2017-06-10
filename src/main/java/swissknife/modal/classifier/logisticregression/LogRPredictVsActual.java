package swissknife.modal.classifier.logisticregression;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class LogRPredictVsActual extends LogisticRegression{
    public LogRPredictVsActual() {
        super();
        super.action=1;
    }

    public LogRPredictVsActual(String inputFile, String keyToPredict, int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.action =1;
    }


    @Override
    public void action() {
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"LR",actionKeys).run();
    }

}
