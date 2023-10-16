package dao;

import model.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements IAdminDAO{
    private static final String select_admin_byID = "select username, password from admin where username = ?";
    private static final String select_all_admin = "select * from admin";
    private String jdbcURL = "jdbc:mysql://localhost:3306/product_management?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "beo01219230619";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public Admin selectAdmin(String username) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(select_admin_byID);
            preparableStatement.setString(1 , username);
            ResultSet rs = preparableStatement.executeQuery();

            System.out.println(this.getClass() + " selectAccount: " + preparableStatement);
            while (rs.next()){
                String account_username = rs.getString("username");
                String password = rs.getString("password");
                Admin admin = new Admin(account_username, password);
                return admin;
            }
            //preparableStatement.setString(2, name);
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return null;
    }

    @Override
    public List<Admin> selectAllAdmin() {
        List<Admin> listAdmin = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(select_all_admin);
            ResultSet rs = preparableStatement.executeQuery();

            while (rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                Admin admin = new Admin(username, password);
                listAdmin.add(admin);
            }
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return listAdmin;
    }

    @Override
    public  List<Admin> selectAllAdminStatement(){
        List<Admin> listAdmin = new ArrayList<>();
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(select_all_admin);
            while (rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                Admin admin = new Admin(username, password);
                listAdmin.add(admin);
            }
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return listAdmin;
    }

    @Override
    public boolean deleteAdmin(String username) throws SQLException {
        return false;
    }

    @Override
    public boolean updateAdmin(String username) throws SQLException {
        return false;
    }

    private void printSQLException(SQLException ex) {
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
