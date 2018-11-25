package controller;

/**
 * File created on 11/22/2018
 * by Toader
 **/
public class User {
    private MyController controller;

    private String userName;
    private String password;

    public User(MyController controller) {
        this.controller = controller;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public MyController getController() {
        return controller;
    }

    public void setController(MyController controller) {
        this.controller = controller;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
