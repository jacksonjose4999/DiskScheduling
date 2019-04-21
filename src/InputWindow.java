import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindow extends JFrame {
    InputWindow() {
        this.setLayout(new GridBagLayout());
        this.setVisible(true);

        JLabel NoOfFrames = new JLabel("Number of Frames");
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridy = 0;
        c.gridx = 0;
        c.fill = 1;
        add(NoOfFrames, c);

        Integer[] frameOptions = {3, 4, 5, 6, 7};
        JComboBox<Integer> framesCount = new JComboBox<>(frameOptions);
        c.gridx++;
        add(framesCount, c);

        JLabel referenceStringsText = new JLabel("Reference String");
        c.gridx--;
        c.gridy++;
        add(referenceStringsText, c);

        JTextField referenceString = new JTextField();
        referenceString.setPreferredSize(new Dimension(200, referenceString.getHeight() + 25));
        c.gridx++;
        add(referenceString, c);

        JButton compute = new JButton("Compute");
        c.fill = 0;
        c.gridy++;
        c.gridwidth = 2;
        c.gridx--;
        add(compute, c);

        c.fill = 1;
        c.gridwidth = 1;
        c.gridy++;
        FIFO fifo = new FIFO();
        add(fifo, c);

        c.gridy++;
        OPT opt = new OPT();
        add(opt, c);


        c.gridy++;
        LRU lru = new LRU();
        add(lru, c);

        compute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] refString = getReferenceStringValues(referenceString.getText().toString());
                setData(fifo.model,FIFO.getFifoArray((int)framesCount.getSelectedItem()),);
            }
        });

    }

    int[] getReferenceStringValues(String refString) {
        String[] splittedString = refString.split(",");
        int[] ref = new int[splittedString.length];

        for (int i = 0; i < splittedString.length; i++) {
            ref[i] = Integer.parseInt(splittedString[i]);
            System.out.println(ref[i]);
        }
        return ref;
    }

        static void setData(DefaultTableModel model, String[][] rows, String[] columns) {
            model.setRowCount(0);
            model.setColumnCount(0);
            if (columns != null)
                for (int i = 0; i < columns.length; i++)
                    model.addColumn(columns[i]);
            if (rows != null)
                for (int i = 0; i < rows.length; i++)
                    model.addRow(rows[i]);
        }

}

