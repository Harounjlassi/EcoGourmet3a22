package tn.esprit.interfaces;

public interface IService<T>{
    public void add(T element);
    public void getall();
    public void modifier(T element );
    public void supprimer(int id);
}
