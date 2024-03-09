package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BlobWriteDemo {

    // JDBC BASIC CREDENDTIALS //
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/aloo";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";


    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main(String[] args) {

        try {
            connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
            File f = new File("D:\\1 VERY VERY DEV\\Genie Course\\Java Code\\JDBC_2nd\\src\\main\\java\\res\\cdfds.jpeg");
            FileInputStream fis = new FileInputStream(f);
            preparedStatement = connection.prepareStatement("insert into blobdemo values(?,?)");
            preparedStatement.setInt(1,1);
            preparedStatement.setBinaryStream(2,fis,(int)f.length());
            LocalDateTime now = LocalDateTime.now();
            System.out.println(now);
            preparedStatement.executeUpdate();
            LocalDateTime now1 = LocalDateTime.now();
            System.out.println(now1);
            System.out.println("Data Inserted -------------");
            connection.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }


}
