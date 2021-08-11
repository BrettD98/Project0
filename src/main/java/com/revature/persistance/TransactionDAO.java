package com.revature.persistance;

import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.util.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO implements DAO<Transaction>{

    /**
     * Inserts a Transaction into the transaction table
     * @param ent Transaction Object being inserted into the table
     * @return
     */
    @Override
    public boolean insert(Transaction ent) {
        String sql = "insert into \"Bank\".\"transaction\" (id_from, id_to, amount) values (cast(? as int), cast(? as int), cast(? as float));";

        try(Connection connection = dbConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ent.getAccountFrom());
            ps.setInt(2, ent.getAccountTo());
            ps.setDouble(3, ent.getAmount());

                    ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    //Not Being Used, no need for implementation
    @Override
    public Transaction[] selectAll() {
        return new Transaction[0];
    }

    //Not Being Used, no need for implementation
    @Override
    public Transaction selectBy(String ent) {
        return null;
    }
    //Not Being Used, no need for implementation
    @Override
    public void update(Transaction ent, String toChange, String changeTo) {

    }
    //Not Being Used, no need for implementation
    @Override
    public void delete(Transaction ent) {

    }

    /**
     * Overloaded selectall
     * Gets all transactions that have anything ti do with given account
     * returns an array of those transactions
     * @param accountId Account ID given to find transactions
     * @return Tranaction Array
     */
    public Transaction[] selectAll(int accountId) {
                Transaction[] transactionArray = null;

        try( Connection connection = dbConnection.getConnection()){

            //Don't need this group if you used the list.
            PreparedStatement arrPS = connection.prepareStatement("select count(*) from \"Bank\".\"transaction\" t where id_from = cast(? as int) or id_to = cast(? as int);");
            arrPS.setInt(1, accountId);
            arrPS.setInt(2, accountId);
            ResultSet arrRS = arrPS.executeQuery();
            arrRS.next();

            transactionArray = new Transaction[arrRS.getInt(1)];

            PreparedStatement ps = connection.prepareStatement("select * from \"Bank\".\"transaction\" t where id_from = cast(? as int) or id_to = cast(? as int) order by id_to asc;");
            ps.setInt(1, accountId);
            ps.setInt(2, accountId);
            ResultSet rs = ps.executeQuery();

            int i = 0;
            while(rs.next()){
                transactionArray[i] = new Transaction(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4));
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return transactionArray;
    }
}
