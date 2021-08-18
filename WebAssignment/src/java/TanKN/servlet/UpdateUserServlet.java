/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.servlet;

import TanKN.userDAO.UserDAO;
import TanKN.userDTO.UpdateError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author winnh
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/UpdateUserServlet"})
public class UpdateUserServlet extends HttpServlet {
    private final String SEARCH_PAGE="searchPage";

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
        String username=request.getParameter("txtUserName");
        String password=request.getParameter("txtPassword");
        String fullname=request.getParameter("txtFullName");
        String role="false";
        String isAdmin=request.getParameter("chkAdmin");
        String searchValue=request.getParameter("lastSearch");
        String url="searchLastName?"
                  +"&txtSearchValue="
                  +searchValue; 
        if(isAdmin!=null){
            role="true";
        }
        boolean foundError=false;
        UpdateError errors=new UpdateError();
        try  {
            if(password.trim().length()<6||password.trim().length()>30){
                foundError=true;
                errors.setPasswordLengthError("Password is required from 6 to 30 chars");
            }
            if(fullname.trim().length()<2|| fullname.trim().length()>50){
                foundError=true;
                errors.setFullNameLengthError("Full name is requried from 2 to 50 chars");
            }
            if(foundError){
                request.setAttribute("UPDATE_ERRORS", errors);
            }else{
                UserDAO dao= new UserDAO();
                dao.update(username, password, fullname, role);            
            }
        } catch (SQLException ex) {
            log("UpdateUserServlet_SQL Exception "+ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateUserServlet_NamingException "+ex.getMessage());
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
