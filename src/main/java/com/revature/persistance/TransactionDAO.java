package com.revature.persistance;

import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.util.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO implements DAO<Transaction>{
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

    @Override
    public Transaction[] selectAll() {
        return new Transaction[0];
    }

    @Override
    public Transaction selectBy(String ent) {
        return null;
    }

    @Override
    public void update(Transaction ent, String toChange, String changeTo) {

    }

    @Override
    public void delete(Transaction ent) {

    }

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
