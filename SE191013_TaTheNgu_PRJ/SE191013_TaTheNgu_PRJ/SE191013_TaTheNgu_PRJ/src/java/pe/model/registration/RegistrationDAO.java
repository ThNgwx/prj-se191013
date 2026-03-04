/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

/**
 *
 * @author MY PC
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Model connects DB
            con = DbUtils.getConnection();
            if (con != null) {
                //2. Create SQL String (15)
                String sql = "SELECT username "
                        + "FROM Registration "
                        + "WHERE username = ? AND password = ?";

                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query  (Step16)
                rs = stm.executeQuery();
                //5. Process result  (Step 17)
                if (rs.next()) {
                    result = true;
                    //username and passwoed are match
                }
            }// connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Mofel connects DB
            con = DbUtils.getConnection();
            if (con != null) {
                //2. Create DB SQL String
                String sql = "Select username, password, lastname, isAdmin " + "From Registration " + "Where lastname Like ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execite Query
                rs = stm.executeQuery();
                //5. Process result 
                while (rs.next()) {
                    //5.1 Model gets data from Result Set
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    Boolean role = rs.getBoolean("isAdmin");
                    //5.2 Model sets data to DTO Object
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    if (this.accounts == null) {
                        accounts = new ArrayList<>();
                    }// account is unavailable
                    this.accounts.add(dto);
                }// end travers each rows of table
            } // connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public boolean deleteAccount(String username)
            throws SQLException, ClassNotFoundException {

        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. model connect DB
            con = DbUtils.getConnection();
            if (con != null) {
                //2. create SQL 
                String sql = "DELETE FROM Registration"
                        + "WHERE username = ?";

                //3
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4
                int effectedRows = stm.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                }
            }// connection available
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        //1 model connect DB
        //2 tao cau lenh SQL
        //3 tao statement object
        //4 execute statement
        //5 xu li ket qua
        return result;

    }

    public boolean createAccount(RegistrationDTO account) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. model connect DB
            con = DbUtils.getConnection();
            if (con != null) {
                //2. create SQL 
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";

                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                //4.Execute Statement Object
                int effectedRows = stm.executeUpdate();
                //5. Process Result
                if (effectedRows > 0) {
                    result = true;
                }
            }// connection available
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        //1 model connect DB
        //2 tao cau lenh SQL
        //3 tao statement object
        //4 execute statement
        //5 xu li ket qua
        return result;

    }
}
