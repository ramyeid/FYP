package swissknife.panels.showvalues;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


public class ShowValues extends JPanel {

    JInternalFrame masterFrame;
    JFrame mainFrame;

    public ShowValues(ArrayList<ArrayList<String>> data, JInternalFrame masterFrame, JFrame mainFrame) {
        Vector<String> columnNames = new Vector<>();
        for (int i = 0; i < data.size(); ++i) {
            columnNames.add(data.get(i).get(0));
        }

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


//        JTable table = new JTable(tableModel);

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
        JTable table = new JTable(tableModel){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.masterFrame = masterFrame;
        this.mainFrame = mainFrame;
        JScrollPane scrollPane = new JScrollPane(table);



        this.masterFrame.setSize(table.getWidth(), table.getHeight());

        this.add(scrollPane);
        this.setLayout(new GridLayout(1,1));


//        this.masterFrame.setSize(10,10);
//        this.masterFrame.pack();

//        scrollPane.setSize(table.getSize());
//        this.setSize(scrollPane.getWidth(),scrollPane.getHeight());


//        this.masterFrame.setMaximumSize(new Dimension(10 + scrollPane.getWidth(), 10 + scrollPane.getHeight()));
//        this.masterFrame.setMinimumSize(new Dimension(10 + scrollPane.getWidth(), 10 + scrollPane.getHeight()));

    }
}