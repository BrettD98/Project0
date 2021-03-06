package com.revature.persistance;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.util.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO implements DAO<Account>{
    /**
     * Inserts an Account object into the database table
     * @param ent Account Object enterd into the database
     * @return if success
     */
    @Override
    public boolean insert(Account ent) {
        String sql = "insert into \"Bank\".account (balance, account_type, user_id) values (cast(? as float), ?, cast(? as int));";
        try(Connection connection = dbConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Double.toString(ent.getBalance()));
            ps.setString(2, ent.getAccountType());
            ps.setString(3, Integer.toString(ent.getUserID()));

            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //Not Being Used, no need for implementation
    @Override
    public Account[] selectAll() {
        return null;
    }

    /**
     * Overloaded selectAll method
     * Gets all Accounts that ahve anything to do with userID given
     * and returns an array of accounts
     * @param userID ID given by service
     * @return array of Accounts
     */
    public Account[] selectAll(int userID) {
        Account[] accounts = null;
        String sql = "select * from \"Bank\".account a where user_id  = cast(? as int);";

        try( Connection connection = dbConnection.getConnection()){

            //Don't need this group if you used the list.
            PreparedStatement arrPS = connection.prepareStatement("select count(*) from \"Bank\".account where user_id = cast(? as int);");
            arrPS.setInt(1, userID);
            ResultSet arrRS = arrPS.executeQuery();
            arrRS.next();

            accounts = new Account[arrRS.getInt(1)];

            PreparedStatement ps = connection.prepareStatement("select * from \"Bank\".account u where user_id = cast(? as int) order by account_id asc;");
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            int i = 0;
            while(rs.next()){
                accounts[i] = new Account(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4));
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }

    /**
     * Selects and returns a specific account from the database
     * @param ent account ID given from the service
     * @return Account object
     */
    @Override
    public Account selectBy(String ent) {
        String sql = "select * from \"Bank\".account a where account_id  = cast(? as int);";
        Account account = null;
        try(Connection connection = dbConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ent);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                account = new Account(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return account;
    }

    /**
     * Updates data for a specific account
     * @param ent account object being updated
     * @param toChange the field being updated
     * @param changeTo data to change the field into
     */
    @Override
    public void update(Account ent, String toChange, String changeTo) {
        String sql = "update \"Bank\".account set balance = cast(? as float) where account_id = ?;";

        try(Connection connection = dbConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, changeTo);
            ps.setInt(2, ent.getAccountID());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * Removes a specific account from the database
     * @param ent
     */
    @Override
    public void delete(Account ent) {
        String sql = "delete from \"Bank\".account  where account_id = cast(? as int);";

        try(Connection connection = dbConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Integer.toString(ent.getAccountID()));
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
