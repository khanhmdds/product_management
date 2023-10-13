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
private IAccountDAO iAccountDAO;
private ICountryDAO iCountryDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/signin0.jsp");
    requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gmail = req.getParameter("email");
        String password = req.getParameter("password");
        List<Account> accountList = iAccountDAO.selectAllAccount();
        boolean flag = true;
        for (Account account : accountList){
            if (gmail.equals(account.getGmail()) && password.equals(account.getPassword())){
                resp.sendRedirect("/product");
                flag = false;
                break;
            }
//            else {
//                req.setAttribute("message", "Wrong Email or Password!");
//                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/signin0.jsp");
//                requestDispatcher.forward(req, resp);
//            }
        }
        if (flag) {
            req.setAttribute("message", "Wrong Email or Password!");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/signin0.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    public void init() throws ServletException {
        iAccountDAO = new AccountDAO();
        iCountryDAO = new CountryDAO();
        List<Country> listCountry = iCountryDAO.selectAllCountry();

        if (this.getServletContext().getAttribute("listCountry") == null) {
            this.getServletContext().setAttribute("listCountry", listCountry);
        }
    }
}


