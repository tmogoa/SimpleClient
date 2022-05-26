// SimpleServer.java: A simple server program.
import java.net.*;
import java.io.*;
import java.util.Scanner;

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
            System.out.println("Waiting to connect...");
            sock = new Socket("localhost",1254);

            os = sock.getOutputStream();
            dos = new DataOutputStream (os);

            is = sock.getInputStream();
            dis = new DataInputStream(is);

            System.out.println("Connected...");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String args[]) throws IOException {
        SimpleClient sc = new SimpleClient();
        Reader reader = new Reader(sc);
        reader.start();
        sc.startWriter();

//        System.out.println(sc.read());
//        sc.write("Hi too!");
//        System.out.println(sc.read());
//        sc.write("The quick brown fox jumped over the lazy sleeping dog");
//        System.out.println(sc.read());
    }

    // Send a string!
    public void write(String data){
        try {
            dos.writeUTF(data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read() {
        try {
            String st = new String (dis.readUTF());

            return st;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void startWriter(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            write(line);
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

    public Socket getSock() {
        return sock;
    }

    public void setSock(Socket sock) {
        this.sock = sock;
    }

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) {
        this.os = os;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

}
