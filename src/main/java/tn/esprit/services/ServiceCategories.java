package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Categories;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceCategories implements IService<Categories> {

    private Connection cnx;

    public ServiceCategories() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Categories c) {
        // ajouter une Events dans la bd
        //1 - req SQL done
        //2 - executer la req SQL done
        String qry = "INSERT INTO `categories`(`name`, `description`) VALUES (?,?)";
        try {

            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, c.getName());

            stm.setString(2, c.getDescription());




            stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


    }

    @Override
    public ArrayList<Categories> getAll() {


        //retourner toute les perosnnes dans la bd
        //1- req SQL done
        //2 -execution de la req done
        // 3- remplire la liste done
        // 4 - retourner la liste done
        ArrayList<Categories> categorieArray = new ArrayList();
        String qry = "SELECT * FROM `Categories`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Categories e = new Categories();
                e.setName(rs.getString("name"));

                e.setDescription(rs.getString(2));



                //e.setButton(new Button("Supprimer"));


                categorieArray.add(e);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return categorieArray;
    }

    @Override
    public void update(Categories c) {
        try {

            String req = "UPDATE `Categories` SET `name` = '" + c.getName() +
                    "', `description` = '" + c.getDescription() + "' WHERE `Categories`.`category_id` = " + c.getId();
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) > 0) {
                System.out.println("Categorie updated successfully!");
            } else {
                System.out.println("No Categorie updated!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public boolean delete(Categories c) {
        try {
            String req = "DELETE FROM `categories` WHERE category_id = " + c.getId();
            ;
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) > 0) {
                System.out.println("Categorie deleted successfully!");
            } else {
                System.out.println("No Categorie deleted!");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

    public ArrayList<String> getAllName() {
        ArrayList<String> categorieArray = new ArrayList();
        String qry = "SELECT name FROM `Categories`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
               // e.setName(rs.getString("name"));




                //e.setButton(new Button("Supprimer"));


                categorieArray.add(rs.getString("name"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return categorieArray;
    }


    public int getIdByName(String value) {
        int id = 0;
        String qry = "SELECT category_id FROM `categories` WHERE name = '" + value + "'";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
    while ( rs.next() ){
        id = rs.getInt("category_id");
    }

    }
           catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return id;
}
}
