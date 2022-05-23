// SimpleServer.java: A simple server program.
import java.net.*;
import java.io.*;

public class SimpleClient {
    // creating class variables
    private Socket sock;
    private OutputStream os;
    private DataOutputStream dos;
    private InputStream is;
    private DataInputStream dis;

    public SimpleClient() {
        try {
            // Register service on port 1254
            // Open your connection to a server, at port 1254
            sock = new Socket("localhost",1254);
            System.out.println("Waiting to connect...");

            os = sock.getOutputStream();
            dos = new DataOutputStream (os);

            is = sock.getInputStream();
            dis = new DataInputStream(is);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Wait and accept a connection

    }

    public static void main(String args[]) throws IOException {
        SimpleClient sc = new SimpleClient();
        System.out.println(sc.read());
        sc.write("Hi too!");
        System.out.println(sc.read());
        sc.write("The quick brown fox jumped over the lazy sleeping dog");
        System.out.println(sc.read());
    }

    // Send a string!
    public void write(String data){
        try {
            dos.writeUTF(data);
            // Close the connection, but not the server socket

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read() {
        try {
            String st = new String (dis.readUTF());
            // Close the connection, but not the server socket

            return st;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        try {
            dis.close();
            dos.close();
            sock.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
