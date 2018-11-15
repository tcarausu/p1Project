package dao;

import java.sql.SQLException;

/**
 * File created on 11/8/2018
 * by Toader
 **/
public interface DatabaseI {

    public boolean verifyUserLogin(String username, String password) throws SQLException;

    public boolean verifyAdminLogin(String username, String password) throws SQLException;

}
