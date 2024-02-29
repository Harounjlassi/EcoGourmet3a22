package tn.esprit.interfaces;

import java.util.ArrayList;

public interface IService<T> {

    void add (T t);
    void update(T t);
    boolean delete(T t);
//getOne getById

}
