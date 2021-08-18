/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.userDAO;

import TanKN.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author winnh
 */
public class OrderDetailDAO implements Serializable{
    public boolean newOrder(String clientID, String bookID, String quantity)throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement stm=null;
        try{
            con=DBUtils.makeConnection();
            String sql="INSERT INTO OrderDetail(clientID, bookID, quantity) "
                    +"Values(?,?,?)";
            stm=con.prepareStatement(sql);
            stm.setString(1, clientID);
            stm.setString(2, bookID);
            stm.setString(3, quantity);
            int row=stm.executeUpdate();
            if(row>0){
                return true;
            }
        }finally{
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return false;
    }
}
