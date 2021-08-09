package com.revature.service;

import com.revature.model.User;
import com.revature.persistance.UserDAO;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public Boolean login(String un, String pw) {
        User loginUser = userDAO.selectBy(un);
        if(loginUser != null && loginUser.getPassword().equals(pw)){
            return true;
        }else{
            return false;
        }
    }
}
