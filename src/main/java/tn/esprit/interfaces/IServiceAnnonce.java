package tn.esprit.interfaces;

import java.util.ArrayList;

public interface IServiceAnnonce<T> {

    void add (T t);
    ArrayList<T> getAll();
    void update(T t);
    boolean delete(T t);
//getOne getById

}
