import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;

public class Server
{
    private boolean frames[];
    private int f;

    private int choice;


    void server_end() throws IOException
    {
        InetAddress addr=InetAddress.getByName("Localhost");

        // creating port for server

        ServerSocket ss=new ServerSocket(3000);
        System.out.println("Server is listening at port 3000");

        //creating socket between the client and server

        Socket client = new Socket();

        client = ss.accept();

        //creating input and output stream

        BufferedInputStream in=new BufferedInputStream(client.getInputStream());
        DataOutputStream out=new DataOutputStream(client.getOutputStream());

        f=in.read();

        System.out.println("Receive request for sending no of packets "+ f);

        frames=new boolean[f];

        choice=in.read();

        if(choice==0)
        {
            for(int i=0;i<f;i++)
            {
                System.out.println("Sending packet number " + i);
                out.write(i);
                out.flush();
                System.out.println("Waiting for acknowledgment ");
                try{
                    Thread.sleep(3000);
                }
                catch (Exception e){}

                int check=in.read();
                System.out.println("Received acknowledgement for packet "+ i + " as " + check);
            }
            out.flush();
        }

        else{
            Random rand= new Random();
            int depricated1= rand.nextInt(f);
            int depricated2= rand.nextInt(f);

            depricated2=depricated1==depricated2?depricated2-1:depricated2;

            System.out.println("Depricated packet are: "+ depricated2 +" and "+ depricated1);

            for(int i = 0; i < f; i++)
            {
                if(frames[i] == false)
                {
                    if(i == depricated1 || i == depricated2) {
                        System.out.println("Skipping deprecated packet number: " + i);
                        continue;
                    }
                    else {
                        System.out.println("Sending packet number: " + i);
                        out.write(i);
                        out.flush();
                        System.out.println("Waiting for acknowledgement ");
                        try {
                            Thread.sleep(3000);
                        } catch (Exception e) {
                        }

                        int check = in.read();

                        if (check != -1) {
                            System.out.println("Received the acknowledgement for the packet " + i + " as " + check);
                            frames[i] = true;
                        }
                        else {
                            System.out.println("Packet get rejected");
                        }
                    }
                    out.flush();
                }
            }




            for(int i=0;i<f;i++)
            {
                if(frames[i]==false){
                    System.out.println("Resending Packet: "+i);
                    out.write(i);
                    out.flush();
                    System.out.println("Waiting for acknowledgement ");
                    try{
                        Thread.sleep(3000);
                    }
                    catch(Exception e){}

                    int check=in.read();
                    System.out.println("Received acknowledgement for the packet "+i+" as "+check);
                    frames[i]=true;
                }
                out.flush();
            }
        }
        in.close();
        out.close();
    }
    public static void main(String [] args) throws IOException
    {
        System.out.println(".............Server...............");
        System.out.println("Waiting for connection .. .. ");

        Server s=new Server();

        s.server_end();
    }
}
