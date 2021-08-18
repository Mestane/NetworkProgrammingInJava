public class Declaration {

    public static String getString(int number) {
        String declaration;

        switch (number) {
            case 0:
                declaration = "Zero";
                break;
            case 1:
                declaration = "One";
                break;
            case 2:
                declaration = "Two";
                break;
            case 3:
                declaration = "Three";
                break;
            case 4:
                declaration = "Four";
                break;
            case 5:
                declaration = "Five";
                break;
            case 6:
                declaration = "Six";
                break;
            case 7:
                declaration = "Seven";
                break;
            case 8:
                declaration = "Eight";
                break;
            case 9:
                declaration = "Nine";
                break;
            default:
                declaration = "Unknown Declaration";
                break;
        }

        return declaration;
    }


}