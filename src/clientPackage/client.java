
package clientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class client {

    public static void main(String[] args) throws IOException {

        // Adresse IP du serveur
        // localhost fonctionne uniquement pour un test sur la même machine
        String adresseServeur = "localhost";
        int port = 1234;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Je suis un client pas encore connecté");

        try (Socket socket = new Socket(adresseServeur, port)) {

            System.out.println("Je suis un client connecté");

            // Afficher le menu
            System.out.println("\n===== CALCULATRICE =====");
            System.out.println("1. Addition       (x + 5)");
            System.out.println("2. Soustraction   (x - 5)");
            System.out.println("3. Multiplication (x * 5)");
            System.out.println("4. Division       (x / 5)");
            System.out.print("Choisir une opération (1-4) : ");

            int choix = scanner.nextInt();

            if (choix < 1 || choix > 4) {
                System.out.println("Choix invalide !");
                return;
            }

            System.out.print("Entrer un entier x : ");
            int x = scanner.nextInt();

            // Préparer les flux de communication
            DataOutputStream dos =
                    new DataOutputStream(socket.getOutputStream());

            DataInputStream dis =
                    new DataInputStream(socket.getInputStream());

            // Envoyer le choix et la valeur au serveur
            dos.writeInt(choix);
            dos.writeInt(x);
            dos.flush();

            System.out.println("Opération et nombre envoyés au serveur.");

            // Recevoir le résultat
            double resultat = dis.readDouble();

            System.out.println("Résultat reçu du serveur : " + resultat);

        } catch (IOException e) {
            System.out.println(
                    "Erreur de connexion ou de communication : " + e.getMessage()
            );
        }

        scanner.close();
    }
}
