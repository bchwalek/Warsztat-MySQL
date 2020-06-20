package pl.coderslab.entity;

import org.apache.commons.lang3.ArrayUtils;

import java.sql.*;
import java.util.Scanner;
import java.util.SortedMap;

public class UserDao {

    private static final String createUser = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";
    private static final String readUser = "SELECT * FROM users WHERE id=?";
    private static final String updateUser = "UPDATE users SET  email = ?, username = ?, password = ? WHERE id =?";
    private static final String deleteUser = "DELETE FROM users WHERE id = ?";
    private static final String findAllUser = "SELECT * FROM users";


    public static User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(createUser, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            }
            return user;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static User read(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(readUser);
            statement.setInt(1, userId);
            ResultSet userData = statement.executeQuery();
            if (userData.next()){
            User user = new User(userData.getInt(1), userData.getString(2), userData.getString(3));
//            user.setId(userData.getInt(1));
//            user.setEmail(userData.getString(2));
//            user.setUserName(userData.getString(3));
            user.setPassword(userData.getString(4));
            return user;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } return null;
    }

    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(updateUser);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public void delete(int userId){
        try(Connection conn=DbUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(deleteUser);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public User[] findAll(){
        try(Connection conn=DbUtil.getConnection()) {
            User[] users = new User[0];
            PreparedStatement statement = conn.prepareStatement(findAllUser);
            ResultSet userAll = statement.executeQuery();
            while (userAll.next()) {
                User user = new User();
                user.setId(userAll.getInt(1));
                user.setEmail(userAll.getString(2));
                user.setUserName(userAll.getString(3));
                user.setPassword(userAll.getString(4));
               users= ArrayUtils.add(users, user);
            } return users;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }

    public void register(){
        User user = new User();
        Scanner scan = new Scanner(System.in);
        System.out.println("REJESTRACJA UŻYTKOWNIKA");
        System.out.println("");

        System.out.println("Podaj Imię i Nazwisko: ");
        user.setUserName(scan.nextLine());

        System.out.println("Podaj email: ");
        user.setEmail(scan.nextLine());

        System.out.println("Podaj hasło: ");
        user.setPassword(scan.nextLine());

        UserDao.create(user);
        scan.close();
    }

    public void dispalyData(){
        Scanner scan = new  Scanner(System.in);
        System.out.println("Podaj id użytkownika do wyświetlenia: ");
        int id=scan.nextInt();
        UserDao.read(id);
        while (UserDao.read(id)==null){
            System.out.println("Brak użytkownika. Podaj poprawne ID: "); id = scan.nextInt();
        }
        System.out.println(UserDao.read(id));
        scan.close();
        }
}
