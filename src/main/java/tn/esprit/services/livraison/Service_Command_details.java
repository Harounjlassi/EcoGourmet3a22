package tn.esprit.services.livraison;

import tn.esprit.models.livraison.CommandeDetail;
import tn.esprit.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service_Command_details {
    private Connection cnx ;
    public static List<CommandeDetail> COMMANDE_DETAILS;
    public Service_Command_details(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    public List<CommandeDetail> getCommandeDetails(int idCommande) {
        String selectCommandeDetailsQuery =
                "SELECT a.Nom_du_plat, pa.quantite, c.prix_total, c.adresse \n" +
                "                FROM Commande c JOIN Panier_Annonce pa ON c.id_panier = pa.id_panier \n" +
                "                JOIN Annonce a ON pa.id_Annonce = a.id_Annonce \n" +
                "                WHERE c.id_commande = ?;";

        List<CommandeDetail> commandeDetailsList = new ArrayList<>();

        try {
            PreparedStatement pst = cnx.prepareStatement(selectCommandeDetailsQuery);
            pst.setInt(1, idCommande);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                CommandeDetail detail = new CommandeDetail();
                detail.setNomDuPlat(rs.getString("Nom_du_plat"));
                detail.setQuantite(rs.getInt("quantite"));
                detail.setPrixTotal(rs.getInt("prix_total"));
                detail.setAdresse(rs.getString("adresse"));
                commandeDetailsList.add(detail);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des détails de la commande : " + e.getMessage());
        }

        return commandeDetailsList;
    }
}