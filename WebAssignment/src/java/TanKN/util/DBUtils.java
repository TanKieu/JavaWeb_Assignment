/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author winnh
 */
public class DBUtils implements Serializable{
    public static Connection makeConnection()throws NamingException, SQLException{

        //1.get current context
        Context context= new InitialContext();
        //2.get server context
        Context tomcatContext=(Context) context.lookup("java:comp/env");
        //3.get Data source
        DataSource ds=(DataSource) tomcatContext.lookup("KNT123");
        //4.make Connection
        Connection con =ds.getConnection();
        return con;
    }
}
