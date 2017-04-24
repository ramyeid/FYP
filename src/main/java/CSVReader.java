
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    //got index
    //  if you check in folder resources : -> Airpasseengers you can see that it has a header "Month" - "#PAssengers"
    // if it has a header then you should specify header keyX and the other header keyY that you want to take from the csv.
    // if you only have 2 vectors you can set keyX to "" and keyY to "" and gotHeader to false.
    // If you need to extract more than 2 vectors this function is not what you re looking for.
    // write in your own in the same file but after this function.
    // I will not modify this file anymore so you can modify it.
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