package swissknife.modal.classifiers.naivebayes.modal;


import swissknife.modal.classifiers.naivebayes.RunNBPython;

/**
 * Created by ramyeid on 6/2/17.
 */
public class NBPredict extends NaiveBayes {


    public NBPredict() {
        super();
    }

    public NBPredict(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
    }

    @Override
    public void action() {
        new RunNBPython(inputFile,keyToPredict,2,actionTime).run();
    }

}
