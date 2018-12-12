package controller;

/**
 * File created on 11/22/2018
 * by Toader
 **/
public class User {
    private MyController controller;

    private String userName;
    private String password;

    /**
     * This constructor represent the User class linked to
     * the My Controller class
     *
     * @param controller represents My Controller class
     */
    public User(MyController controller) {
        this.controller = controller;
    }

    /**
     * This constructor represent the User class with
     * username and password variables
     *
     * @param userName represents the username for the User
     * @param password represents the password for the User
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * This method returns a controller
     *
     * @return that controller linked to the User
     */
    public MyController getController() {
        return controller;
    }

    /**
     * This method set a controller to associate with
     * a My Controller entity
     *
     * @param controller the controller that is going to be linked to this User entity
     */
    public void setController(MyController controller) {
        this.controller = controller;
    }

    /**
     * This method set the userName for the specific user
     *
     * @return represents the username for the User
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method set the userName for the specific user
     *
     * @param userName represents the new userName for the User
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method get the password for the specific user
     *
     * @return gives password for the User
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method set the password for the specific user
     *
     * @param password represents the new password for the User
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
