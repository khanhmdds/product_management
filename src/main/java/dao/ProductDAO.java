package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO{
    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO product (image, title, price, quantity, description , idCategory) VALUES " + " (?, ?, ?, ?,?,?);";
    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM product";
    private static final String SELECT_PRODUCT_BY_ID = "select * from product where id =?;";
    private static final String UPDATE_PRODUCT = "update product set image=?, title =?, price=?, quantity =?, description =?, idCategory=? where id = ?;";
    private static final String DELETE_PRODUCT = "delete from product where id = ?;";

    private String jdbcURL = "jdbc:mysql://localhost:3306/product_management?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Bokhanh@271298";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertProduct(Product products) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
            preparedStatement.setString(1, products.getImage());
            preparedStatement.setString(2, products.getTitle());
            preparedStatement.setInt(3, products.getPrice());
            preparedStatement.setInt(4, products.getQuantity());
            preparedStatement.setString(5, products.getDescription());
            preparedStatement.setInt(6,products.getIdCategory());

            System.out.println(this.getClass() + " insertProduct: " + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Product selectProduct(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() +  " selectProduct: " + preparedStatement);
            while (rs.next()){
                int idProduct = rs.getInt("id");
                String title = rs.getString("title");
                String image = rs.getString("image");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                int category = rs.getInt("idCategory");
                Product products = new Product(idProduct ,title,image,price,quantity,description,category);
                return products ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> productsList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            System.out.println("preparableStatement: " + preparableStatement);
            ResultSet rs = preparableStatement.executeQuery();

            while (rs.next()){
                int idProduct = rs.getInt("id");
                String title = rs.getString("title");
                String image = rs.getString("image");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                int idCategory = rs.getInt("idCategory");
                Product products = new Product(idProduct,title,image,price,quantity,description,idCategory);
                productsList.add(products);
            }
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return productsList;
    }

    @Override
    public List<Product> selectAllProductStatement() {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted = false;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            System.out.println(this.getClass() + " deleteProduct " + preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getImage());
            preparedStatement.setString(2, product.getTitle());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6,product.getIdCategory());
            preparedStatement.setInt(7, product.getId());
            System.out.println(this.getClass() + " updateProduct " + preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
            return rowUpdated;
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return false;
    }

    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
