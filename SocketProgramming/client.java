import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class client extends JPanel
{
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;

    BufferedInputStream in ;
    DataOutputStream out ;

    private int arr[];
    private int sarr[];
    public static final int n=20;

    client(){
        arr=new int[n];
        sarr=new int[n];
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    }
    public void clientSetup() throws IOException
    {
        System.out.println("Client Started");
        Socket Client= new Socket("localhost",3000);
        in = new BufferedInputStream(Client.getInputStream());
        out = new DataOutputStream(Client.getOutputStream());
    }

    public void sendUnsortedblock() throws IOException{
        for(int i=0;i<n;i++){
            int num= (int) (Math.random()*30);
            sarr[i]=num;
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

    public void recieveSortedarray() throws IOException
    {
        int j;
        for(int i=0;i<20;i++){
            j=in.read();
            System.out.println("Recieved number ="+j);
            System.out.println("Sending acknowledgement: ");
            out.write(1);
            arr[i]=j;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.PLAIN, 24); // create a new font with size 16
        g.setFont(font); // set the font of the graphics object
        g.drawString("Send Array: " + Arrays.toString(sarr), 10, 30);
        g.drawString("Received Array: " + Arrays.toString(arr), 10, 60);

    }
    public static void main (String [] args){
        client c=new client();

        try {
            c.clientSetup();
            c.sendUnsortedblock();
            try{
                Thread.sleep(10000);
            }
            catch (Exception e){}
            System.out.println("******************** Received Sorted ARRAY *****************");
            c.recieveSortedarray();
        } catch (Exception e) {
            // TODO: handle exception
        }
        JFrame frame = new JFrame("Client Side");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(c);
        frame.pack();
        frame.setVisible(true);
    }
}