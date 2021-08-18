/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.userDTO;

import java.io.Serializable;

/**
 *
 * @author winnh
 */
public class BookDTO implements  Serializable{
    private String id;
    private String bookname;

    public BookDTO(String id, String bookname) {
        this.id = id;
        this.bookname = bookname;
    }

    public BookDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
    
    
}
