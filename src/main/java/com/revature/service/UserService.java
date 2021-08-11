package com.revature.service;

import com.revature.model.User;
import com.revature.persistance.UserDAO;

public class UserService {

    //Pointer to DAO
    //Currently logged in user
    private UserDAO userDAO;
    private User loggedUser;

    //constructor
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }


    /**
     * Calls DAO selectby method
     * If User exists,  returns true, if not returns false
     * @param un username
     * @param pw password
     * @return boolean, if login is successful or not
     */
    public boolean login(String un, String pw) {
        User loginUser = userDAO.selectBy(un);
        if(loginUser != null && loginUser.getPassword().equals(pw)){
            loggedUser = loginUser;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Checks if username already exists, if it does returns false
     *  if not, calls DAO to create a user
     * @param fn firstname
     * @param ln lastname
     * @param un username
     * @param pw password
     * @param conPW confirmed password
     * @return boolean if register is successful or not
     */
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

    //getter for userID
    public int getUserID(){
        return loggedUser.getUserID();
    }
}
