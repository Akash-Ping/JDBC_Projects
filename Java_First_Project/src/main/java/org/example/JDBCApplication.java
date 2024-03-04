package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCApplication {
    public static void main(String[] args){ // it is a static area so we cant call non static method

        String url = "jdbc:mysql://localhost:3306/aloo";
        String username = "root";
        String password = "root";


        try {
            ///load and reg
            //Class.forName("com.mysql.cj.jdbc.Driver");

           Connection connection =  DriverManager.getConnection(url,username,password);
           Statement statement = connection.createStatement();
//           String query = "Select * from students";
//           ResultSet resultSet = statement.executeQuery("Select * from students");
//            ResultSet resultSet = statement.executeQuery(query);

            //1. user input ---> fetch data
            //2. user input ---> insert data
            Scanner sc= new Scanner(System.in);
            System.out.println("Enter 1 for fetch data \nEnter 2 for insert data\nEnter 3 for batch update\nEnter 4 for delete data");

            int choice = sc.nextInt();
            switch (choice){

                case 1:
                    ResultSet fetchData = statement.executeQuery("Select * from students");
                    Operations.fetchData(fetchData);
                    break;

                case 2:
                    ResultSet maxIdResultSet = statement.executeQuery("Select MAX(st_id) as Max_st_id from students");
//                    boolean next = maxIdResultSet.next();
                    int max_id = 0;
                    while(maxIdResultSet.next()){
                        max_id = maxIdResultSet.getInt("Max_st_id");
                        System.out.println("Max student ID: "+max_id);
                    }
                    max_id++;
                    System.out.println("Enter your Name");
                    String name = sc.next();
                    System.out.println("Enter the email");
                    String email = sc.next(); //sometime jvm skips nextline so here we have used next
                    System.out.println("Enter your Phone No");
                    String phoneNo = sc.next();


                    int rowCount = statement.executeUpdate("insert into students values("+max_id+",'"+name+"','"+email+"','"+phoneNo+"')"); //interger ke liye double quotes nahi lagate string ki liye lagate hai
                    if(rowCount > 0){
                        System.out.println("Data inserted sucessfully");
                    }
                    else{
                        System.out.println("Enter valid input");
                    }
                    break;

                case 3:
                    ResultSet maxIdResultSet1 = statement.executeQuery("Select MAX(st_id) as Max_st_id from students");
//                    boolean next = maxIdResultSet.next();
                    int max_id1 = 0;
                    while(maxIdResultSet1.next()){
                        max_id1 = maxIdResultSet1.getInt("Max_st_id");
                        System.out.println("Max student ID: "+max_id1);
                    }
//                    System.out.println("Enter the details in batch");
//                    String [] bulkQuries = new String[10];


                    System.out.println("Enter the number of records you want to insert:");
                    int numRecords = sc.nextInt();
                    sc.nextLine(); // Consume newline character
                    String[] bulkQueries = new String[numRecords];

                    for (int i = 0; i < numRecords; i++) {
                        System.out.println("Enter details for record " + max_id1 + ":");
                        System.out.println("Enter your Name:");
                        String name1 = sc.nextLine();
                        System.out.println("Enter the email:");
                        String email1 = sc.nextLine();
                        System.out.println("Enter your Phone No:");
                        String phoneNo1 = sc.nextLine();

                        max_id1++; // Increment max_id for each record
                        bulkQueries[i] = "insert into students values(" + max_id1 + ",'" + name1 + "','" + email1 + "','" + phoneNo1 + "')";

                    }

                    // Add batch queries
                    for (String query : bulkQueries) {
                        statement.addBatch(query);
                    }

                    // Execute batch
                    int[] updateCounts = statement.executeBatch();

                    // Process results
                    int totalRecordsInserted = 0;
                    for (int count : updateCounts) {
                        if (count >= 0) {
                            totalRecordsInserted++;
                        } else {
                            // Handle batch failure
                            System.out.println("Error occurred while inserting records.");
                        }
                    }

                    System.out.println(totalRecordsInserted + " records inserted successfully.");
                    break;

                    // statement.addBatch();
                    //statement.executeBatch();

                case 4:
                    System.out.println("Enter the id for delete record:");
                    int id = sc.nextInt();
                    int row = statement.executeUpdate("DELETE from students where st_id = " + id);
                    if(row>0)
                    {
                        System.out.println("Data Deleted:"+id);
                    }
                    else {
                        System.out.println("Data Deletion failed:");
                    }
                    break;


                default:
                    System.out.println("Enter Valid Input");
                    break;
            }

        /*   System.out.println("*********************** STUDENT DETAILS *****************************");
           while (resultSet.next()){
               String id = resultSet.getString("st_id");
               String name = resultSet.getString("name");
               String email = resultSet.getString("email");
               String phoneno = resultSet.getString("phoneNo");

               System.out.println("ID :" + id);
               System.out.println("NAME :" + name);
               System.out.println("EMAIL :" + email);
               System.out.println("PHONE NO :" + phoneno);


               System.out.println("Your id :" + resultSet.getInt("st_id") );
               System.out.println("Your name :" + resultSet.getString("name") );
               System.out.println("Your email :" + resultSet.getString("email") );
               System.out.println("Your name :" + resultSet.getString("phoneNo") );

               System.out.println("--------------------------------------------------------");
           }*/
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
