/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Cart;

import TanKN.userDTO.BookDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author winnh
 */
public class CartObjct implements Serializable{
    Map<String, Integer> item;
    Map<String, String> bookID;

    public Map<String, Integer> getItem() {
        return item;
    }

    public Map<String, String> getBookID() {
        return bookID;
    }
    
    public void addBooktoCart(BookDTO book){
        if(this.item==null){
            this.item=new HashMap<>();
        }
        int quantity=1;
        if(this.item.containsKey(book.getBookname())){
            quantity=this.item.get(book.getBookname())+1;
        }
        this.item.put(book.getBookname(), quantity);
        
        if(this.bookID==null){
            this.bookID=new HashMap<>();
        }
        this.bookID.put(book.getBookname(), book.getId());
    }
    public void removeBookfromCart(String bookname){
        if(this.item==null){
            return;
        }
        if(this.item.containsKey(bookname)){
            this.item.remove(bookname);
            if(this.item.isEmpty()){
                this.item=null;
            }
        }
    }
    
}
