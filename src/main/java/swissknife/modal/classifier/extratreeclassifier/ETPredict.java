package swissknife.modal.classifier.extratreeclassifier;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class ETPredict extends ExtraTreeClassifier {

    public ETPredict(){
        super();
        this.action = 2;
    }
    public ETPredict(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"ET",actionKeys).run();
    }
}