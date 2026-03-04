/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.Registration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.model.UserDao;
import pe.model.UserDto;
import pe.model.registration.RegistrationDAO;

/**
 *
 * @author ASUS
 */
public class LoginServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.html";
    private final String INVALID_PAGE = "invalid.html";

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
        String url = INVALID_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

//        UserDao dao = new UserDao();
//        UserDto user = dao.checkLogin(username, password);
        try {
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.checkLogin(username, password);
            if (result) {
                url = SEARCH_PAGE;
            }
        } catch (SQLException ex) {
            log("LoginServlet _ SQL" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("LoginServlet _ Clas not found" + ex.getMessage());
        } finally {
            //send DTO to views
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

            out.close();
        }
    }
//            if (user != null) {
//                out.println("<h2>Login Information</h2>");
//                out.println("Username: " + username + "<br/>");
//                out.println("Password: " + password + "<br/><br/>");
//                out.println("<a href='search.html'>Go to Search Page</a>");
//            } else {
//                out.println("<h2>Login Failed</h2>");
//                out.println("<a href='login.html'>Back to Login</a>");
//
//            }
//
//        } finally {
//            out.close();
//        }
//    }

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
