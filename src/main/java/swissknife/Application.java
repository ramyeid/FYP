package swissknife;

import swissknife.modal.classifier.decisiontree.DTPredict;
import swissknife.modal.classifier.decisiontree.DTPredictVsActual;
import swissknife.modal.classifier.gradientboosting.GBPredict;
import swissknife.modal.classifier.gradientboosting.GBPredictVsActual;
import swissknife.modal.classifier.knearestneighbors.KNNPredict;
import swissknife.modal.classifier.knearestneighbors.KNNPredictVsActual;
import swissknife.modal.classifier.lineardiscriminant.LDPredict;
import swissknife.modal.classifier.lineardiscriminant.LDPredictVsActual;
import swissknife.modal.classifier.linearsvm.LSVCPredict;
import swissknife.modal.classifier.linearsvm.LSVCPredictVsActual;
import swissknife.modal.classifier.logisticregression.LogRPredict;
import swissknife.modal.classifier.logisticregression.LogRPredictVsActual;
import swissknife.modal.classifier.naivebayes.NBPredict;
import swissknife.modal.classifier.naivebayes.NBPredictVsActual;
import swissknife.modal.classifier.randomforest.RFPredict;
import swissknife.modal.classifier.randomforest.RFPredictVsActual;
import swissknife.modal.classifier.svm.SVMPredict;
import swissknife.modal.classifier.svm.SVMPredictVsActual;

/**
 * Created by ramyeid on 4/24/17.
 */
//TODO CONTROL EVERY INPUT FROM THE USER (TEXT FIELD)...
//TODO ADD in menu bar - print graph
//TODO ADD in menu bar - SAVE GRAPHs
//TODO ADD CHOICE TO GET NON HEADERS CSV MAYBE AUTOMATIC IF NO HEADERS => CHOOSE COLUMN.
//TODO add compare accuracy for classifiers in tool menu.
//TODO FOR TIME SERIES ANALYSIS PLOT IN DIFFERENT PANEL.
//TODO ADD OPTION TO PLOT MANY ALGORITHM ON THE SAME PANEL. - DO AFTER PLOT IN DIFFERENT PANEL.
//TODO INSTEAD OF MSE ERROR - TRY TO PUT RELATIF AND ABSOLUTE ERROR -- CHECK MSE ERROR FORMULA.
//TODO ADD AN OPTION FOR THE USER TO VIEW LIST OF VALUES AFTER ACTION.
//TODO IN CONTINOUS FORECAST - FIX ADD NEXT VALUE BUTTON ( TRY TO THINK OF MORE ERGONOMOIQUE WAY OF ADDING A VALUE)
//TODO TOOLS INTEAD OF ALGORITHM IN THE MENU BAR.
//TODO TRY TO FIX OVERLAPPING PANELS. BY CREATING THE FIRST PANEL IN THE MIDDLE.
//TODO IN THE PRESENTATION - IN THE PPT - AFTER Q&A SLIDE ADD SLIDES DETAILING EVERY ALGORITHM - IF THEY ASK ABOUT A CERTAIN ALGO WE CAN EXPLAIN MORE.
//TODO IN THE PRESENTATION - EVERY ALGORITHM IN ONE SLIDE AND THEN FOR MORE DETAILS GO TO SLIDES AFTER Q&A.
//TODO IN THE PRESENTATION - EXPLAIN THAT WE DID NOT GET ANY DATA SO WE ABSTRACTED OUR PROJECT IN ORDER TO ANALYZE EVERY SIGNAL (N'IMPORTE QUEL SIGNAL). -- PROJET AKBAR.
//TODO - FACULTATIF - ADD OPTION IN CLASSIFIERS TO PLOT VALUES THAT THE USER ONE (EXAMPLE CHOOSE X AS TEMPS AND Y AS Y-AXIS AND PLOT.)

//TODO - CHECK ERROR CALCULATE IN CONTINUOUSFORECAST FUNCTION IN PLOTCONTINUOUSFORECAST.
    //TODO CHECK WITH MEZHER IF ABSOLUTE ERROR IS OKAY.
