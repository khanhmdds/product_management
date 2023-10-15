package controller;

import dao.UserDAO;
import model.Admin;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    private void createGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/register.jsp");
        requestDispatcher.forward(req, resp);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        if (action == null) {
//            action = "";
//        }
//        try {
//            switch (action) {
//                case "create":
//                    createGet(req, resp);
//                    break;
//                default:
//                    break;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/register.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createPost(req, resp);
                break;
            default:
                break;
        }
    }

    private void createPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        //List<Account> accounts = accountDAO.selectAllAccount();
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");

        List<User> accountList = userDAO.selectAllUser();

        for (User account0 : accountList) {
            if (username.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                req.setAttribute("error", "Information cannot be empty");
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/register.jsp");
                requestDispatcher.forward(req, resp);
                break;
            }
            else if (email.equals(account0.getEmail())) {
                req.setAttribute("error", "Email already exist!");
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/register.jsp");
                requestDispatcher.forward(req, resp);
                break;
            }
            else if (pass.equals(rePassword)) {
                User account = new User(email, pass, username);
                try {
                    // Thực hiện đăng ký tài khoản
                    userDAO.registerUser(account);
                    req.setAttribute("account", account);
                    req.setAttribute("success", "Registration successful!");
                    requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/register.jsp");
                    requestDispatcher.forward(req, resp);
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Xử lý lỗi kết nối cơ sở dữ liệu hoặc lỗi khác
                    req.setAttribute("error", "An error occurred during registration.");
                    requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/register.jsp");
                    requestDispatcher.forward(req, resp);
                    break;
                }
            } else {
                // Password và rePassword không giống nhau, hiển thị thông báo lỗi
                req.setAttribute("error", "Password and Confirm Password do not match.");
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/account/register.jsp");
                requestDispatcher.forward(req, resp);
                break;
            }
        }
    }
}