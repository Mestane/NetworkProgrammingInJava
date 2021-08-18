public class Client {

    public static void main(String[] args)  {

        int attackLimit = 0;
        if (args.length == 1) {

            try {
                attackLimit = Integer.parseInt(args[0]);


            } catch (Exception e) {
                System.err.println("Please enter number");
                System.exit(-1); // exit the program

            }
        } else {
            System.err.println("how many takers will be sent to the server");
            System.exit(-1);

        }

            for (int i = 0; i < attackLimit; i++) {
            Taker taker = new Taker();
            taker.start();
        }
    }
}
