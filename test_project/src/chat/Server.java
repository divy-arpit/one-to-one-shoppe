package chat;

import server.core.Strings;
import server.utility.ProductFunctions;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        ServerSocket serverSocket = new ServerSocket(9806);
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream.writeUTF("Hello from " + Strings.storeName);
        dataOutputStream.writeUTF("Please wait while we connect you to an employee");
        socket.close();
        serverSocket.close();
    }
}
