/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author 845593
 */
public class HomeServlet extends HttpServlet {

  

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession session=request.getSession();
        
        User user=(User)session.getAttribute("user");
         // if session attribute user is not null, always forward home page
        if(user!=null){
              getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);
       }

        else {
            //if no session attribute user,redirect to login page
              response.sendRedirect("login");}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }


}
