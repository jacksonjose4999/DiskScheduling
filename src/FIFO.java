import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FIFO extends JPanel {
    DefaultTableModel model;
    JTable fifoTable;
    static JLabel PF = new JLabel("Page Fault:");
    FIFO(){
        setLayout(new BorderLayout());
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());
        JLabel fifoLabel = new JLabel("FIFO");
        labelPanel.add(fifoLabel,BorderLayout.WEST);
        labelPanel.add(PF,BorderLayout.EAST);
        model = new DefaultTableModel();
        fifoTable = new JTable(model);
        fifoTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(fifoTable);
        add(labelPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);

    }

    public static boolean checkPresence(int [][]array,int coloumn, int num){
        for (int i = 0 ; i < array.length ; i++) {
            if (array[i][coloumn] == num)
                return true;
        }
            return false;
    }

    public static void initialise(int [][] array){
        for (int i = 0 ; i < array.length; i++){
            for(int j = 0 ; j < array[0].length; j++)
                array[i][j] = Integer.MAX_VALUE;
        }
    }

    public static void initialiseRow(int [] array, int num, int i){
        for(int j = i ; j < array.length; j++)
            array[j] = num;
    }

    public static String[][] getFifoArray(int frameCount, int []refString){
        int frame[][] = new int[frameCount][refString.length];
        initialise(frame);
        int count = 0;
        boolean flag[] = new boolean[frameCount];

        for (int i = 0 ; i < refString.length ; i++){
            if (!checkPresence(frame, i, refString[i])){
                frame[count%frameCount][i] = refString[i];
                flag[count%frameCount] = true;
                initialiseRow(frame[count%frameCount],refString[i],i);
                count++;
            }
        }

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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }
}

