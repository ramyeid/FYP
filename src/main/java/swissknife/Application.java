package swissknife;

/**
 * Created by ramyeid on 4/24/17.
 */
//TODO CONTROL EVERY INPUT FROM THE USER (TEXT FIELD)...

    //TODO ADD CHOICE TO GET NON HEADERS CSV MAYBE AUTOMATIC IF NO HEADERS => CHOOSE COLUMN.
//TODO add compare accuracy for classifiers in tool menu.
//TODO FOR TIME SERIES ANALYSIS PLOT IN DIFFERENT PANEL.
//TODO ADD OPTION TO PLOT MANY ALGORITHM ON THE SAME PANEL. - DO AFTER PLOT IN DIFFERENT PANEL.
//TODO ADD TITLE TO EVERY PANEL.
//TODO INSTEAD OF MSE ERROR - TRY TO PUT RELATIF AND ABSOLUTE ERROR -- CHECK MSE ERROR FORMULA.
//TODO ADD AN OPTION FOR THE USER TO VIEW LIST OF VALUES AFTER ACTION.
//TODO IN JFREECHART FIX THE NAME OF EACH LINE (X- X- ).
//TODO IN CONTINOUS FORECAST - FIX ADD NEXT VALUE BUTTON ( TRY TO THINK OF MORE ERGONOMOIQUE WAY OF ADDING A VALUE)
//TODO ALGORITHMS INTEAD OF TOOLS IN THE MENU BAR.
//TODO TRY TO FIX OVERLAPPING PANELS. BY CREATING THE FIRST PANEL IN THE MIDDLE.
//TODO IN CLASSIFIERS - ADD TO KEYTOPREDICT -> CHOOSE KEYS TO DO THE ACTION WITH.
//TODO IN THE PRESENTATION - IN THE PPT - AFTER Q&A SLIDE ADD SLIDES DETAILING EVERY ALGORITHM - IF THEY ASK ABOUT A CERTAIN ALGO WE CAN EXPLAIN MORE.
//TODO IN THE PRESENTATION - EVERY ALGORITHM IN ONE SLIDE AND THEN FOR MORE DETAILS GO TO SLIDES AFTER Q&A.
//TODO IN THE PRESENTATION - EXPLAIN THAT WE DID NOT GET ANY DATA SO WE ABSTRACTED OUR PROJECT IN ORDER TO ANALYZE EVERY SIGNAL (N'IMPORTE QUEL SIGNAL). -- PROJET AKBAR.
//TODO - FACULTATIF - ADD OPTION IN CLASSIFIERS TO PLOT VALUES THAT THE USER ONE (EXAMPLE CHOOSE X AS TEMPS AND Y AS Y-AXIS AND PLOT.)


public class Application {
    public static void main(String []args) throws InterruptedException {

/*
        String inputFile = System.getProperty("user.dir")+"/src/main/resources/data_2.csv";
        String keyToPredict = "var1";
        int actionTime = 3300;

        KNNPredictVsActual knn = new KNNPredictVsActual(inputFile,keyToPredict,actionTime);
        NBPredictVsActual nb = new NBPredictVsActual(inputFile,keyToPredict,actionTime);
        SVMPredictVsActual svm = new SVMPredictVsActual(inputFile,keyToPredict,actionTime);
        DTPredictVsActual dt = new DTPredictVsActual(inputFile,keyToPredict,actionTime);
        LogRPredictVsActual lr = new LogRPredictVsActual(inputFile,keyToPredict,actionTime);
        LDPredictVsActual ld = new LDPredictVsActual(inputFile,keyToPredict,actionTime);

        knn.action();
        nb.action();
        svm.action();
        dt.action();
        lr.action();
        ld.action();

        System.out.println("KNN: "+knn.getAccuracy(Resources.KNN_PREDICTED_ACTUAL_ONLY_FILE));
        System.out.println("NB: "+nb.getAccuracy(Resources.NB_PREDICTED_ACTUAL_ONLY_FILE));
        System.out.println("SVM: "+svm.getAccuracy(Resources.SVM_PREDICTED_ACTUAL_ONLY_FILE));
        System.out.println("DT: "+dt.getAccuracy(Resources.DT_PREDICTED_ACTUAL_ONLY_FILE));
        System.out.println("lr: "+dt.getAccuracy(Resources.LOGR_PREDICTED_ACTUAL_ONLY_FILE));
        System.out.println("ld: "+dt.getAccuracy(Resources.LD_PREDICTED_ACTUAL_ONLY_FILE));



        inputFile = System.getProperty("user.dir")+"/src/main/resources/data_2_Empty.csv";
        keyToPredict = "var1";
        actionTime = 12;
        KNNPredict knnP = new KNNPredict(inputFile,keyToPredict,actionTime);
        NBPredict nbP = new NBPredict(inputFile,keyToPredict,actionTime);
        SVMPredict svmP = new SVMPredict(inputFile,keyToPredict,actionTime);
        DTPredict dtP = new DTPredict(inputFile,keyToPredict,actionTime);
        LogRPredict lrP = new LogRPredict(inputFile,keyToPredict,actionTime);
        LDPredict ldP = new LDPredict(inputFile,keyToPredict,actionTime);

        knnP.action();
        nbP.action();
        svmP.action();
        dtP.action();
        lrP.action();
        ldP.action();
*/

    }

}
