/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.model.registration.RegistrationDAO;
import pe.model.registration.RegistrationDTO;

@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.html";
    private final String RESULT_PAGE = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = SEARCH_PAGE;
        // 1. Controller gets all user's information
        String searchValue = request.getParameter("txtSearchValue");
        try {
            if (searchValue.trim().length() > 0) {
                // 2. Controller calls methods of Model
                // 2.1 Controller initializes DAO object
                RegistrationDAO dao = new RegistrationDAO();
                // 2.2 Controller calls methods of DAO object
                dao.searchLastname(searchValue);
                // 3. Controller processes result
                List<RegistrationDTO> result = dao.getAccounts(); //kieu cua resuilt
                request.setAttribute("SEARCH_RESULT", result);
                url = RESULT_PAGE;
                
            }

        } catch (SQLException ex) {
            log("SearchLastnameServlet _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("SearchLastnameServlet _ Class Not Found" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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

//if (result != null){ // have atleast record
//                log("STT \t Username \t Password \t Fullname \t Role ");
//              int count = 0;
//            for (RegistrationDTO dto : result) {
//              log(""
//                    + ++count
//                  + " \t "
//                        + dto.getUsername()
//                      + " \t "
//                            + dto.getPassword()
//                          + " \t "
//                                + dto.getFullName()
//                              + " \t "
//                                    + dto.toString());
//}
// } else { //no record
//   log("No record is matched!!!");
        //}
