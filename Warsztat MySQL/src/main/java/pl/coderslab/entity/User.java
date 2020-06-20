package pl.coderslab.entity;

public class User {

    private int id;
    private String email;
    private String userName;
    private String password;

    public User(int id, String email, String userName) {
        this.id = id;
        this.email = email;
        this.userName = userName;
    }

    public User(){
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User = id: " + id + " email: " + email + " userName: " + userName +" password: " + password;
    }
}

