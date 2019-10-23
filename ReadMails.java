/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.net.*;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Sanjeev
 */
public class ReadMails extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ArrayList all=new ArrayList();
    
    StringBuilder str=new StringBuilder();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         JSONObject json = new JSONObject();
//        response.setContentType("text/html;charset=UTF-8");
        try{
//             out.writeObject(allMails);
           String email=request.getParameter("email");
           String pass=request.getParameter("pass");
         System.out.println("email id======"+email+"pasword======="+pass);
 PrintWriter out=response.getWriter();
         
//        ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
//        Enumeration paramNames = request.getParameterNames();
//        String params[] = new String[2];
//        int i = 0;
//        while (paramNames.hasMoreElements()) {
//          String paramName = (String) paramNames.nextElement();
// 
//            //System.out.println(paramName);
//            String[] paramValues = request.getParameterValues(paramName);
//            params[i] = paramValues[0];
// 
//            //System.out.println(params[i]);
//            i++;
//            }
        ReadMailSample rms=new ReadMailSample();
        str.append(rms.readMails(email, pass)+"\n");
        System.out.println("====="+str.toString());
//        out.writeBytes(str.toString());
        out.println(str.toString());
        System.out.println("responses sended");
//      out.writeUTF(str.toString());
//        if(str==null)
//        {
//            json.put("info", "nothing");
//        }
//        else
//        {
//           System.out.println("alll mails content===="+str.toString());
//        json.put("info",str.toString());
//        }
        }
//            System.out.println()
            
            
        catch(Exception e)
        {
            e.printStackTrace();
        }
//         response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        System.out.println("json object value========="+json.toString());
       
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
