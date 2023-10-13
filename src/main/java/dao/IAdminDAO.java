package dao;

import model.Admin;

import java.sql.SQLException;
import java.util.List;

public interface IAdminDAO {
    //public void insertAdmin(Admin admin) throws SQLException;

    public Admin selectAdmin(String username);
    public List<Admin> selectAllAdmin();

    List<Admin> selectAllAdminStatement();

    public boolean deleteAdmin(String admin) throws SQLException;
    public boolean updateAdmin(String admin) throws SQLException;
}
