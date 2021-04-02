package org.example.MiniSI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Plat {
    int id;
    String nom;
    double prix_unitaire;
    double prix_de_revient;

    public Plat(int id, String nom, double prix_unitaire, double prix_de_revient) {
        this.id=id;
        this.nom = nom;
        this.prix_unitaire = prix_unitaire;
        this.prix_de_revient = prix_de_revient;

    }

    @Override
    public String toString() {
        return id + ". " + nom + " " + prix_unitaire+ " " + prix_de_revient;
    }

    public void savePlat(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO plat (nom,prix_unitaire, prix_de_revient) VALUES ('" + nom + "','" + prix_unitaire + "','" + prix_de_revient + "')");
        ordreSQL.close();
    }

    public static List<Plat> getPlats(Connection connection) throws SQLException {
        // Pouvoir lister des personnages
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from plat");

        List<Plat> platsList = new ArrayList<>();

        while (resultats.next()) {
            Plat dbPlats = new Plat(resultats.getInt("id"), resultats.getString("nom"),
                    resultats.getDouble("prix_unitaire"),
                    resultats.getDouble("prix_de_revient"));
            platsList.add(dbPlats);

            System.out.println(dbPlats);
        }

        resultats.close();
        ordreSQL.close();

        return platsList;
    }
}
