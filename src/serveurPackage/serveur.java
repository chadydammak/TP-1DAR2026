package serveurPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class serveur {
    public static void main(String[] args) throws IOException {
        ServerSocket serveurSocket = new ServerSocket(1234);
        System.out.println("je suis un serveur en attente la connexion d'un client");

        Socket Socket =serveurSocket.accept();
        System.out.println("un client est connecté");
        Socket.close();
        serveurSocket.close();

        InputStream is = Socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        int nb = dis.readInt();
        System.out.println("le serveur a recu le nombre :"+nb);
        int x =nb*5;
        System.out.println("calcul du serveur :"+x+"+5="+5);
        OutputStream os = Socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(x);
        System.out.println("Le resultat a été envoyé au client"+x);
        dis.close();
        dos.close();
        Socket.close();


    }


}
