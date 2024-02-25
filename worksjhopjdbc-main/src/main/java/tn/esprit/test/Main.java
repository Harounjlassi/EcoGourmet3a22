package tn.esprit.test;

import tn.esprit.models.Personne;
import tn.esprit.services.ServicePersonne;
import tn.esprit.utils.MyDataBase;

public class Main {
    public static void main(String[] args) {

        Personne p1 = new Personne(1000,"ben mohamed ", "mohamed ",20);
        Personne p2 = new Personne(5,"ben mohamed ", "mohamed haroun ",35);
        Personne p3 = new Personne(47,"ben mohamed ", "mohamed ali ",99);

        ServicePersonne sp = new ServicePersonne();
//        sp.add(p1);
//        sp.add(p2);
//        sp.add(p3);
        //sp.delete(p2);
       // sp.update(p3);



        //System.out.println(sp.getAll());


    }
}