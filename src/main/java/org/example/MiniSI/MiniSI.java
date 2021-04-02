package org.example.MiniSI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MiniSI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Attention, ce code est un exemple pour aller vite, mais il convient de noter qu'il n'est pas sécurisé de laisser un mot de passe en clair dans un fichier Java. Nous verrons ensemble comment gérer ça plus proprement dans la suite de la formation.
        String url = "jdbc:postgresql://localhost:5432/MiniSI";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            System.out.println("Menu S.I Restauration");
            System.out.println("1. Edition des factures");
            System.out.println("2. Mise à jour de la base de données");
            System.out.println("3. Statistiques");
            System.out.println("Votre choix ? (1, 2 ou 3)");
            int choix = sc.nextInt();
            sc.nextLine();

            if (choix == 1) {
                System.out.println("Bienvenue dans le module de saisie des factures.");
                System.out.println("Voici les tables disponibles :");
                //print de la liste des tables
                Table.getTables(connection);

                //connexion et création de la facture
                Facture.getFactures(connection);
                System.out.println("Veuillez indiquer le numéro de table : ");
                int idx_site = sc.nextInt();
                sc.nextLine();
                // print de la liste des serveurs
                Serveur.getServeurs(connection);
                //retour sur la classe Facture
                System.out.println("Merci d'indiquer le numéro du serveur : ");
                int idx_serveur = sc.nextInt();
                sc.nextLine();
                Facture nouvelleFacture = new Facture(idx_site, idx_serveur);
                int idx_facture= nouvelleFacture.saveFacture(connection);
                Facture.getFactures(connection);

                //boucle de saisie des articles, tant qu'un plat est rentré
                //Bloc plats
                Plat.getPlats(connection);
                //Bloc jointures
                Join_Plat_Facture.getJoins(connection);
                //int idx_facture= id;
                int idx_plat = 0;

                do {
                    System.out.print("Quel plat a été commandé : ");
                    idx_plat = sc.nextInt();
                    sc.nextLine();

                    if (idx_plat>=0) {
                        System.out.print("En quelle quantité : ");
                        int quantite = sc.nextInt();
                        sc.nextLine();
                        Join_Plat_Facture nouvelleJointure = new Join_Plat_Facture(idx_plat, idx_facture, quantite);
                        nouvelleJointure.saveJoin(connection);
                    }
                }
                while (idx_plat>=0);
                System.out.println("Si j'ai le temps j'insérerai l'édition de la facture");
            }

            //Bloc factures
//            Facture.getFactures(connection);
//            System.out.println("Donne moi le numéro de table : ");
//            int idx_site = sc.nextInt();
//            System.out.println("Donne moi le numéro du serveur : ");
//            int idx_serveur = sc.nextInt();
//            Facture nouvelleFacture = new Facture(idx_site, idx_serveur);
//            nouvelleFacture.saveFacture(connection);
//            Facture.getFactures(connection);

            //Bloc tables
//            Table.getTables(connection);
//            System.out.println("Donne moi le nom de la nouvelle table : ");
//            String nom = sc.nextLine();
//            System.out.println("Donne moi le nombre de convives max : ");
//            int nb_places = sc.nextInt();
//            Table nouvelleTable = new Table(nom, nb_places);
//            nouvelleTable.saveTable(connection);
//            Table.getTables(connection);

            //Bloc jointures
//            Join_Plat_Facture.getJoins(connection);
//            System.out.println("Donne moi le numéro de la facture : ");
//            int idx_facture = sc.nextInt();
//            System.out.println("Donne moi le numéro du plat : ");
//            int idx_plat = sc.nextInt();
//            System.out.println("Donne moi la quantité : ");
//            int quantite = sc.nextInt();
//            Join_Plat_Facture nouvelleJointure = new Join_Plat_Facture(idx_plat,idx_facture,quantite);
//            nouvelleJointure.saveJoin(connection);
//            Join_Plat_Facture.getJoins(connection);

            //Bloc serveurs
//            Serveur.getServeurs(connection);
//            System.out.println("Donne moi le nom du nouveau serveur : ");
//            String nom = sc.nextLine();
//            Serveur nouveauServeur = new Serveur(nom);
//            nouveauServeur.saveServeur(connection);
//            Serveur.getServeurs(connection);

            //Bloc plats
