import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Taker extends Thread {

    @Override
    public void run() {

        try {
            InetAddress address = InetAddress.getByName(null);
            Socket socket = new Socket(address, 8080);
            boolean error = false;

            try {

                System.out.println("Socket= " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);


                try {
                    int number = (int) (Math.random() * 10);
                    out.println(number);
                    String str = in.readLine();


                } catch (Exception e) {
                    error = true;
                    System.out.println(this.getName() + " I couldn't connect to the server");

                }
                if (!error) {
                    System.out.println(this.getName() + " I connected to the server");
                }


            } finally {
                System.out.println(this.getName() + " connection is closing...");
                socket.close();
            }

        } catch (Exception e) {
            System.err.println(this.getName() + " An error occurred");
        }
    }
}
