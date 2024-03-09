package org.example;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class EmployeeManagement
{

    // JDBC BASIC CREDENDTIALS //
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/aloo";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";


    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main( String[] args )
    {
        try {
            //making database connectivity
            connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
            // Uses Factory Design pattern

            //create table
             createEmployeeTable();

            Scanner sc= new Scanner(System.in);
            System.out.println("Enter 1 to exit \nEnter 2 for insert data");


            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // Fetch data
                    break;
                case 2:
                    // Insert data
                    insertMultipleData();
                    break;
//                case 3:
//                    // Batch update
//                    break;
//                case 4:
//                    // Delete data
//                    break;
                default:
                    System.out.println("Invalid choice");
            }

//            insert data into table
//            insertDataIntoDb("ash",100,"rahul@gmail.com");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private static void insertMultipleData() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of entries to insert:");
        int numEntries = scanner.nextInt();

        for (int i = 0; i < numEntries; i++) {
            System.out.println("Enter name:");
            String name = scanner.next();

            System.out.println("Enter age:");
            int age = scanner.nextInt();

            System.out.println("Enter email:");
            String email = scanner.next();

            insertDataIntoDb(name, age, email);
        }
    }

    private static void insertDataIntoDb(String name, int age, String email) throws SQLException {
        String insertSql = "INSERT INTO Employee (name ,age , email) VALUES (? , ? , ?)" ;
        preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,age);
        preparedStatement.setString(3,email);

        preparedStatement.executeUpdate();
        System.out.println("  Data Inserted---------------");
    }



    private static void createEmployeeTable() throws SQLException {

        String createTable = "CREATE TABLE IF NOT EXISTS Employee " +
                "               (id INT AUTO_INCREMENT PRIMARY KEY," +
                                "name VARCHAR(100) ," +
                                "age INT ," +
                                "email VARCHAR(100) " +
                                ")" ;

        preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        System.out.println(" Table created --------------------------");

    }
}

//
//--------- To Do ---------
//
//1. Fetch and insert multiple data
//2. update data based no id
//3. delete dat based on id
//4. try adding duplicate data