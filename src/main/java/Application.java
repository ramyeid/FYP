import java.util.ArrayList;

/**
 * Created by ramyeid on 4/24/17.
 */
public class Application {
    public static void main(String []args) {
        String pythonFile = "/Users/ramyeid/Desktop/Proj/FYP/src/main/java/script.py";
        new RunPythonFile(pythonFile).run();
//
//        String csvFile = "/Users/ramyeid/Desktop/Proj/FYP/src/main/resources/AirPassengers.csv";
//        CSVReader csvReader = new CSVReader(csvFile);
//        ArrayList<ArrayList<String>> dataFrame = csvReader.getDataCSVForKeys("Date","#Passengers",true);
//        for (String key:csvReader.getColumnKeys()) {
//            System.out.println(key);
//        }
//        System.out.println(dataFrame.size());
//        System.out.println(dataFrame.get(0).size());
//        System.out.println(dataFrame.get(1).size());
//        System.out.println(dataFrame.get(0).get(0));
//        System.out.println(dataFrame.get(1).get(0));
//
//        for(int i=0;i<dataFrame.get(0).size();++i){
//            System.out.println(dataFrame.get(0).get(i)+ "\t"+dataFrame.get(1).get(i));
//        }
    }
}
