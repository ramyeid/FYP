package swissknife.modal.classifier.logisticregression;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class LogRPredict extends LogisticRegression
{
    public LogRPredict() {
        super();
        super.action = 2;
    }

    public LogRPredict(String inputFile, String keyToPredict, int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"LR",actionKeys).run();
    }

}
