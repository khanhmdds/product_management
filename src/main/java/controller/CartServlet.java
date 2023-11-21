package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Item;
import model.Order;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    public void init() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        try {
            switch (action){
                case "addToCart":
                    addToCart(request, response);
                    break;
                case "removeCart":
                    removeCart(request, response);
                    break;
                case "checkOut":
                    checkOut(request, response);
                    break;
                default:
                    displayCart(request, response);
                    break;
            }
        }catch (SQLException ex){
            throw new ServletException(ex);
        }
    }
    protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{List<Category> categoryList = categoryDAO.selectAllCategory();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client/cart.jsp");
        dispatcher.forward(request, response);
    }
    protected void addToCart(HttpServletRequest request, HttpServletResponse httpServletResponse) throws SQLException, ServletException, IOException{
        int quantity = 1;
        int id;
        if(request.getParameter("id") != null){
            id = Integer.parseInt(request.getParameter("id"));
            Product product = productDAO.selectProduct(id);
            if(product != null){
                if(request.getParameter("quantity") != null){
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }
                HttpSession session = request.getSession();
                if(session.getAttribute("order") == null){
                    Order order = new Order();
                    List<Item> itemList = new ArrayList<Item>();
                    Item item = new Item();
                    item.setProduct(product);
                    item.setQuantity(quantity);
                    item.setPrice(product.getPrice());
                    itemList.add(item);
                    order.setItems(itemList);
                    session.setAttribute("order", order);
                }else {
                    Order order = (Order) session.getAttribute("order");
                    List<Item> itemList = order.getItems();
                    boolean check = false;
                    for(Item item : itemList){
                        if(item.getProduct().getId() == product.getId()){
                            item.setQuantity(item.getQuantity()+quantity);
                            check = true;
                        }
                    }
                    if(check == false){
                        Item item = new Item();
                        item.setProduct(product);
                        item.setQuantity(quantity);
                        item.setPrice(product.getPrice());
                        itemList.add(item);
                    }
                    session.setAttribute("order", order);
                }
            }
        }
    }
    protected void removeCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        if(request.getParameter("id") != null){
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            if(session.getAttribute("order") != null){
                Order order = (Order) session.getAttribute("order");
                List<Item> itemList = order.getItems();

                for(Item item : itemList){
                    if(item.getProduct().getId() == id){
                        itemList.remove(item);
                        break;
                    }
                }
                order.setItems(itemList);
                session.setAttribute("order", order);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client/cart.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }

    protected void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.removeAttribute("order");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client/cart.jsp");
        dispatcher.forward(request, response);
    }
}