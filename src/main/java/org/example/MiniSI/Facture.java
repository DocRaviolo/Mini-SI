package org.example.MiniSI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Facture {
    int id;
    int idx_site;
    int idx_serveur;

    public Facture(int idx_site,int idx_serveur) {
        this.idx_site = idx_site;
        this.idx_serveur = idx_serveur;
    }

    @Override
    public String toString() {
        return id + ". " + idx_site + " " + idx_serveur;
    }

    public void saveFacture(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO facture (idx_site, idx_serveur) VALUES ('" + idx_site + "','" + idx_serveur + "')");
        ordreSQL.close();
    }

    public static List<Facture> getFactures(Connection connection) throws SQLException {
        // Pouvoir lister des factures
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from facture");

        List<Facture> facturesList = new ArrayList<>();

        while (resultats.next()) {
            Facture dbFacture = new Facture(resultats.getInt("idx_site"),
                    resultats.getInt("idx_serveur"));
            facturesList.add(dbFacture);

            //System.out.println(dbFacture);
        }

        resultats.close();
        ordreSQL.close();

        return facturesList;
    }
}