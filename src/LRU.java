import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LRU extends JPanel {
    DefaultTableModel model;
    JTable lruTable;
    JLabel lru = new JLabel("LRU");
    static JLabel PF = new JLabel("Page Fault:");
    LRU(){
        JPanel labelPanel = new JPanel();
        setLayout(new BorderLayout());
        labelPanel.setLayout(new BorderLayout());
        labelPanel.add(lru,BorderLayout.WEST);
        labelPanel.add(PF,BorderLayout.EAST);

        model = new DefaultTableModel();
        lruTable = new JTable(model);
        lruTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(lruTable);
        add(labelPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
    }

    int findLeastRecent(int [][]array, int col, int [] refString){
        int []lru = new int[array.length];

        for (int i = 0 ; i < lru.length; i++){
            lru[i] = Integer.MIN_VALUE;
        }

        for (int j = 0 ; j < array.length ; j++) {
            if (array[j][col] == Integer.MAX_VALUE) {
                return j;
            }
        }

        for (int k = 0 ; k < array.length ; k++) {
            for (int i = 0; i < col; i++) {
                if (refString[i] == array[k][col]){
                    lru[k] = i ;
                }
            }
        }

        int minIndex = 0;

        for (int i = 0 ; i < lru.length ; i++){
            if (lru[minIndex] > lru[i]){
                minIndex = i;
            }
        }
        for (int i = 0 ; i < lru.length; i++){
            System.out.print(lru[i]+" ");
        }
        System.out.println("min index "+minIndex);
        return minIndex;
    }

    void printer(int [][] frame){
        System.out.println("LRU");

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

    public String[][] getLruArray(int frameCount, int []refString){
        int frame[][] = new int[frameCount][refString.length];
        FIFO.initialise(frame);
        int count = 0;
        for( int i = 0 ; i < refString.length ; i++){
            if (!FIFO.checkPresence(frame, i, refString[i])){
                int replace = findLeastRecent(frame, i , refString);
                FIFO.initialiseRow(frame[replace], refString[i], i);
                count++;
            }
        }

        printer(frame);
        String [][]f = new String[frameCount][refString.length];
        for (int i = 0 ; i < frameCount; i++){
            for (int j = 0 ; j < refString.length ; j++){
                if (frame[i][j] == Integer.MAX_VALUE){
                    f[i][j] = "Null";
                    continue;
                }
                f[i][j] = Integer.toString(frame[i][j]);
            }
        }

        PF.setText("Page Fault: "+count);
        return f;
    }
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

}
