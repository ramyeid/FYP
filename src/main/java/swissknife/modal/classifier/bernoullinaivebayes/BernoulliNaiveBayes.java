package swissknife.modal.classifier.bernoullinaivebayes;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/11/17.
 */
public abstract class BernoulliNaiveBayes extends Classifier{
    public BernoulliNaiveBayes(String inputFile, String keyToPredict, int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.BNB_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.BNB_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.BNB_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.BERNOULLI_NAIVE_BAYES;

    }
    public BernoulliNaiveBayes(){
        super.fileToReadAccuracy = Resources.BNB_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.BNB_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.BNB_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.BERNOULLI_NAIVE_BAYES;

    }
}

