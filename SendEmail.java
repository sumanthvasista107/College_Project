/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sanjeev
 */
public class SendEmail extends HttpServlet {

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
//        PrintWriter out=response.getWriter();
         ObjectOutputStream out1 = new ObjectOutputStream(response.getOutputStream());
        try  {
            /* TODO output your page here. You may use following sample code. */
          String toAdd=request.getParameter("toAdd");
          String toAdd1=toAdd.replaceAll("\\s", "");
          String subj=request.getParameter("subj");
          String mesg=request.getParameter("mesg");
          String semail=request.getParameter("semail");
          String spass=request.getParameter("spass");
          System.out.println("client Details==="+toAdd+"\n"+subj+"\n"+mesg+"\n"+semail+"\n"+spass+"\n");
          Mail m=new Mail();
          boolean b=Mail.secretMail(mesg, subj, toAdd1, semail, spass);
          if(b)
          {
              out1.writeObject("Mail Sended successfully");
//              out.println("DONE");
          }
          else
          {
              out1.writeObject("An Error Occured While Sending Mail");
//              out.println("fail");
          }
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
