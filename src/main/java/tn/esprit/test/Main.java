package tn.esprit.test;

import com.sun.security.jgss.GSSUtil;
import tn.esprit.models.Categories;
import tn.esprit.models.Events;
import tn.esprit.models.Personne;
import tn.esprit.models.Votes;
import tn.esprit.services.ServiceCategories;
import tn.esprit.services.ServiceEvents;
import tn.esprit.services.ServicePersonne;
import tn.esprit.services.ServiceVotes;
import tn.esprit.utils.MyDataBase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = new Date(utilDate.getTime());



        Personne p1 = new Personne(1000,"ben mohamed ", "mohamed ",20);
        Personne p2 = new Personne(5,"ben mohamed ", "mohamed haroun ",35);
        Personne p3 = new Personne(47,"ben mohamed ", "mohamed ali ",99);
        //Events e= new Events(2,"dd", sqlDate,"dd",23,"dd");
        //Events e2= new Events(3,"haroun", sqlDate,"xx",222,"ddw");
        Categories ca = new Categories(34,"RfgooooooR","fsgdpopgdd");
        Votes v =new Votes(241,"comzzdsfsdfmant",212232,1,25);


        ServiceEvents sp = new ServiceEvents();
        ServiceCategories sc = new ServiceCategories();
        ServiceVotes sv= new ServiceVotes();
        System.out.println(sp.getAll());

       // sp.add(e);
        //System.out.println(sp.getAll());
        //
        //sp.update(e2);
        //sc.add(ca);
        //System.out.println(sc.getAll());
        //sc.update(ca);
        //sc.getAll();
        //System.out.println(sc.getAllName());
//        sp.add(p2);
//        sp.add(p3);
        //sp.delete(e2);
       // sp.update(p3);
       // sv.delete(v);
        //System.out.println(sv.getAll());



        //System.out.println(sp.getAll());


    }
}