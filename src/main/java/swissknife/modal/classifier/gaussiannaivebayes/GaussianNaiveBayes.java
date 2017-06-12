package swissknife.modal.classifier.gaussiannaivebayes;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/2/17.
 */
public abstract class GaussianNaiveBayes extends Classifier {

    public GaussianNaiveBayes(String inputFile, String keyToPredict, int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.GNB_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = Resources.GAUSSIAN_NAIVE_BAYES;

    }
    public GaussianNaiveBayes(){
        super.fileToReadAccuracy = Resources.GNB_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = Resources.GAUSSIAN_NAIVE_BAYES;

    }

}

