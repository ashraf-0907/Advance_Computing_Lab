import java.awt.*;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class server extends JPanel
{
    private static final int ARRAY_SIZE = 20;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 630;
    private static final int BAR_WIDTH = FRAME_WIDTH / ARRAY_SIZE;

    private int[] array;
    private boolean[] sorted;
    BufferedInputStream in;
    DataOutputStream out;
    server(){
        array=new int[20];
        sorted = new boolean[ARRAY_SIZE];
        Arrays.fill(sorted, false);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    }
    public void serverSetup() throws IOException
    {
        System.out.println("Waiting for the client...");
        ServerSocket ss=new ServerSocket(3000);
        Socket Server= ss.accept();
        System.out.println("Connection Establish...");
        in = new BufferedInputStream(Server.getInputStream());
        out = new DataOutputStream(Server.getOutputStream());
    }

    public void recieveUnsortedblock() throws IOException{
        int j;
        for(int i=0;i<20;i++){
            j=in.read();
            System.out.println("Recieved number ="+j);
            System.out.println("Sending acknowledgement: ");
            out.write(1);
            array[i]=j;
        }
    }

    public void sendSortedblock() throws IOException{
        for(int i=0;i<20;i++){
            int num= array[i];
            System.out.println("Sending the element ="+num);
            out.write(num);
            out.flush();
            System.out.println("Waiting for acknowledgement: ");

            try {
                Thread.sleep(300);
            } catch (Exception e) {
                // TODO: handle exception
            }

            int check=in.read();
            if(check==1){
                System.out.println("Recieved Acknowledgement");
            }
            else{
                System.out.println("Error in Recieving the acknowledgement");
            }
        }
    }

    public void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public void merge(int[] arr, int l, int m, int r) {
        int[] left = Arrays.copyOfRange(arr, l, m + 1);
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
            repaint();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
            repaint();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
            repaint();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mark the sorted elements in green
        for (int x = l; x <= r; x++) {
            sorted[x] = true;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Arial", Font.PLAIN, 18); // create a new font with size 16
        g.setFont(font); // set the font of the graphics object
        for (int i = 0; i < ARRAY_SIZE; i++) {
            // Display input array in red
            g.setColor(Color.RED);
            g.fillRect(i * BAR_WIDTH, FRAME_HEIGHT - array[i] * 20 - 20, BAR_WIDTH, 20);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(array[i]), i * BAR_WIDTH + BAR_WIDTH / 2, FRAME_HEIGHT - array[i] * 20);

            if (sorted[i]) {
                // Display sorted elements in green
                g.setColor(Color.GREEN);
            } else if (i == 0 || sorted[i - 1]) {
                // Display merging elements in blue
                g.setColor(Color.BLUE);
            } else {
                // Display unsorted elements in black
                g.setColor(Color.BLACK);
            }
            g.fillRect(i * BAR_WIDTH, FRAME_HEIGHT - array[i] * 20, BAR_WIDTH, array[i] * 20);
        }

        // Display sorted array in green
        g.setColor(Color.BLACK);
        g.drawString("Sorted Array: " + Arrays.toString(array), 10, 30);
    }

    public static void main(String[] args) {
        server visualizer =new server();
        try {
            visualizer.serverSetup();
            visualizer.recieveUnsortedblock();
        } catch (Exception e) {
            // TODO: handle exception
        }
        JFrame frame = new JFrame("Server Side");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(visualizer);
        frame.pack();
        frame.setVisible(true);
        visualizer.mergeSort(visualizer.array, 0, ARRAY_SIZE - 1);

        try {
            visualizer.sendSortedblock();
        }
        catch (Exception e){}
    }
}