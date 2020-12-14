package kursach;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommunicationWithServer {
    public static String request(Code code, String strRequest) {
        Socket mySocket;
        try {
        mySocket = MySingletonSocket.getInstance();
        DataOutputStream oos = new DataOutputStream(mySocket.getOutputStream());
        DataInputStream ois = new DataInputStream(mySocket.getInputStream());
        String resultRequest = code + "&" + strRequest;
        oos.writeUTF(resultRequest);
        oos.flush();
        Thread.sleep(10);
        String in = ois.readUTF();
        return in;
        } catch (IOException e) {}
        catch (InterruptedException e) {}
        return null;
    }

    public static String createMessageForServer(String...words) {
        StringBuffer builder = new StringBuffer();
        for (String word : words) {
            builder.append(word.trim()).append(Const.DELIMITER);
        }
        return builder.toString().substring(0, builder.length() - 1);
    }
}
