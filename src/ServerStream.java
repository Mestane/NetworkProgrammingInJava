import java.io.*;
import java.net.Socket;

public class ServerStream extends Thread {

    private BufferedReader in;
    private PrintWriter out;

    public Socket socket;

    public ServerStream() {

    }

    public static String findTheAnswer(int number) {
        return Declaration.getString(number);

    }

    @Override
    public void run() {
        try {

            System.out.println("Connecting thread: " + this.getName() + " port num= " + socket);
            extracted();

        } catch (IOException e) {
            System.err.println("Error= " + this.getName() + " ---> " + e);

        } finally {
            System.out.println("Connecting is closing...");

            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed= " + this.getName() + " ---> " + e);
            }
        }
    }

    public void extracted() throws IOException {

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

        String str = in.readLine();

        while (str != null) {
            System.out.println(str);
            int number = Integer.parseInt(str);
            String declaration = findTheAnswer(number);
            out.println(declaration);
            str = in.readLine();

        }
    }

    public BufferedReader getIn() {
        return in;
    }


    public PrintWriter getOut() {
        return out;
    }


}
