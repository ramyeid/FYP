import java.io.IOException;

/**
 * Created by ramyeid on 4/24/17.
 */
public class RunPythonFile {
    String file;
    String options;


    public RunPythonFile(String file, String options){
        this.file = file;
        this.options = options;
    }

    public RunPythonFile(String file){
        this(file,"");
    }
    public void run(){
        ProcessBuilder pb = new ProcessBuilder("python",this.file);
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
