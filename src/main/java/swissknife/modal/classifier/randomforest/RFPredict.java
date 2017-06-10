package swissknife.modal.classifier.randomforest;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class RFPredict extends RandomForest {

    public RFPredict() {
        super();
        this.action = 2;
    }

    public RFPredict(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        this.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"RF").run();
    }

}
