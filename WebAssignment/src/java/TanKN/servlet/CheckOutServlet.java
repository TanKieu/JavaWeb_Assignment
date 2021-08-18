/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.servlet;

import TanKN.Cart.CartObjct;
import TanKN.userDAO.ClientDAO;
import TanKN.userDAO.OrderDetailDAO;
import TanKN.userDTO.CheckOutOrderError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
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
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
    private final String SHOPPING_PAGE = "shoppingPage";
    private final String VIEW_PAGE="view";

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
        boolean foundError=false;
        CheckOutOrderError error= new CheckOutOrderError();
        String url=SHOPPING_PAGE;
        try {
            HttpSession session=request.getSession(false);
            if(session!=null){
                CartObjct cart=(CartObjct) session.getAttribute("CUSTCART");
                if(cart!=null){
                    String clientname=request.getParameter("txtClientName");
                    if(clientname.length()<2||clientname.length()>50){
                        foundError=true;
                        error.setClientnameLengthError("Client name is required from 2- 50 chars");
                    }
                    if(foundError){
                        request.setAttribute("CHECKOUT_ERRORS", error);
                        url=VIEW_PAGE;
                    }
                    else{
                        ClientDAO Cdao=new ClientDAO();
                        Cdao.createNewClient(clientname);
                        String ClientID=Cdao.getIDbyName(clientname);
                        if(!clientname.equals("")){
                            Map<String, Integer> items=cart.getItem();
                            Map<String, String > bookID=cart.getBookID();
                            for(String key: items.keySet()){
                                OrderDetailDAO dao=new OrderDetailDAO();
                                dao.newOrder(ClientID, bookID.get(key), items.get(key).toString());
                            }
                            session.removeAttribute("CUSTCART");
                        }
                    }
                    
                }
            }
        } catch (SQLException ex) {
            log("CheckOutServlet_SQLException "+ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOutServlet_NamingException "+ex.getMessage());
        }finally{
            RequestDispatcher rd= request.getRequestDispatcher(url);
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
