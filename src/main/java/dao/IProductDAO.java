package dao;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    public void insertProduct(Product product) throws SQLException;

    public Product selectProduct(int id);

    public List<Product> selectAllProduct();

    List<Product> selectAllProductStatement();

    public boolean deleteProduct(int id) throws SQLException;

    boolean updateProduct(Product product) throws SQLException;

    public int getNoOfRecords();
    public List<Product> selectAllProductsPaggingFilter(int offset, int noOfRecords, String q, int idCategory);

}
