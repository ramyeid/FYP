package swissknife.modal.classifier.knearestneighbors;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class KNearestNeighbors  extends Classifier{

    public KNearestNeighbors(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.KNN_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.KNN_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.KNN_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.K_NEAREST_NEIGHBORS;
    }
    public KNearestNeighbors(){
        super.fileToReadAccuracy = Resources.KNN_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.KNN_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.KNN_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.K_NEAREST_NEIGHBORS;

    }
}


