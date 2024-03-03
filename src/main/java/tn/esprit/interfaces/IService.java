package tn.esprit.interfaces;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IService<T>{
    public void add(T element);
    //public void getall();
    ArrayList<T> getAll();
    public void modifier(T element );
    public void supprimer(int id);
}
