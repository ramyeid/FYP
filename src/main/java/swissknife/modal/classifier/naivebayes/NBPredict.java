package swissknife.modal.classifier.naivebayes;


import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/2/17.
 */
public class NBPredict extends NaiveBayes {


    public NBPredict() {
        super();
        super.action = 2;
    }

    public NBPredict(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"NB").run();
    }

}
