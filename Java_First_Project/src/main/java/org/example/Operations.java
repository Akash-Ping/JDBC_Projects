package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Operations {

    public static void fetchData(ResultSet resultSet) throws SQLException {
        System.out.println("*********************** STUDENT DETAILS *****************************");
        while (resultSet.next()){
            String id = resultSet.getString("st_id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phoneno = resultSet.getString("phoneNo");

            System.out.println("ID :" + id);
            System.out.println("NAME :" + name);
            System.out.println("EMAIL :" + email);
            System.out.println("PHONE NO :" + phoneno);

            System.out.println("--------------------------------------------------------");
        }

        }

}
