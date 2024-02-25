package tn.esprit.interfaces;

import java.util.ArrayList;

public interface ICService<T> {
    public void Ajouter(T t);
    public void Supprimer(int id);
    public void Modifier(int id,int prixtotal,String adresse,String etatcommande);
    public ArrayList<T> getAll();
}
