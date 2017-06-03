package swissknife.modal.naivebayes.modal;

import swissknife.Resources;
import swissknife.modal.naivebayes.RunNBPython;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramyeid on 6/2/17.
 */
public class NBPredictVsActual extends NaiveBayes {


    public float getAccuracy() {
        return accuracy;
    }

    private float accuracy;

    public NBPredictVsActual(){

    }
    public NBPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
    }

    @Override
    public void action() {
        new RunNBPython(inputFile, keyToPredict, 1, actionTime).run();
        accuracy = readAccuracy(Resources.NB_PREDICTED_ACTUAL_ONLY_FILE);
    }


    public float readAccuracy(String outputFile) {
        float result = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(outputFile));
            String line = null;
            List<String> data = new ArrayList<>();
            while ((line = in.readLine()) != null) {
                if (line.contains("ACCURACY: ")) {
                    result = Float.valueOf(line.substring(10, line.length()));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}