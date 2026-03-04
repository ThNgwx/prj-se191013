package pe.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class MainController extends HttpServlet {

//    private static final String WELCOME="login.jsp";
    private static final String WELCOME = "login.html";
    private static final String LOGIN_CONTROLLER = "LoginServlet";
    private static final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private static final String DELETE_PK_CONTROLLER = "DeletePkServlet";
    private static final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";
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
        //        String url= WELCOME;
        //        try {
        //            String action= request.getParameter("action");
        //            //-----            your code here   --------------------------------
        //            
        //            //-----            your code here   --------------------------------
        //        } catch (Exception e) {
        //            log("error at MainController: "+ e.toString());
        //        }finally{
        //            request.getRequestDispatcher(url).forward(request, response);
        //        }
         String url = WELCOME;
        String action= request.getParameter("action");
        try {
            if (action == null) {
              //do nothing  
            } else if (action.equals("Login")){
                // User đã nhấn nút login 
                url = LOGIN_CONTROLLER;
            }else {
                switch (action) {
                    case "Search":
                        url = SEARCH_LASTNAME_CONTROLLER;
                        break;
                    case "delete":
                        url = DELETE_PK_CONTROLLER;
                        break;
                    case "Create New Account":
                        url = CREATE_ACCOUNT_CONTROLLER;
                } 
                
            }
           
        } catch (Exception e) {
            log("error at MainController: " + e.toString());
        } finally {
//            request.getRequestDispatcher(url).forward(request, response); 
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
     * * @throws ServletException if a servlet-specific error occurs
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
