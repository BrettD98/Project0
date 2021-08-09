package com.revature.persistance;

import com.revature.model.Account;

public class AccountDAO implements DAO<Account>{
    @Override
    public boolean insert(Account ent) {
        return false;
    }

    @Override
    public Account[] selectAll() {
        return new Account[0];
    }

    @Override
    public Account selectBy(String ent) {
        return null;
    }

    @Override
    public void update(Account ent, String toChange, String changeTo) {

    }

    @Override
    public void delete(Account ent) {

    }
}
