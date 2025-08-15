package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Demo {
	static String url="jdbc:mysql://localhost:3307/emp_table";
	static String unm="root";
	static String pwd="1234";
	static Connection con;
	
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,unm,pwd);
			if(con!=null) {
				System.out.println("Connected");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		
		
		 Scanner sc = new Scanner(System.in);
	        int choice;

	        while (true) {
	            System.out.println("\n===== Java DB CRUD Menu =====");
	            System.out.println("1. Add User");
	            System.out.println("2. View Users");
	            System.out.println("3. Update User");
	            System.out.println("4. Delete User");
	            System.out.println("5. Exit");
	            System.out.print("Enter choice: ");
	            choice = sc.nextInt();
	            sc.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter Name: ");
	                    String name = sc.nextLine();
	                    System.out.print("Enter Email: ");
	                    String email = sc.nextLine();
	                    addUser(name, email);
	                    break;
	                case 2:
	                   viewUsers();
	                    break;
	                case 3:
	                    System.out.print("Enter User ID to Update: ");
	                    int uid = sc.nextInt();
	                    sc.nextLine();
	                    System.out.print("Enter New Name: ");
	                    String newName = sc.nextLine();
	                    updateUser(uid, newName);
	                    break;
	                case 4:
	                    System.out.print("Enter User ID to Delete: ");
	                    int did = sc.nextInt();
	                    deleteUser(did);
	                    break;
	                case 5:
	                    System.out.println("Exiting...");
	                    sc.close();
	                    System.exit(0);
	                default:
	                    System.out.println(" Invalid choice.");
	            }
	        }
	}

	private static void updateUser(int uid, String name) throws SQLException {
		// TODO Auto-generated method stub
		String s="update users set name=? where id=?";
		PreparedStatement stmt=con.prepareStatement(s);
		stmt.setString(1, name);
		stmt.setInt(2, uid);
		int res=stmt.executeUpdate();
		System.out.println(
                "Updated Successed.. "
                + res);
	}
	
	private static void deleteUser(int uid) throws SQLException {
		// TODO Auto-generated method stub
		String s="delete from users where id=?";
		PreparedStatement stmt=con.prepareStatement(s);
		stmt.setInt(1, uid);
		int res=stmt.executeUpdate();
		System.out.println(
                "Deleted  Successed.. "
                + res);
	}

	private static void viewUsers() throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from users";
		Statement stmt=con.createStatement();
		ResultSet res=stmt.executeQuery(sql);
		while(res.next()) {
			System.out.println("Employee [ Id : "+res.getInt(1)+", Emp Name : "+res.getString(2)+", Emp Email : "+res.getString(3)+"]");
		}
	}

	private static void addUser(String name, String email) throws SQLException {
		// TODO Auto-generated method stub
		String sql="insert into users (name,email) values (?,?)";
		PreparedStatement stmt=con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, email);
		int res=stmt.executeUpdate();
		System.out.println(
                "Number of rows affected by this query: "
                + res);
		stmt.close();
		con.close();
		
	}
}

