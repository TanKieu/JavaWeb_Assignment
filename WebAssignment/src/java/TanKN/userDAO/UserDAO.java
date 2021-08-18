/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.userDAO;

import TanKN.userDTO.UserDTO;
import TanKN.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import org.apache.tomcat.jni.OS;

/**
 *
 * @author winnh
 */
public class UserDAO implements Serializable{
    public  List<UserDTO> userList;

    public List<UserDTO> getUserList() {
        return userList;
    }
    
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
                        +"From \"User\" "
                        +"Where username=?  AND password=? ";
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
    public void searchValue(String searchValue) throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try{
            //1.make connection
            con=DBUtils.makeConnection();
            //2.Create SQL String
            if(con !=null){
                String sql="Select username, password, lastname,isAdmin "
                        +"From \"User\" "
                        +"Where lastname LIKE ? ";
                //3.Create Statement
                stm=con.prepareStatement(sql);
                stm.setString(1, "%"+searchValue+"%");
                //4.execute Query
                rs=stm.executeQuery();
                while(rs.next()){
                    String username=rs.getString("username");
                    String password=rs.getString("password");
                    String lastname=rs.getString("lastname");
                    boolean role=rs.getBoolean("isAdmin");
                    UserDTO dto=new UserDTO(username, password, lastname, role);
                    if(this.userList ==null){
                        this.userList= new ArrayList<>();
                    }//end if user list not initialized
                    this.userList.add(dto);
                }//end if con is not initialized
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
    }
    public boolean delete(String username)throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement stm=null;
        try{
            //1.make Connection
            con=DBUtils.makeConnection();
            //2.crate SQL String
            if(con!=null){
                String sql ="Delete From \"User\" "
                            +"Where username=?";
                //3.Create Statement
                stm=con.prepareStatement(sql);
                stm.setString(1, username);
                //4.Execute Query
                int row=stm.executeUpdate();
                if(row>0){
                    return true;
                }
                
            }//end if con is not initialized
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
    public boolean update(String username, String password,String fullname, String isAdmin) throws SQLException,NamingException{
        Connection con=null;
        PreparedStatement stm=null;
        try{
            //1.Make connection
            con=DBUtils.makeConnection();
            //2.Create SQL String
            if(con!=null){
                String sql="Update \"User\" "
                        +"Set password=?, lastname=?, isAdmin=? "
                        +"Where username=?";
                //3.Create Statement
                stm=con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, fullname);
                stm.setString(3, isAdmin);
                stm.setString(4, username);
                //4.Execute query
                int row=stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }//end if con is not initialized
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
    public String getRealNamebyUsername(String username)throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try{
            //1.make connection
            con=DBUtils.makeConnection();
            //2.Create sql String
            if(con!=null){
                String sql="Select lastname "
                       +"From \"User\" "
                       +"Where username=?";
             //3.Create statement
             stm=con.prepareStatement(sql);
             stm.setString(1, username);
             //4.Execute query
             rs=stm.executeQuery();
             if(rs.next()){
                 String realname=rs.getString("lastname");
                 return realname;
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
    public boolean insert(String username, String password, String fullname) throws SQLException, NamingException{
        Connection con =null;
        PreparedStatement stm=null;
        try {
            con=DBUtils.makeConnection();
            if(con!=null){
                String sql="INSERT into \"User\"(username, password, lastname,isAdmin) "
                        +"Values(?,?,?,?)";
                stm=con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, false);
                int row=stm.executeUpdate();
                if(row>0){
                    return true;
                }
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
