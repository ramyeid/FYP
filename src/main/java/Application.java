import java.io.IOException;

/**
 * Created by ramyeid on 4/24/17.
 */
public class Application {
    public static void main(String []args) {

        ProcessBuilder pb = new ProcessBuilder("python","/Users/ramyeid/Desktop/Proj/PythonTimeSeriååes/test.py");
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        Process p = null;
        try {
            p = pb.start();å
            p.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
