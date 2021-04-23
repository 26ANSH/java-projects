import java.sql.*;
import java.util.Scanner;
public class company
{
	static Connection get_db()throws SQLException
	{
		return DriverManager.getConnection("jdbc:mysql://localhost:8889/data", "root", "root");
	}
	static void isdb() throws SQLException
 	{
 		Connection db = DriverManager.getConnection("jdbc:mysql://localhost:8889", "root", "root");
		ResultSet rs = db.getMetaData().getCatalogs();
		 
		while(rs.next())
		{
			String catalogs = rs.getString(1);
			if("data".equals(catalogs))
			{
				return;
			}
		}
		
		Statement st = db.createStatement();
		String sql = "CREATE DATABASE data";
	    st.executeUpdate(sql);
	    st.close();
	    db = get_db();
	    st = db.createStatement();
		String create_database ="CREATE TABLE inventory(product varchar(128), quantity int, PRIMARY KEY(product));\n";
		st.executeUpdate(create_database);
		create_database ="CREATE TABLE sales(customer varchar(128), product varchar(128), quantity int);\n";
		st.executeUpdate(create_database);
 	}
	
	static void add_product(String pname, int quant) throws SQLException
	{
		Connection db = get_db();
		Statement st = db.createStatement();
		ResultSet r = st.executeQuery("select * from inventory");
		while(r.next())
		  {
			  if(r.getString("product").equals(pname))
			  {
					  System.err.println("Product already Exists || Available quantity = "+r.getInt("quantity"));
					  return;
			  }
				  
		  }
		System.err.println("Product Added to Inventory");
		String cmnd = ("INSERT INTO inventory (product, quantity) VALUES ('"+pname+"','"+ quant+"');");
		st.executeUpdate(cmnd);
	}
	
	static int get_quant(String pname) throws SQLException
	{
		Connection db = get_db();
		Statement st = db.createStatement();
		ResultSet r = st.executeQuery("select * from inventory");
		while(r.next())
		  {
			  if(r.getString("product").equals(pname))
			  {
				  return r.getInt("quantity");
			  }
				  
		  }
		return -1;
	}
	
	
	static void bills() throws SQLException
	{
		Connection db = get_db();
		Statement st = db.createStatement();
		ResultSet r = st.executeQuery("SELECT * FROM sales ORDER By customer, product, quantity;");
		System.out.format("+ ------------ + ------------ + -------- +\n");
		System.out.format("|   Customer   |    Product   | Quantity |\n");
		System.out.format("+ ------------ + ------------ + -------- +\n");
		while(r.next())
		  {
			System.out.format("|%12s  |%12s  |%5d     |", r.getString("customer"), r.getString("product"),r.getInt("quantity") );
			System.out.println();
		  }
		System.out.format("+ ------------ + ------------ + -------- +\n");
	}
	static void products() throws SQLException
	{
		Connection db = get_db();
		Statement st = db.createStatement();
		ResultSet r = st.executeQuery("SELECT * FROM inventory ORDER By product;");
		System.out.format("+ ------------ + -------- +\n");
		System.out.format("|    Product   | Quantity |\n");
		System.out.format("+ ------------ + -------- +\n");
		while(r.next())
		  {
			System.out.format("|%12s  |%5d     |", r.getString("product"),r.getInt("quantity") );
			System.out.println();
		  }
		System.out.format("+ ------------ + -------- +\n");
	}
	static void update_product(String pname, int quant) throws SQLException
	{
		Connection db = get_db();
		Statement st = db.createStatement();
		ResultSet r = st.executeQuery("select * from inventory");
		while(r.next())
		  {
			  if(r.getString("product").equals(pname))
			  {
					  System.err.println("Product Quantity Updated");
					  int new_quantity = r.getInt("quantity") - quant;
					  String cmnd = ("UPDATE inventory SET quantity ="+new_quantity+" WHERE product = '"+pname+"';");
					  st.executeUpdate(cmnd);
					  return;
			  }
				  
		  }
	}
	
	
	
	static void remove_product(String pname) throws SQLException
	{
		Connection db = get_db();
		Statement st = db.createStatement();
		ResultSet r = st.executeQuery("select * from inventory");
		while(r.next())
		  {
			  if(r.getString("product").equals(pname))
			  {
					  System.err.println("Product Removed From inventory");
					  String cmnd = ("DELETE FROM inventory WHERE product='"+pname+"';");
					  st.executeUpdate(cmnd);
					  return;
			  }
				  
		  }
		System.err.println("Product NOT Found");
	}
	
	static void add_sale(String cname, String pname, int quant) throws SQLException
	{
		Connection db = get_db();
		Statement st = db.createStatement();
		int avail = get_quant(pname);
		if(avail == -1)
		{
			System.err.println("Product Not Found");
			return;
		}
		else if(avail < quant)
		{
			System.err.println("Not Sufficient Quantity | Available = "+avail);
			return;
		}
		String cmnd = ("INSERT INTO sales (customer, product, quantity) VALUES ('"+cname+"','"+pname+"','"+ quant+"');");
		st.executeUpdate(cmnd);
		update_product(pname, quant);
		System.err.println("Sale Record Added");
	}

	public static void main(String[] args) throws SQLException
	{
		System.out.println("-------------- Welcome to Sales and Inventory Management ----------------");
		isdb();
		
		Scanner input=new Scanner(System.in);
		int option, quant;
		String cname, pname;
		do {
			System.out.println("-------------------- Choose what u want to do ------------------------");
			System.out.println("1. Add Product Record ");
			System.out.println("2. Add Sales Record ");
			System.out.println("3. Delete Product Record ");
			System.out.println("4. Display all Product Records");
			System.out.println("5. Display all Sales Records");
			System.out.println("6. Exit From System");
			option = input.nextInt();
			switch (option)
			{
			case 1:
			{
				System.out.println("Enter Record details You Want to add");
				System.out.println("Enter Product name");
				pname = input.next();
				System.out.println("Enter Product Quantity");
				quant = input.nextInt();
				
				add_product(pname, quant);
			}break;
			
			case 2:
			{
				System.out.println("Enter sales details You Want to add");
				System.out.println("Enter Customer name");
				cname = input.next();
				System.out.println("Enter Product name");
				pname = input.next();
				System.out.println("Enter Product Quantity");
				quant = input.nextInt();
				
				add_sale(cname, pname, quant);
			}break;
			
			case 3:
			{
				System.out.println("Enter Product name");
				pname = input.next();
				remove_product(pname);
			}break;
			
			case 4: 
				{
					products();
				}break;
				
			case 5: 
			{
				bills();
			}break;
			
			default:
			{
				System.out.println("Thank You Fro using the system");
			}break;
			}
		} while(!(option==6));	
		


	}

}
