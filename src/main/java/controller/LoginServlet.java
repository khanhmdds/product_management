package controller;

import dao.*;
import model.User;
import model.Category;
import model.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
private UserDAO userDAO;
private AdminDAO adminDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/signin0.jsp");
    requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gmail = req.getParameter("email");
        String password = req.getParameter("password");
        List<User> accountList = userDAO.selectAllUser();
        List<Admin> adminList = adminDAO.selectAllAdmin();
        boolean flag = true;
        for (User account : accountList){
            if ((gmail.equals(account.getEmail()) && password.equals(account.getPassword())) || (gmail.equals(account.getUsername()) && password.equals(account.getPassword()))){
                resp.sendRedirect("/client");
                flag = false;
                break;
            }
//            else {
//                req.setAttribute("message", "Wrong Email or Password!");
//                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/signin0.jsp");
//                requestDispatcher.forward(req, resp);
//            }
        }
        for (Admin admin : adminList) {
            if (gmail.equals(admin.getUsername()) && password.equals(admin.getPassword())){
                resp.sendRedirect("/product");
                flag = false;
                break;
            }
        }
        if (flag) {
            req.setAttribute("message", "Wrong Email or Password!");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/signin0.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        adminDAO = new AdminDAO();
        List<Admin> listAdmin = adminDAO.selectAllAdmin();

        if (this.getServletContext().getAttribute("listAdmin") == null) {
            this.getServletContext().setAttribute("listAdmin", listAdmin);
        }
    }
}


