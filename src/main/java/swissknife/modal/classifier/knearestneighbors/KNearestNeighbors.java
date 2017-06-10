package swissknife.modal.classifier.knearestneighbors;

import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class KNearestNeighbors  extends Classifier{

    public KNearestNeighbors(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
    }
    public KNearestNeighbors(){}
}