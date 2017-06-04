package swissknife.modal.naivebayes.modal;

import swissknife.Resources;
import swissknife.modal.Tool;

/**
 * Created by ramyeid on 6/2/17.
 */
public abstract class NaiveBayes implements Tool {

    protected String inputFile;
    protected String keyToPredict;
    protected int actionTime;
    protected String pythonFile = Resources.NAIVE_BAYES_PYTHON_FILE;

    public NaiveBayes(){

    }
    public NaiveBayes(String inputFile,String keyToPredict,int actionTime){
        this.inputFile = inputFile;
        this.keyToPredict = keyToPredict;
        this.actionTime = actionTime;
    }

    @Override
    public void build(String... arg) {
        inputFile = arg[0];
        keyToPredict = arg[1];
        actionTime = Integer.valueOf(arg[2]);
    }
}
