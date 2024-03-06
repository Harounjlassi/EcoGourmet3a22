package tn.esprit.services;

import javafx.scene.control.Button;
import tn.esprit.interfaces.IEService;
import tn.esprit.interfaces.IService;
import tn.esprit.models.Personne;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServicePersonne implements IEService<Personne> {
    private Connection cnx;

    public ServicePersonne() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Personne personne) {
        // ajouter une personne dans la bd
        //1 - req SQL done
        //2 - executer la req SQL done
        String qry = "INSERT INTO `personne`(`nom`, `prenom`, `age`) VALUES (?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, personne.getNom());
            stm.setString(2, personne.getPrenom());
            stm.setInt(3, personne.getAge());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }

    @Override
    public ArrayList<Personne> getAll() {


        //retourner toute les perosnnes dans la bd
        //1- req SQL done
        //2 -execution de la req done
        // 3- remplire la liste done
        // 4 - retourner la liste done
        ArrayList<Personne> personnes = new ArrayList();
        String qry = "SELECT * FROM `personne`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Personne p = new Personne();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString(3));
                p.setAge(rs.getInt("age"));
                p.setButton(new Button("Supprimer"));


                personnes.add(p);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return personnes;
    }

    @Override
    public void update(Personne p) {
        try {
            String req = "UPDATE `personne` SET `nom` = '" + p.getNom() + "', `prenom` = '" + p.getPrenom() +
                    "', `age` = '" + p.getAge() + "' WHERE `personne`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) > 0) {
                System.out.println("Personne updated successfully!");
            } else {
                System.out.println("No Personne updated!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public boolean delete(Personne personne) {
        try {
            String req = "DELETE FROM `personne` WHERE id = " + personne.getId();
            ;
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) > 0) {
                System.out.println("Personne deleted successfully!");
            } else {
                System.out.println("No Personne deleted!");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }



}
