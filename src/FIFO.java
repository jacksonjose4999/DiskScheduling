import javax.swing.*;
import java.awt.*;

public class FIFO extends JPanel {
    FIFO(int frameCount, int[] refString){
        setPreferredSize(new Dimension(500,300));
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

        System.out.println("FIFO");

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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }
}

