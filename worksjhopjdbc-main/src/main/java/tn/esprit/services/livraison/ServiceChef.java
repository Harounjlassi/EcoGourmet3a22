package tn.esprit.services.livraison;

import tn.esprit.interfaces.IService;
import tn.esprit.models.livraison.chef;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceChef implements IService<chef>{
    private Connection cnx ;
    public ServiceChef(){
        cnx =MyDataBase.getInstance().getCnx();
    }
    public void add (chef chef){
        String qry ="INSERT INTO `chef`(`nom`, `prénom`, `age`,`tel`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,chef.getNom());
            stm.setString(2,chef.getPrénom());
            stm.setInt(3,chef.getAge());
            stm.setInt(4,chef.getTel());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }
    public ArrayList<chef> getAll(){
        ArrayList<chef> chefs= new ArrayList();
        String qry ="SELECT * FROM `chef`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                chef p = new chef();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrénom(rs.getString(3));
                p.setAge(rs.getInt("age"));
                p.setTel(rs.getInt("tel"));

                chefs.add(p);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return chefs;
    };
    public void update(chef c1){};
    public boolean delete(chef c1){ return false;};
    public chef getById(int id) {
        chef chef = null;
        String qry = "SELECT * FROM `chef` WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prénom");
                int age = rs.getInt("age");
                int tel = rs.getInt("tel");

                chef = new chef(id, nom, prenom, age, tel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return chef;
    }
    public chef getChefId(chef chef) {
        String qry = "SELECT `id` FROM `chef` WHERE `nom` = ? AND `prénom` = ? AND `age` = ? AND `tel` = ?";
        int id = -1;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, chef.getNom());
            stm.setString(2, chef.getPrénom());
            stm.setInt(3, chef.getAge());
            stm.setInt(4, chef.getTel());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new chef(id);
    }




}
