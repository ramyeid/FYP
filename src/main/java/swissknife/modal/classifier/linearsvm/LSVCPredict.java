package swissknife.modal.classifier.linearsvm;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class LSVCPredict extends LinearSVC{

    public LSVCPredict(){
        super();
        this.action = 2;
    }
    public LSVCPredict(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        this.action = 2;
    }
    @Override
    public void action(){
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"LSVC").run();
    }
}

