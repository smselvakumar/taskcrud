package tasks;
/*SELVAKUMAR S*/
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQL_CRUD {

	Connection con;
	Statement st;
	
	public static void main(String[] args) throws SQLException {
		SQL_CRUD j = new SQL_CRUD();
		
		System.out.println("****************************");
		System.out.println("Welcome to SQL CRUD Program");
		System.out.println("****************************");
		
		String option = "N";
		do {
			System.out.println("Enter The Choice");
			System.out.println("------------------");
			System.out.println("1. Create Data");
			System.out.println("2. Read Data");
			System.out.println("3. Update Data");
			System.out.println("4. Delete Data");

			Scanner s = new Scanner(System.in);
			int choice = s.nextInt();
			switch (choice) {
			case 1:
				j.createData();
				break;
			case 2:
				j.readData();
				break;
			case 3:
				j.updateData();
				break;
			case 4:
				j.deleteData();
				break;
			default:
				System.out.println("Invalid choice");
			}
		
			System.out.println("------------------------------------");
			System.out.println("Do you want to run the program again?\n");
			String runOption = s.next();
			option = runOption.trim().toUpperCase();			
		} while(option.equals("Y"));
		
		System.out.println("**************************");
		System.out.println("Thank you sir.. by Selva");
		System.out.println("**************************");
	}

	
	public void openConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/employee";
		String uname = "root";
		String password = "";
		con = DriverManager.getConnection(url, uname, password);
		st = con.createStatement();
	}

	public void closeConnection() throws SQLException {
		st.close();
		con.close();
	}

	private void createData() throws SQLException {

		openConnection();
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Employee Id:\n");
		int eid = s.nextInt();

		System.out.println("Enter Employee Name:\n");
		String name = s.next();

		System.out.println("Enter Employee Type:\n");
		String type = s.next();

		System.out.println("Enter Employee Salary:\n");
		double sal = s.nextDouble();

		String query = "INSERT INTO employeedata (emp_id, emp_name, emp_type, salary) VALUES (?, ?, ?, ?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, eid);
		pst.setString(2, name);
		pst.setString(3, type);
		pst.setDouble(4, sal);

		int rows = pst.executeUpdate();
		System.out.println("Number of rows inserted: " + rows);
		closeConnection();
	}

	private void readData() throws SQLException {

		String query = "select * from employeedata";
		openConnection();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {
			String emp = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
			System.out.println(emp);
		}
		closeConnection();
	}

	private void updateData() throws SQLException {

		openConnection();

		Scanner s = new Scanner(System.in);
		System.out.println("Enter Employee Id:\n");
		int eid = s.nextInt();

		System.out.println("Enter Employee Salary:\n");
		double sal = s.nextDouble();

		String query = "UPDATE employeedata SET salary = " + sal + " WHERE emp_id = " + eid;
		int rows = st.executeUpdate(query);
		System.out.println("Number of rows updated: " + rows);
	}

	private void deleteData() throws SQLException {

		openConnection();

		Scanner s = new Scanner(System.in);
		System.out.println("Enter Employee Id:\n");
		int eid = s.nextInt();

		String query = "DELETE FROM employeedata WHERE emp_id = " + eid;
		int rows = st.executeUpdate(query);
		System.out.println("Number of rows deleted: " + rows);
	}
}
