
package serveurPackage;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class serveur {

    public static void main(String[] args) throws IOException {

        int port = 1234;

        // Récupérer l'adresse IP locale de la machine
        InetAddress adresseLocale = InetAddress.getLocalHost();

        System.out.println("Adresse locale : " + adresseLocale.getHostAddress());

        // Créer le serveur et choisir son adresse d'écoute
        ServerSocket serveurSocket = new ServerSocket();

        serveurSocket.bind(
                new InetSocketAddress(adresseLocale, port)
        );

        System.out.println(
                "Serveur en attente d'un client sur le port " + port
        );

        // Attendre la connexion d'un client
        try (
                Socket socket = serveurSocket.accept();
                DataInputStream dis =
                        new DataInputStream(socket.getInputStream());
                DataOutputStream dos =
                        new DataOutputStream(socket.getOutputStream())
        ) {

            System.out.println("Un client est connecté.");

            // Recevoir le choix et la valeur
            int choix = dis.readInt();
            int x = dis.readInt();

            System.out.println("Choix reçu : " + choix);
            System.out.println("Nombre reçu : " + x);

            double resultat;

            // Effectuer le calcul demandé
            switch (choix) {

                case 1:
                    resultat = x + 5;
                    System.out.println(
                            "Calcul : " + x + " + 5 = " + resultat
                    );
                    break;

                case 2:
                    resultat = x - 5;
                    System.out.println(
                            "Calcul : " + x + " - 5 = " + resultat
                    );
                    break;

                case 3:
                    resultat = x * 5;
                    System.out.println(
                            "Calcul : " + x + " * 5 = " + resultat
                    );
                    break;

                case 4:
                    resultat = (double) x / 5;
                    System.out.println(
                            "Calcul : " + x + " / 5 = " + resultat
                    );
                    break;

                default:
                    System.out.println("Opération invalide.");
                    return;
            }

            // Envoyer le résultat au client
            dos.writeDouble(resultat);
            dos.flush();

            System.out.println(
                    "Résultat envoyé au client : " + resultat
            );

        } finally {
            serveurSocket.close();
            System.out.println("Serveur arrêté.");
        }
    }
}

