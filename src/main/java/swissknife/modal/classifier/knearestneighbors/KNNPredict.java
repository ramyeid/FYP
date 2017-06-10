package swissknife.modal.classifier.knearestneighbors;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class KNNPredict extends KNearestNeighbors {

    public KNNPredict() {
        super();
        this.action = 2;
    }

    public KNNPredict(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        this.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"KNN",actionKeys).run();
    }

}



