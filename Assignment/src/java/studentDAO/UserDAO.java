/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentDAO;

import TanKN.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author winnh
 */
public class UserDAO implements Serializable{
    public boolean checkLogin(String username, String password) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs= null;
        try{
            //1.,make connection
            con= DBUtils.makeConnection();
            //2.create SQL String
            if(con!=null){
                String sql="Select username "
                        +"From User "
                        +"Where username=/, password=? ";
                //3. create Statement
                stm=con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //execute query
                rs=stm.executeQuery();
                if(rs.next()){
                    return true;
                }
            }
        }finally{
            if(rs!=null){
                rs.close();
            }
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
