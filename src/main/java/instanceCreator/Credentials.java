package instanceCreator;

import sample.indexController;

import java.io.IOException;

public class Credentials {
    private static indexController getKeyPair;

    static {
        try {
            getKeyPair = new indexController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String AWS_ACCESS_KEY_ID;
    public static String AWS_SECRET_ACCESS_KEY;
}
