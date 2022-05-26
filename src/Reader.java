import java.io.IOException;

public class Reader extends Thread{
    private SimpleClient sc;

    public Reader(SimpleClient sc) {
        this.sc = sc;
    }


    @Override
    public void run() {
        while (true) {
            try {
                // reduce number of checks
                sleep(100);
                if (sc.getDis().available() > 0){
                    System.out.println(sc.read());
                };
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
