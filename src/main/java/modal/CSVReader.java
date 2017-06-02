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
}