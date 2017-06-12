package swissknife.modal.classifier.gaussiannaivebayes;


import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/2/17.
 */
public class GNBPredict extends GaussianNaiveBayes {


    public GNBPredict() {
        super();
        super.action = 2;
    }

    public GNBPredict(String inputFile, String keyToPredict, int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"GNB",actionKeys).run();
    }

}
