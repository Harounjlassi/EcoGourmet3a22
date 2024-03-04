package tn.esprit.interfaces;

import java.util.ArrayList;

public interface IPService<T>{

    public void Ajouter(T t);
    public void Supprimer(int id);
    public void Modifier(T t);
    public ArrayList<T> getAll();

}
