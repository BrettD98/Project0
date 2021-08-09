package com.revature.persistance;

import com.revature.model.User;
import com.revature.util.dbConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements DAO<User>{
    static final Logger logger = Logger.getLogger(UserDAO.class);
    /**
     * Inserts a user into the database table with the same name
     * @param ent User object being inserted into the user table
     */
    @Override
    public boolean insert(User ent) {

        if(selectBy(ent.getUsername()) == null){
            String sql = "insert into \"Bank\".\"user\" (first_name, last_name,\"password\",username) values (?, ?, ?, ?);";

            try(Connection connection = dbConnection.getConnection()){
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, ent.getFirstName());
                ps.setString(2, ent.getLastName());
                ps.setString(3, ent.getPassword());
                ps.setString(4, ent.getUsername());

                ps.execute();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return true;
        }
        else {
            logger.error("Username already taken");
            return false;
        }
    }

    /**
     * Gets the total number of users on the db and returns an array of those users
     * @return an array of users.
     */
    @Override
    public User[] selectAll() {
        User[] userArray = null;

        try( Connection connection = dbConnection.getConnection()){

            //Don't need this group if you used the list.
            PreparedStatement arrPS = connection.prepareStatement("select count(*) from \"Bank\".\"user\"");
            ResultSet arrRS = arrPS.executeQuery();
            arrRS.next();

            userArray = new User[arrRS.getInt(1)];

            PreparedStatement ps = connection.prepareStatement("select * from \"Bank\".\"user\" u;");
            ResultSet rs = ps.executeQuery();

            int i = 0;
            while(rs.next()){
                userArray[i] = new User(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return userArray;
    }

    /**
     * Selects and returns a specific user object from the user table
     * @param ent Username being compared in database
     * @return User object from database table
     */
    public User selectBy(String ent) {
        String sql = "select * from \"Bank\".\"user\" u where username = ?;";

        User user = null;

        try( Connection connection = dbConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ent);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new User(rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("password"),
                                rs.getString("username"),
                                rs.getInt("userid"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Updates a user
     * @param ent User object to be updated
     */
    @Override
    public void update(User ent, String toChange, String changeTo) {
        String sql;
        switch (toChange){
            case "username":
                sql = "update \"Bank\".\"user\" set username = ? where username = ?;"; break;
            case "password":
                sql = "update \"Bank\".\"user\" set password = ? where username = ?;"; break;
            case "first_name":
                sql = "update \"Bank\".\"user\" set first_name = ? where username = ?;"; break;
            case "last_name":
                sql = "update \"Bank\".\"user\" set last_name = ? where username = ?;"; break;
            default:
                logger.fatal("This should have never gotten here");
                sql = "update \"Bank\".\"user\" set password = ? where username = ?;";
        }

        try(Connection connection = dbConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, changeTo);
            ps.setString(2, ent.getUsername());

            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Removes a user from the user table
     * @param ent User object to be removed
     */
    @Override
    public void delete(User ent) {
        String sql = "delete from \"Bank\".\"user\" where username = ?";

        try(Connection connection = dbConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ent.getUsername());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
