package pl.coderslab.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

  private static final  String dbURL ="jdbc:mysql://localhost:3306/workshop2?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
  private static final  String dbUser ="root";
  private static final String dbPassword="coderslab";

  public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(dbURL, dbUser, dbPassword);
  }
}
