import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OPT extends JPanel {
    DefaultTableModel model;
    JTable optTable;
    OPT(){
        model = new DefaultTableModel();
        optTable = new JTable(model);
        optTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(optTable);
        add(scrollPane);
    }

    public int findOptimal(int []refString, int [][] array, int i) {
        for (int j = 0 ; j < array.length ; j++){
            if (array[j][i] == Integer.MAX_VALUE) {
                return j;
            }
        }

        int [] opt = new int[array.length];
        for (int k = 0 ; k < opt.length ; k++){
            opt[k] = refString.length;
        }
        for (int k = 0 ; k < array.length ; k++) {
            for (int j = i + 1; j < refString.length; j++) {
                if (array[k][i] == refString[j]){
                    opt[k] = j;
                    break;
                }
            }
        }

        int minIndex = 0;

        for (int j = 0 ; j < opt.length; j++){
            if (opt[minIndex]<opt[j]){
                minIndex = j;
            }
        }
        System.out.println(minIndex);
        return minIndex;
    }

    void printer(int [][] frame){
        System.out.println("OPT");

        for (int i = 0 ; i < frame.length; i++) {
            for (int j = 0; j < frame[0].length; j++) {
                if (frame[i][j] == Integer.MAX_VALUE){
                    System.out.print(-1+" ");
                    continue;
                }
                System.out.print(frame[i][j]+"  ");
            }
            System.out.println("");
        }
    }

    public int[][] getOptArray(int frameCount, int []refString){
        int frame[][] = new int[frameCount][refString.length];
        FIFO.initialise(frame);
        boolean flag[] = new boolean[frameCount];

        for (int i = 0 ; i < refString.length ; i ++){
            if (!FIFO.checkPresence(frame, i, refString[i])){
                int replace = findOptimal(refString, frame, i);
                FIFO.initialiseRow(frame[replace], refString[i], i);
            }
        }
        printer(frame);
        return frame;
    }
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

}

