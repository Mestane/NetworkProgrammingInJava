import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static final int PORT = 8080;

    public final ArrayList<Object> pool = new ArrayList<>();

    public void start(int poolLimit) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server Started= " + serverSocket);

        for (int i = 0; i < poolLimit; i++) {
            ServerThread serverThread = new ServerThread(pool);
            serverThread.start();
            pool.add(serverThread);
            System.out.println(serverThread + " Added to the pool ");
        }


        try {
            while (true) {

                Socket userSocket = serverSocket.accept();
                synchronized (pool) {
                    if (pool.isEmpty()) {
                        System.out.println("New request arrived but no idle threads");
                        userSocket.close();

                    } else {
                        System.out.println("This thread will deal with the request " + this.pool);
                        ServerThread serverThread = (ServerThread) pool.get(0);
                        pool.remove(0);

                        userSocket.setSoTimeout(5000);
                        serverThread.specifySocket(userSocket);

                    }

                }
            }
        } catch (Exception e) {
            System.err.println("There is an error in the main build = " + e);

        } finally {
            serverSocket.close();
        }

    }


    public static void main(String[] args) throws IOException {

        int poolLimit = 0;
        if (args.length == 1) {

            try {
                poolLimit = Integer.parseInt(args[0]);

            } catch (Exception e) {
                System.err.println("Please specify number: ");
                System.exit(-1);

            }
        } else {

            System.err.println("Specify how many threads the pool should have inside");
            System.exit(-1);

        }

        Server server = new Server();
        server.start(poolLimit);


    }

}
