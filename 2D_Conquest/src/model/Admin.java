package model;

/**
 *
 */
public class Admin implements User {

    private String userName;
    private String password;

    /**
     * Description...
     *
     * @return userName of the user
     */

    @Override
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return password of the user currently logged in
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @param password 
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
