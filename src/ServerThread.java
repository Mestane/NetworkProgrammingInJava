import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {


    public Socket socket;
    public ArrayList<Object> pool;

    public ServerThread(ArrayList<Object> pool) {
        this.pool = pool;

    }

    public synchronized void specifySocket(Socket socket) {
        this.socket = socket;
        notify();

    }


    @Override
    public synchronized void run() {

        while (true) {

            try {

                if (socket == null) {

                    try {

                        wait();

                    } catch (InterruptedException e) {
                        continue;
                    }

                }

                System.out.println("connecting thread= " + this.getName() + " port num= " + socket);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                String str = in.readLine();
                while (str != null) {
                    int number = Integer.parseInt(str);

                    String declaration = Declaration.getString(number);
                    out.println(declaration);

                    str = in.readLine();
                }
            } catch (IOException e) {
                System.err.println("Error " + this.getName() + "---> " + e);
            } finally {

                try {

                    assert socket != null;
                    socket.close();
                    socket = null;
                    pool.add(this);
                } catch (IOException e) {
                    System.err.println("Socket is not closed " + this.getName() + "---> " + e);
                }
            }


        }

    }

}
