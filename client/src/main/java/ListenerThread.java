import java.io.BufferedReader;

public class ListenerThread extends Thread {
    private BufferedReader br;

    public ListenerThread(BufferedReader br) {
        this.br = br;
    }

    public void run() {
        while(true) {
            try {
                String currentLine;
                if(br.ready() && (currentLine = br.readLine() ) != null) {
                    System.out.println(currentLine);
                }

            } catch (Exception e) {}
        }
    }
}
