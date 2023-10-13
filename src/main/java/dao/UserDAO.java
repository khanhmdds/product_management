package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
    private static final String INSERT_USER = "INSERT INTO user (username, password, email, phoneNumber, address) VALUES (?,?,?,?,?);";
    private static final String SELECT_ACCOUNT_BYID = "SELECT id , username, password, email , phoneNumber, address FROM user where id = ?";
    private static final String SELECT_ALL_USERS = "select * from user;";
//    private static final String INSERT_ACCOUNT = "INSERT INTO user (username, password, email, phoneNumber, address) VALUES (?,?,?,?,?);";
    private static final String DELETE_USERS_SQL = "delete from user where id = ?;";
    private static final String UPDATE_USERS_SQL = "update user set username = ?, password = ? , email = ? , phoneNumber = ? , address = ? where id = ?;";
    private static final String SP_EDIT_ACCOUNT = "call user(?, ?, ?, ?, ?)";
    private static final String CHECK_USERNAME_EXISTS = "SELECT * FROM user where username = ?";
    private static final String CHECK_EMAIL_EXISTS = "SELECT * FROM user where email = ?";

    private String jdbcURL = "jdbc:mysql://localhost:3306/product_management?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Bokhanh@271298";

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

    public void registerUser(User user) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPhoneNumber());
        preparedStatement.setString(5, user.getAddress());
        preparedStatement.executeUpdate();
    }

    @Override
    public void insertUser(User user) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPhoneNumber());
        preparedStatement.setString(5, user.getAddress());
        preparedStatement.executeUpdate();
    }

    @Override
    public User selectUser(int id) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_ACCOUNT_BYID);
            preparableStatement.setInt(1 , id);
            ResultSet rs = preparableStatement.executeQuery();

            System.out.println(this.getClass() + " selectAccount: " + preparableStatement);
            while (rs.next()){
                int idUser = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                User user = new User(idUser, username, password, email , phoneNumber, address);
                return user;
            }
            //preparableStatement.setString(2, name);
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return null;
    }

    @Override
    public List<User> selectAllUser() {
        List<User> listUser = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = preparableStatement.executeQuery();

            while (rs.next()){
                int idUser = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                User user = new User(idUser, username, password, email, phoneNumber, address);
                listUser.add(user);
            }
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return listUser;
    }

    public  List<User> selectAllUserStatement(){
        List<User> listUser = new ArrayList<>();
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_USERS);
            while (rs.next()){
                int idUser = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                User user = new User(idUser,username,password,phoneNumber,address);
                listUser.add(user);
            }
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return listUser;
    }

    @Override
    public boolean updateUserWithSP(User user) throws SQLException {
        Connection connection = getConnection();
        //call c6_customermanager.sp_editUser(?, ?, ?, ?, ?)
        CallableStatement callableStatement = connection.prepareCall(SP_EDIT_ACCOUNT);
        callableStatement.setInt(1, user.getId());
        callableStatement.setString(2, user.getUsername());
        callableStatement.setString(3, user.getPassword());
        callableStatement.setString(4, user.getPhoneNumber());
        callableStatement.setString(5, user.getAddress());
        callableStatement.registerOutParameter(5, Types.VARCHAR);

        System.out.println(this.getClass() + " updateUserWithSP " + callableStatement);
        callableStatement.executeUpdate();
        String message = callableStatement.getString(5);
        System.out.println("Message: " + message);

        return true;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return false;
    }

    @Override
    public boolean checkEmailExists(String email) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMAIL_EXISTS);
        preparedStatement.setString(1, email);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUsernameExists(String username) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME_EXISTS);
        preparedStatement.setString(1, username);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            return true;
        }
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
