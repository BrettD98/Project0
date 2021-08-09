package com.revature.persistance;

public interface DAO <E> {
    //CRUD
    //Create
    boolean insert(E ent);

    //Read
    E[] selectAll();
    E selectBy(String ent);

    //Update
    void update(E ent, String toChange, String changeTo);

    //Delete
    void delete(E ent);

}
