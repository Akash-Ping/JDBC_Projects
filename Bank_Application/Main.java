package Bank_Application;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		String url = "jdbc:mysql://localhost:3306/aloo";
		String username = "root";
		String password = "root";

		Operation operlation = new Operation();
		operlation.bankinfo();
	}

}