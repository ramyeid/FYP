package modal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    String csvFile;

    public CSVReader(String csvFile) {
        this.csvFile = csvFile;
    }

    public String[] getColumnKeys() {
        String line = "";
        String cvsSplitBy = ",";
        String[] columnKeys = null;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            line = br.readLine();
            columnKeys = line.split(cvsSplitBy);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnKeys;
    }





    public <T> ArrayList<ArrayList<T>> getDataCSVForKeys(String keyX, String keyY,boolean gotHeader) {
        String line = "";
        String csvSplitBy = ",";

        ArrayList<T> dataX = new ArrayList<>();
        ArrayList<T> dataY = new ArrayList<>();

        int indexX = 0;
        int indexY = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (gotHeader == true) {
                    gotHeader = false;
                    String[] keys = line.split(csvSplitBy);
                    for (int i = 0; i < keys.length; ++i) {
                        if (keys[i].equals(keyX)) {
                            indexX = i;
                        } else if (keys[i].equals(keyY)) {
                            indexY = i;
                        }
                    }

                }
                T[] data = (T[]) line.split(csvSplitBy);
                for (int i = 0; i < data.length; ++i) {
                    if (i == indexX) {
                        dataX.add(data[i]);
                    } else if (i == indexY) {
                        dataY.add(data[i]);
                    }
                }
            }
            ArrayList<ArrayList<T>> temp = new ArrayList<>();
            temp.add(dataX);
            temp.add(dataY);
            return temp;

        }

        catch(FileNotFoundException e){
                e.printStackTrace();
                return null;
        }
        catch(IOException e){
                e.printStackTrace();
                return null;
        }
    }



    public static ArrayList<ArrayList<Double>> readDataFromCSV(String inputFile, int numberOfData){
        ArrayList<ArrayList<Double>> result = new ArrayList<>();
        for (int i=0;i<numberOfData;++i){
            result.add(new ArrayList<Double>());
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            String s = null;
            while ((s = in.readLine()) != null) {
                if (s.matches(".*[a-zA-Z]+.*")) {
                    continue;
                }

                String[] a = s.split(",");
                for(int i=0;i<numberOfData;++i){
                    result.get(i).add( Double.valueOf(a[i]));
                }
                float actual = Float.valueOf(a[1]);
                float predicted = Float.valueOf(a[2]);


            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return result;



    }
}