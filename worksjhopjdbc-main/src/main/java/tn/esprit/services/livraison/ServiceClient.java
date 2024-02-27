package tn.esprit.services.livraison;

import tn.esprit.interfaces.IService;
import tn.esprit.models.livraison.client;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceClient implements IService<client> {
    private Connection cnx ;
    public ServiceClient(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    public void add (client client){
        String qry ="INSERT INTO `client`(`nom`, `prénom`, `age`,`tel`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,client.getNom());
            stm.setString(2,client.getPrénom());
            stm.setInt(3,client.getAge());
            stm.setInt(4,client.getTel());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }
    public ArrayList<client> getAll(){
        ArrayList<client> clients= new ArrayList();
        String qry ="SELECT * FROM `client`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                client cl = new client();
                cl.setId(rs.getInt(1));
                cl.setNom(rs.getString("nom"));
                cl.setPrénom(rs.getString(3));
                cl.setAge(rs.getInt("age"));
                cl.setTel(rs.getInt("tel"));
                clients.add(cl);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return clients;
    };
    public void update(client c1){};
    public boolean delete(client c1){ return false;};
    public client getById(int id) {
        client client = null;
        String qry = "SELECT * FROM `client` WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prénom");
                int age = rs.getInt("age");
                int tel = rs.getInt("tel");

                client = new client(id, nom, prenom, age, tel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return client;
    }
    public client getClientId(client client) {
        String qry = "SELECT `id` FROM `client` WHERE `nom` = ? AND `prénom` = ? AND `age` = ? AND `tel` = ?";
        int id = -1;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, client.getNom());
            stm.setString(2, client.getPrénom());
            stm.setInt(3, client.getAge());
            stm.setInt(4, client.getTel());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new client(id);
    }



}
