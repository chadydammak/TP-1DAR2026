package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException {
        System.out.println("Je suis un client pas encore connecté");

        Socket socket = new Socket("localhost",1234);
        System.out.println("je suis un client connecte");
        socket.close();

    }
}
