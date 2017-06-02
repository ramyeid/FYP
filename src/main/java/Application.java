import pages.ContinuousForcastView;

/**
 * Created by ramyeid on 4/24/17.
 */
public class Application {
    public static void main(String []args) throws InterruptedException {


//        new Test();
//        new Index();
//        new TimeSeriesAnalysisView("/Users/ramyeid/Desktop/AirPassengers.csv");
        //1 -- draws the line to see if the values are going up or down -- Coefficient Directeur
        //2 -- forecast of the last actionTime [-ActionTime] Values to see actual is similar to what the prog predicteed for the last ActionTime values.

        //3 -- forecast to +ActionTime to see in the future what is going to happen







//

//        System.out.println("predict 2");
//        //ss1 - predict
        String inputFile = System.getProperty("user.dir")+"/src/main/resources/AirPassengers.csv";
        String keyX = "Date";
        String keyY = "#Passengers";
//        int action = 3;
//        int actionTime = 2;
        String average = "-1";
        String dateFormat ="%Y-%m";
//        int resetCSV = 1;
//        float data = 0.0f;
//        new RunPythonFile(inputFile,keyX,keyY,action,actionTime,average,dateFormat,resetCSV,data).run();
        new ContinuousForcastView(inputFile,keyX,keyY,average,dateFormat);




//        new TimeSeriesAnalysisView(inputFile);

//
//        System.out.println("now wait");
////        Thread.sleep(4000);
//
//        System.out.println("now add number 508.0");
//
//        action = 5;
//        data = 508.0f;
//        new RunPythonFile(inputFile,keyX,keyY,action,actionTime,average,dateFormat,resetCSV,data).run();

//
//        System.out.println("now wait");
//        Thread.sleep(4000);
//
//        System.out.println("now predict once again after 390 for 1 value");


//        action = 4;
//        actionTime = 2;
//        resetCSV = 0;
//        new RunPythonFile(inputFile,keyX,keyY,action,actionTime,average,dateFormat,resetCSV,data).run();
//
//
//
//        System.out.println("now wait");
//        Thread.sleep(4000);
//
//        System.out.println("now add number  461.0");
//
//
//        action = 5;
//        data = 461.0f;
//        new RunPythonFile(inputFile,keyX,keyY,action,actionTime,average,dateFormat,resetCSV,data).run();
//
//
//        System.out.println("now wait");
//        Thread.sleep(4000);
//
//        System.out.println("now add predict 2 after  432");
//
//
//        action = 4;
//        actionTime = 2;
//        resetCSV = 0;
//        new RunPythonFile(inputFile,keyX,keyY,action,actionTime,average,dateFormat,resetCSV,data).run();
//
//



//
//        System.out.println("now wait");
//        Thread.sleep(10000);
//        System.out.println("now next");
////
//
//        action = 4;
//        actionTime = 2;
//        average = "-1";
//        dateFormat ="%Y-%m";
//        resetCSV = 0;
//        data = 0;
//        new RunPythonFile(inputFile,keyX,keyY,action,actionTime,average,dateFormat,resetCSV,data).run();



//

//        String resources = "/Users/ramyeid/Desktop/Proj/FYP/src/main/resources/ContinuousForecast/";
//        String srcOutput = "/Users/ramyeid/Desktop/Proj/FYP/src/main/resources/output.csv";
////        PlotContinuousForecasting.plotContinuousForecasting("TEST", resources+"Forecasts.txt",resources+"/Continuous_output.csv","yyyy-MM");
//
//        PlotPrediction.plotPrediction("TEST",srcOutput,"yyyy-MM");

////
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
