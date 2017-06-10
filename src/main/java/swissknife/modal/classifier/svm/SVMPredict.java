package swissknife.modal.classifier.svm;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class SVMPredict extends SupportVectorMachine {

    public SVMPredict() {
        super();
        super.action = 2;
    }

    public SVMPredict(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        this.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"SVM").run();
    }

}
