package swissknife;

import java.io.IOException;


/**
 * Created by ramyeid on 4/24/17.
 */
public abstract class RunPythonFile {

    public void run(ProcessBuilder pb){
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);

        Process p;
        try {
            p = pb.start();
            p.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