//            Plat.getPlats(connection);
//            System.out.println("Donne moi le nom du plat : ");
//            String nom = sc.nextLine();
//            System.out.println("Donne moi le prix du plat : ");
//            double prix_unitaire = sc.nextDouble();
//            System.out.println("Donne moi le prix de revient du plat : ");
//            double prix_de_revient = sc.nextDouble();
//            Plat nouveauPlat = new Plat(nom,prix_unitaire,prix_de_revient);
//            nouveauPlat.savePlat(connection);
//            Plat.getPlats(connection);


            if (choix == 2) {
                System.out.println("Bienvenue dans le module de mise à jour de la base de données.");
                System.out.println("1. Nouvelle table");
                System.out.println("2. Nouveau serveur");
                System.out.println("3. Nouveau plat");
                System.out.println("Quel élément souhaitez-vous ajouter ? (1, 2 ou 3)");
                int choixMAJ = sc.nextInt();
                sc.nextLine();

                if (choixMAJ == 1) {
                    //Bloc tables
            Table.getTables(connection);
            System.out.println("Donne moi le nom de la nouvelle table : ");
            String nom = sc.nextLine();
            System.out.println("Donne moi le nombre de convives max : ");
            int nb_places = sc.nextInt();
            sc.nextLine();
            int id=0;
            Table nouvelleTable = new Table(id,nom,nb_places);
            nouvelleTable.saveTable(connection);
            Table.getTables(connection);
                }
                if (choixMAJ == 2) {
                    //Bloc serveurs
            Serveur.getServeurs(connection);
            System.out.println("Donne moi le nom du nouveau serveur : ");
            int id=0;
            String nom = sc.nextLine();
            Serveur nouveauServeur = new Serveur(id,nom);
            nouveauServeur.saveServeur(connection);
            Serveur.getServeurs(connection);
                }
                if (choixMAJ == 3) {
                    //Bloc plats
            Plat.getPlats(connection);
            int id=0;
            System.out.println("Donne moi le nom du plat : ");
            String nom = sc.nextLine();
            System.out.println("Donne moi le prix du plat : ");
            double prix_unitaire = sc.nextDouble();
            sc.nextLine();
            System.out.println("Donne moi le prix de revient du plat : ");
            double prix_de_revient = sc.nextDouble();
            sc.nextLine();
            Plat nouveauPlat = new Plat(id,nom,prix_unitaire,prix_de_revient);
            nouveauPlat.savePlat(connection);
            Plat.getPlats(connection);
                }
            }

                if (choix == 3) {
                    System.out.println("Classement des tables les plus vendeuses :");
                    DriverManager dbConnect;
                    Statement statement = connection.createStatement();
                    ResultSet resultSet1 = statement.executeQuery("select site.id,site.nom,sum (join_plat_facture.quantite*plat.prix_unitaire) from site join facture on site.id = facture.idx_site join join_plat_facture on facture.id = join_plat_facture.idx_facture join plat on join_plat_facture.idx_plat = plat.id GROUP BY site.id ORDER BY sum(join_plat_facture.quantite*plat.prix_unitaire) DESC");

                    while (resultSet1.next()) {
                        System.out.print(resultSet1.getString("nom"));
                        System.out.printf("%15s", resultSet1.getDouble("sum"));
                        System.out.print(" EUR");
                        System.out.println();
                    }
                    System.out.println();

                    System.out.println("Classement des plats les plus rentables :");
                    ResultSet resultSet2 = statement.executeQuery("select plat.id, plat.nom, sum (join_plat_facture.quantite*plat.prix_unitaire) from plat join join_plat_facture on plat.id = join_plat_facture.idx_plat GROUP BY plat.id ORDER BY sum (join_plat_facture.quantite*plat.prix_unitaire) DESC;");

                    while (resultSet2.next()) {
                        System.out.print(resultSet2.getString("nom"));
                        System.out.printf("%15s", resultSet2.getDouble("sum"));
                        System.out.print(" EUR");
                        System.out.println();
                    }
                    resultSet2.close();
                    statement.close();
                }

                //Je ferme la connexion.
                connection.close();
            } catch(Exception e){
                System.out.println("ça marche pas");
                e.printStackTrace();
            }
        }
    }