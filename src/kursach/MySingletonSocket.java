package kursach;

import java.io.IOException;
import java.net.Socket;

public class MySingletonSocket {
    private static Socket mySocket;
    private MySingletonSocket() {

    }

    public static Socket getInstance() throws IOException {
        if (mySocket == null) mySocket = new Socket(Const.LOCALHOST, Const.PORT);
        return mySocket;
    }
}
