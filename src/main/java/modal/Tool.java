package modal;

import javax.swing.*;

/**
 * Created by ramyeid on 6/2/17.
 */
public interface Tool {
    void action();
    JPanel plot();
    void build(String ... arg);

}
