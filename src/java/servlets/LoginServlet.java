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
import services.AccountService;

/**
 *
 * @author 845593
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //acquire session attribute user
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
       //create variable for message to be displayed
        String message = "";
        //check if there is a parameter of "logout", if so, set session attribute "user" to null and display "successfully log out"
        if (request.getParameterMap().containsKey("logout")) {
         
            message = "You have successfully logged out.";
         //set attribute message to the local variable
            request.setAttribute("message", message);
         //set session attribute user to null
            session.setAttribute("user",null);
            //forward the jsp
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
           return;
        }
        //check if there is already a session attribute of "user", redirct page to home page.
        else if  (user!=null){
        response.sendRedirect("home");
        return;
        }
        //if session attribute user is null and there is no parameter of logout, forward the login jsp directly
        else getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //create accountService to check user input, if invalid return null, if valid return an user instance
        AccountService accountService = new AccountService();
        
        User user = accountService.login(username, password);
        //set session attribute to user
        session.setAttribute("user",user);
        //if session attribute is null, give invalid input message 
           if (user == null) {

            request.setAttribute("message", "invalid input");

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        } else {
            //if session attribute user is not null, redirect to home page.
            response.sendRedirect("home");
            return;
        }

    }

}
