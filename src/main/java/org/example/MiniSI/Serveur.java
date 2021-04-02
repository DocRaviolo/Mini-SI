package org.example.MiniSI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
    int id;
    String nom;

    public Serveur(int id,String nom) {
        this.id=id;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return id + ". " + nom + " ";
    }

    public void saveServeur(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO serveur (nom) VALUES ('" + nom  +"')");
        ordreSQL.close();
    }

    public static List<Serveur> getServeurs(Connection connection) throws SQLException {
        // Pouvoir lister des serveurs
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from serveur ORDER BY id");

        List<Serveur> serveursList = new ArrayList<>();

        while (resultats.next()) {
            Serveur dbServeur = new Serveur(resultats.getInt("id"),resultats.getString("nom"));
            serveursList.add(dbServeur);

            System.out.println(dbServeur);
        }

        resultats.close();
        ordreSQL.close();

        return serveursList;
    }

}
