package swissknife.modal.naivebayes.modal;

import swissknife.modal.naivebayes.RunNBPython;

/**
 * Created by ramyeid on 6/2/17.
 */
public class NBPredictVsActual extends NaiveBayes {


    public NBPredictVsActual(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
    }

    @Override
    public void action() {
        new RunNBPython(inputFile,keyToPredict,1,actionTime).run();

    }

}
