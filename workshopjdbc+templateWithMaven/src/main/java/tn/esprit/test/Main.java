package tn.esprit.test;

import tn.esprit.models.Personne;
import tn.esprit.services.ServicePersonne;
import tn.esprit.utils.MyDataBase;

public class Main {
    public static void main(String[] args) {

        Personne p1 = new Personne(1000,"ben mohamed ", "mohamed ",20);
        Personne p2 = new Personne(8945,"ben mohamed ", "mohamed aziz ",35);
        Personne p3 = new Personne(4565,"ben mohamed ", "mohamed ali ",99);

        ServicePersonne sp = new ServicePersonne();
        sp.add(p1);
        sp.add(p2);
        sp.add(p3);


        System.out.println(sp.getAll());


    }
}