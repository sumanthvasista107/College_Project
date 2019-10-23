/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import email.*;

/**
 *
 * @author Sanjeev
 */
public class Register extends HttpServlet {

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
        try  {
            /* TODO output your page here. You may use following sample code. */
           response.setContentType("text/html");  
            ObjectOutputStream out=new ObjectOutputStream(response.getOutputStream()); 
            
String recived_data="";  
String s1=request.getParameter("email");  
 String s2=request.getParameter("pass");  
 System.out.println("User Name"+s1);  
 System.out.println("Password"+s2);  
// String res=insert(s1,s2);
 validate_code_source vcs=new validate_code_source();
 boolean isValid=vcs.isAddressValid(s1);
 System.out.println("======="+isValid);
 if(isValid)
 {
 if(insert(s1,s2))
 {
     out.writeObject("completed");
     
 }
 else
 {
        out.writeObject("Sorry username or password error");  
 }
 }else
 {
     out.writeObject("Emil account is not valid");
 }
 
 out.close();
        }
    catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static boolean insert(String s1,String s2) throws SQLException, ClassNotFoundException
    {
       
            boolean i;
            
            String s="";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vmail","root","root");
            PreparedStatement ps=con.prepareStatement(
                    "insert into user(email,pass) values(?,?)");
            ps.setString(1, s1);
            ps.setString(2,s2);
            i=    ps.execute();
            System.out.println("value of i======="+i);
            
//        con.close();
            
            
            return i;
     
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