//TODO CHECK http://www.scipy-lectures.org/packages/scikit-learn/    3.6.3.1. K-means clustering
public class Application {
    public static void main(String[] args) throws InterruptedException {
/*
        String inputFile = System.getProperty("user.dir")+"/src/main/resources/data_2.csv";
        KNNPredictVsActual knn = new KNNPredictVsActual();
        JFrame frame = new JFrame();
        JInternalFrame intFrame = new JInternalFrame();
        ClassifierPanel classifierPanel = new ClassifierPanel(knn,inputFile,intFrame);
        intFrame.add(classifierPanel);
        intFrame.setVisible(true);
        intFrame.pack();

        frame.add(intFrame);
        frame.setVisible(true);
        frame.pack();
    */





        String inputFile = System.getProperty("user.dir")+"/src/main/resources/data_2.csv";
        String keyToPredict = "var1";
        int actionTime = 3300;

        KNNPredictVsActual knn = new KNNPredictVsActual(inputFile,keyToPredict,actionTime);
        NBPredictVsActual nb = new NBPredictVsActual(inputFile,keyToPredict,actionTime);
        SVMPredictVsActual svm = new SVMPredictVsActual(inputFile,keyToPredict,actionTime);
        DTPredictVsActual dt = new DTPredictVsActual(inputFile,keyToPredict,actionTime);
        LogRPredictVsActual lr = new LogRPredictVsActual(inputFile,keyToPredict,actionTime);
        LDPredictVsActual ld = new LDPredictVsActual(inputFile,keyToPredict,actionTime);
        RFPredictVsActual rf = new RFPredictVsActual(inputFile,keyToPredict,actionTime);
        GBPredictVsActual gb = new GBPredictVsActual(inputFile,keyToPredict,actionTime);
        LSVCPredictVsActual lsvc = new LSVCPredictVsActual(inputFile,keyToPredict,actionTime);

        gb.action();
        rf.action();
        knn.action();
        nb.action();
        svm.action();
        dt.action();
        lr.action();
        ld.action();
        lsvc.action();

        System.out.println("KNN: "+knn.getAccuracy());
        System.out.println("NB: "+nb.getAccuracy());
        System.out.println("SVM: "+svm.getAccuracy());
        System.out.println("DT: "+dt.getAccuracy());
        System.out.println("lr: "+lr.getAccuracy());
        System.out.println("ld: "+ld.getAccuracy());
        System.out.println("rf: "+rf.getAccuracy());
        System.out.println("gb: "+gb.getAccuracy());
        System.out.println("lsvc"+ lsvc.getAccuracy());


        inputFile = System.getProperty("user.dir")+"/src/main/resources/data_2_Empty.csv";
        keyToPredict = "var1";
        actionTime = 12;
        KNNPredict knnP = new KNNPredict(inputFile,keyToPredict,actionTime);
        NBPredict nbP = new NBPredict(inputFile,keyToPredict,actionTime);
        SVMPredict svmP = new SVMPredict(inputFile,keyToPredict,actionTime);
        DTPredict dtP = new DTPredict(inputFile,keyToPredict,actionTime);
        LogRPredict lrP = new LogRPredict(inputFile,keyToPredict,actionTime);
        LDPredict ldP = new LDPredict(inputFile,keyToPredict,actionTime);
        RFPredict rfP = new RFPredict(inputFile,keyToPredict,actionTime);
        GBPredict gbP = new GBPredict(inputFile,keyToPredict,actionTime);
        LSVCPredict lsvcP = new LSVCPredict(inputFile,keyToPredict,actionTime);

        knnP.action();
        nbP.action();
        svmP.action();
        dtP.action();
        lrP.action();
        ldP.action();
        rfP.action();
        gbP.action();
        lsvcP.action();



/*
        System.out.println("NOW LINEAR STUFF - TEST");

        LRPredictVsActual linR = new LRPredictVsActual(inputFile,keyToPredict,actionTime);

        linR.action();

        System.out.println("linr"+ linR.getMseError());




        System.out.println("NOW LINEAR STUFF - TEST");

        LRPredict linRP = new LRPredict(inputFile,keyToPredict,actionTime);

        linRP.action();
*/




    }

}
