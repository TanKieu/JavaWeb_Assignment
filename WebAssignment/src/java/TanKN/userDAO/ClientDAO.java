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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author winnh
 */
public class ClientDAO implements Serializable{
    
    public String getIDbyName(String name)throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        
        try{
            con=DBUtils.makeConnection();
            if(con!=null){
                String sql="Select clientID "
                        +"From \"Client\" "
                        +"Where clientname=?";
                stm=con.prepareStatement(sql);
                stm.setString(1, name);
                
                rs=stm.executeQuery();
                if(rs.next()){
                    String id=rs.getString("clientID");
                    return id;
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
        return null;
    }
    public boolean createNewClient(String clientname)throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement stm=null;
        try{
            con=DBUtils.makeConnection();
            String sql="INSERT INTO Client(clientname) "
                    +"Values(?)";
            stm=con.prepareStatement(sql);
            stm.setString(1, clientname);
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
