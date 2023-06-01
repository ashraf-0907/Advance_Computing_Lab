import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Random;

public class Client {
    public void client_end() throws IOException {
        InetAddress address = InetAddress.getByName("Localhost");
        System.out.println(address);
        Socket connection = new Socket(address, 3000);
        BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        int f = 10;
        out.write(f);
        out.flush();
        Random rand = new Random();
        int choice = rand.nextInt(2);
        out.write(choice);
        int check = 0;
        int j;
        HashSet<Integer> receivedPackets = new HashSet<Integer>();
        if (choice == 0) {
            for (int i = 0; i < f; i++) {
                j = in.read();
                System.out.println("Received packet number: " + j);
                System.out.println("Sending acknowledgement for packet number: " + i);
                out.write(i);
            }
            out.flush();
        } else {
            for (int i = 0; i < f; i++) {
                j = in.read();
                if (receivedPackets.contains(j)) {
                    System.out.println("Discarded packet number: " + j);
                    System.out.println("Sending negative acknowledgement ");
                    out.write(-1);
                } else {
                    System.out.println("Received packet number: " + j);
                    System.out.println("Sending acknowledgement for the packet number: " + j);
                    out.write(j);
                    receivedPackets.add(j);
                }
            }
            out.flush();
        }
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("**************Client**************");
        System.out.println("Connect to server .. ..");
        Client c = new Client();
        c.client_end();
    }
}


