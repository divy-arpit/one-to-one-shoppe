package chat;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("2409:4064:4e92:970e:5c48:ce92:3f67:affc", 9806);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        System.out.println(dataInputStream.readUTF());
        socket.close();
    }
}
