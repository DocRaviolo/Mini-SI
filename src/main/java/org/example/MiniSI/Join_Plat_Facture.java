package org.example.MiniSI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Join_Plat_Facture {
    int id;
    int idx_plat;
    int idx_facture;
    int quantite;

    public Join_Plat_Facture(int idx_plat,int idx_facture,int quantite) {
        this.idx_plat = idx_plat;
        this.idx_facture = idx_facture;
        this.quantite=quantite;
    }

    @Override
    public String toString() {
        return id + ". " + idx_facture + " " + idx_plat + " " + quantite;
    }

    public void saveJoin(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO join_plat_facture (idx_plat, idx_facture,quantite) VALUES ('" + idx_plat+ "','" + idx_facture + "','" + quantite+ "')");
        ordreSQL.close();
    }

    public static List<Join_Plat_Facture> getJoins(Connection connection) throws SQLException {
        // Pouvoir lister des jointures
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from join_plat_facture");

        List<Join_Plat_Facture> joinList = new ArrayList<>();

        while (resultats.next()) {
            Join_Plat_Facture dbJoin = new Join_Plat_Facture(resultats.getInt("idx_plat"),
                    resultats.getInt("idx_facture"),
                    resultats.getInt("quantite"));
            joinList.add(dbJoin);

            //System.out.println(dbJoin);
        }

        resultats.close();
        ordreSQL.close();

        return joinList;
    }
}