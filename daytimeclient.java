import java.net.*;
import java.io.*;

public class daytimeclient {
    public static void main(String args[]) throws java.io.IOException {
        String host = args[0];
        String protocol = args[1];
        int port = 13;

        System.out.println(host);
        System.out.println(protocol);
        System.out.println("Finding protocol");
        if (protocol.equals("UDP")) {
            System.out.println("In UDP");
            System.out.println("Finding protocol");
            DatagramSocket udpsock = new DatagramSocket();
            InetAddress address = InetAddress.getByName(host);
            udpsock.setSoTimeout(1000);
            byte[] buf = new byte[512];
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);

            try {
                udpsock.send(packet);
                packet.setLength((0));


                packet.setLength(buf.length);
                udpsock.receive(packet);

                System.out.println(new String(buf, 0, packet.getLength(), "US-ASCII"));

                
            } catch (SocketTimeoutException e) {
                System.out.println("Whoops no connection made");
            }

            udpsock.close();


        }

        else if (protocol.equals("TCP")) {
            try
            {
                System.out.println("In TCP");
                
                Socket daytime = new Socket (host, 
                port);

                System.out.println ("Connection established");

                
                daytime.setSoTimeout ( 2000 );

                
                BufferedReader reader = new BufferedReader (
                    new InputStreamReader
                    (daytime.getInputStream()
                ));

                System.out.println (
                reader.readLine());

                // Close the connection
                daytime.close();
            }
            catch (IOException ioe)
            {
                System.err.println ("Error " + ioe);
            }
        }
    }
}

