package swissknife.panels.showvalues;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by ramyeid on 6/12/17.
 */
public class ShowValues extends JPanel {

    JInternalFrame masterFrame;
    JFrame mainFrame;

    public ShowValues(ArrayList<ArrayList<String>> data, JInternalFrame masterFrame, JFrame mainFrame) {

        Vector<String> columnNames = new Vector<String>();
        for (int i = 0; i < data.size(); ++i) {
            columnNames.add(data.get(i).get(0));
        }

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);

        for (int j = 1; j < data.get(0).size(); j++) {
            Vector<String> result = new Vector<String>();
            for (int i = 0; i < data.size(); i++) {
                result.add(data.get(i).get(j));
            }
            if (j == 0) {
                tableModel.setColumnIdentifiers(result);
            } else {
                tableModel.addRow(result);
            }
        }

        this.masterFrame = masterFrame;
        this.mainFrame = mainFrame;
        JScrollPane scrollPane = new JScrollPane(table);

//        this.setLayout(null);
        scrollPane.setBounds(10,10,scrollPane.getWidth(),scrollPane.getHeight());
        masterFrame.setSize(scrollPane.getWidth()+10,scrollPane.getHeight()+10);
        this.add(scrollPane);

//        this.masterFrame.setSize(10,10);
//        this.masterFrame.pack();

//        scrollPane.setSize(table.getSize());
//        this.setSize(scrollPane.getWidth(),scrollPane.getHeight());


//        this.masterFrame.setMaximumSize(new Dimension(10 + scrollPane.getWidth(), 10 + scrollPane.getHeight()));
//        this.masterFrame.setMinimumSize(new Dimension(10 + scrollPane.getWidth(), 10 + scrollPane.getHeight()));

    }
}