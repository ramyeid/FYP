package swissknife;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CSVReader {


    public static String[] getColumnKeys(String csvFile) {
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


    public static ArrayList<ArrayList<String>> getDataCSVForKeys(String csvFile, String... keysWanted) {
        Boolean gotHeader = true;
        String line = "";
        String csvDelimiter = ",";

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        for (int i = 0; i < keysWanted.length; ++i) {
            data.add(new ArrayList<String>());
        }
        HashMap<Integer, Integer> arrayListIndexToCSVIndex = new HashMap<>();
        HashSet<Integer> indexOfKeysWanted = new HashSet<>();
        int index = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (line.contains("Accuracy") || line.contains("ACCURACY:") || line.contains("ERROR MSE:")){
                    continue;
                }
                if (gotHeader == true) {
                    gotHeader = false;
                    String[] keys = line.split(csvDelimiter);
                    for (int i = 0; i < keys.length; ++i) {
                        for (String keyWanted : keysWanted) {
                            if (keys[i].equals(keyWanted) && !(indexOfKeysWanted.contains(i))) {
                                indexOfKeysWanted.add(Integer.valueOf(i));
                                arrayListIndexToCSVIndex.put(i,index);
                                data.get(index).add(keyWanted);
                                index = index+1;
                            }
                        }
                    }
                    continue;
                }
                String[] values = line.split(csvDelimiter);
                for (int i = 0; i < values.length; ++i) {
                    if (indexOfKeysWanted.contains(i)) {
                        int dataIndex = arrayListIndexToCSVIndex.get(i);
                        data.get(dataIndex).add(values[i]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static ArrayList<ArrayList<Double>> readDataFromCSV(String inputFile, int numberOfData) {
        ArrayList<ArrayList<Double>> result = new ArrayList<>();
        for (int i = 0; i < numberOfData; ++i) {
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
                for (int i = 0; i < numberOfData; ++i) {
                    result.get(i).add(Double.valueOf(a[i]));
                }
                float actual = Float.valueOf(a[1]);
                float predicted = Float.valueOf(a[2]);


            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return result;


    }

    public static float readError(String outputFile, String substring) {
        float result = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(outputFile));
            String line = null;
            List<String> data = new ArrayList<>();
            while ((line = in.readLine()) != null) {
                if (line.contains(substring)) {
                    result = Float.valueOf(line.substring(substring.length(), line.length()));
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