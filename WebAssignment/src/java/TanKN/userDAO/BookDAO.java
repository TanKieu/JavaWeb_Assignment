/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.userDAO;

import TanKN.userDTO.BookDTO;
import TanKN.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author winnh
 */
public class BookDAO implements  Serializable{
    public List<BookDTO> bookList;

    public List<BookDTO> getBookList() {
        return bookList;
    }
    public void bookList()throws SQLException, NamingException{
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        
        try{
            con=DBUtils.makeConnection();
            if(con!=null){
                String sql="Select bookID, bookname "
                        +"From \"Book\" ";
                stm=con.createStatement();
                rs=stm.executeQuery(sql);
                
                while(rs.next()){
                    String id=rs.getString("bookID");
                    String bookname= rs.getString("bookname");
                    BookDTO book=new BookDTO(id, bookname);
                    if(this.bookList==null){
                        this.bookList=new ArrayList();
                    }
                    this.bookList.add(book);
                }
                        
            }
        }catch(SQLException | NamingException ex){
            ex.printStackTrace();
        }
        finally{
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
    public BookDTO getBookbyName(String name) throws SQLException, NamingException {
        bookList();
        for(BookDTO book: bookList){
            if(name.equals(book.getBookname())){
                return book;
            }
        }
        return null;
    }
    
    
}
