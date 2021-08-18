/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.servlet;

import TanKN.Cart.CartObjct;
import TanKN.userDAO.BookDAO;
import TanKN.userDTO.BookDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author winnh
 */
@WebServlet(name = "AddBooktoCartServlet", urlPatterns = {"/AddBooktoCartServlet"})
public class AddBooktoCartServlet extends HttpServlet {
    private final String SHOPPING_PAGE="shoppingPage";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //1.Cust goes to cart place
            HttpSession session=request.getSession();
            //2.Cust take cart
            CartObjct cart=(CartObjct) (session.getAttribute("CUSTCART"));
            if(cart==null){
                cart=new CartObjct();
            }//end if cart is not exist
            //3.cust chon mon hang
            BookDAO dao= new BookDAO();
            String bookname= request.getParameter("cboBook");
            //converts ten thanh dto
            BookDTO book= dao.getBookbyName(bookname);
            
            cart.addBooktoCart(book);
            session.setAttribute("CUSTCART", cart);
        } catch (SQLException ex) {
            log("AddBooktoCartServlet_SQLException "+ex.getMessage());
        } catch (NamingException ex) {
            log("AddBooktoCartServlet_NamingException "+ex.getMessage());
        }finally{
            RequestDispatcher rd=request.getRequestDispatcher(SHOPPING_PAGE);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
