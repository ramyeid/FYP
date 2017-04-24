/**
 * Created by ramyeid on 4/24/17.
 */
public class Application {
    public static void main(String []args){
//        ScriptEngine engine = new ScriptEngineManager().getEngineByName("python");
        String[] cmd = {
                "ls|",
                "python script.py "
        };
        try {
            Runtime.getRuntime().exec(cmd);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
