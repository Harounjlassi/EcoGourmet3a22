package tn.esprit.interfaces;

import tn.esprit.models.Annonce;
import tn.esprit.models.User;

import java.util.List;

public interface IService<T>{
    public void add(T element);
    public void getall();
    public void modifier(T element );
    public void supprimer(int id);

    List<Annonce> getAll();

    void update(Annonce annonce);

    boolean delete(Annonce annonce);

}
