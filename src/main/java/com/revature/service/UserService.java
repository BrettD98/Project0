package com.revature.service;

import com.revature.model.User;
import com.revature.persistance.UserDAO;

public class UserService {
    private UserDAO userDAO;
    private User loggedUser;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public boolean login(String un, String pw) {
        User loginUser = userDAO.selectBy(un);
        if(loginUser != null && loginUser.getPassword().equals(pw)){
            loggedUser = loginUser;
            return true;
        }else{
            return false;
        }
    }

    public boolean register(String fn, String ln, String un, String pw, String conPW) {
        if(pw.equals(conPW)){
            if(userDAO.selectBy(un) == null){
                userDAO.insert(new User(fn, ln, pw, un));
                loggedUser = userDAO.selectBy(un);
                return true;
            }
        }
        return false;
    }

    public int getUserID(){
        return loggedUser.getUserID();
    }
}
