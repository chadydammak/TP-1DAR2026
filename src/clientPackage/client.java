package clientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        System.out.println("Je suis un client pas encore connecté");

        Socket socket = new Socket("localhost",1234);
        System.out.println("je suis un client connecte");
        socket.close();
        Scanner scanner = new Scanner(System.in);
        System.out.println("entrer un entier x :");
        int x = scanner.nextInt();
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(x);
        System.out.println("Nombre envoyé au serveur :"+ x);
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        int xx =dis.readInt();
        System.out.println("Result reçu du serveur "+ xx);
        dis.close();
        dos.close();
        socket.close();
        scanner.close();


    }
}
