package org.example.MiniSI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Table {
    int id;
    String nom;
    int nb_places;

    public Table(int id,String nom,int nb_places) {
        this.id=id;
        this.nom = nom;
        this.nb_places = nb_places;
    }

    @Override
    public String toString() {
        return
                id+String.format("%20s",nom) + " " + nb_places+ " places.";
    }

    public void saveTable(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO site (nom, nb_places) VALUES ('" + nom + "','" + nb_places + "')");
        ordreSQL.close();
    }

    public static List<Table> getTables(Connection connection) throws SQLException {
        // Pouvoir lister des tables
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from site");

        List<Table> tablesList = new ArrayList<>();

        while (resultats.next()) {
            Table dbTable = new Table(resultats.getInt("id"),resultats.getString("nom"),
                    resultats.getInt("nb_places"));
            tablesList.add(dbTable);

            System.out.println(dbTable);
        }

        resultats.close();
        ordreSQL.close();

        return tablesList;
    }
}

