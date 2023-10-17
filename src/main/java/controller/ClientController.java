package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ClientController", urlPatterns = "/client")
public class ClientController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public void init(){
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        try{
            switch (action){
                case "selectProductByCategory":
                    selectProductByCategory(request, response);
                    break;
//                case "search":
//                    searchProduct(request, response);
//                    break;
                default:
                    listProduct(request, response);
                    break;
            }
        }catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        List<Product> productList = productDAO.selectAllProduct();
        request.setAttribute("productList", productList);
        List<Category> categoryList = categoryDAO.selectAllCategory();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client/index.jsp");
        dispatcher.forward(request, response);
    }
    private void selectProductByCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        List<Category> categoryList = categoryDAO.selectAllCategory();
        request.setAttribute("categoryList", categoryList);
        int categoryID = Integer.parseInt(request.getParameter("cid"));
        List<Product> productList = productDAO.selectProductByCategory(categoryID);
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client/index.jsp");
        dispatcher.forward(request, response);
    }
//    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
//        List<Category> categoryList = categoryDAO.selectAllCategory();
//        request.setAttribute("categoryList", categoryList);
//        String keyword = request.getParameter("keyword");
//        List<Product> productList = productDAO.search(keyword);
//        request.setAttribute("productList", productList);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client/index.jsp");
//        dispatcher.forward(request, response);
//    }
}
