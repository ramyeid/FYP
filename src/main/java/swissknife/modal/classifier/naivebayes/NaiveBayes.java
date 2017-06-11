package swissknife.modal.classifier.naivebayes;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/2/17.
 */
public abstract class NaiveBayes extends Classifier {

    public NaiveBayes(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.NB_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = "Naive Bayes";

    }
    public NaiveBayes(){
        super.fileToReadAccuracy = Resources.NB_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = "Naive Bayes";

    }

}

