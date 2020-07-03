package test_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test_1_management
{
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/haiyuncafe?useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	 
	 
	    // 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "root";
	static final String PASS = "mysql";
	 static HashMap <String,String>menuMap = new HashMap<String,String>()
	{{

	}};
	static
	{
		menuMap.put("菜名","Meal_name");
		menuMap.put("种类","Type");
		menuMap.put("库存数","Stock");
		menuMap.put("单价","Price");
	}
	public static void insertOnMenu()
	{
        Connection conn = null;
        Statement stmt = null;
        try
        {
            Class.forName(JDBC_DRIVER);
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();         
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入要增加的菜名");
            String dish = scanner.nextLine();
            System.out.println("请输入要增加的种类");
            String type = scanner.nextLine();            
            System.out.println("请输入要增加的库存数量");
            String stockname = scanner.nextLine();            
            System.out.println("请输入要增加的单价");
            String price = scanner.nextLine();
            
            String sql;
            sql = "INSERT INTO menu ( Meal_name,Type,Stock,Price) " + 
            		"VALUES('"+dish+"','"+type+"','"+stockname+"','"+price+"')";
            System.out.println(sql);
            if(stmt.executeUpdate(sql)!=0)
            {
            	System.out.println("新增成功");
            }
            else 
            {
            	System.out.println("新增失败");
            }
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
            
        }
        System.out.println("Goodbye!");
    }

	public static void deleteOnMenu()
	{
        Connection conn = null;
        Statement stmt = null;
        try
        {
            Class.forName(JDBC_DRIVER);
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();         
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入要删除的菜名");
            String dish = scanner.nextLine();

            
            String sql;
            sql = "DELETE FROM menu WHERE  Meal_name = '"+dish+"'";
            System.out.println(sql);
            if(stmt.executeUpdate(sql)!=0)
            {
            	System.out.println("删除成功");
            }
            else 
            {
            	System.out.println("删除失败");
            }
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
            
        }
        System.out.println("Goodbye!");
    }
	
	public static void updateOnMenu()
	{
		Connection conn = null;
        Statement stmt = null;
        try
        {
	        Class.forName(JDBC_DRIVER);
	        System.out.println("连接数据库...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        System.out.println(" 实例化Statement对象...");
	        stmt = conn.createStatement();         
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("请输入要更新的菜名");
	        String dish = scanner.nextLine();
            System.out.println("请输入要更新的种类");
            String type = scanner.nextLine();            
            System.out.println("请输入要更新的库存数量");
            String stockname = scanner.nextLine();            
            System.out.println("请输入要更新的单价");
            String price = scanner.nextLine();
	        
	        String sql;
	        sql = "DELETE FROM menu WHERE  Meal_name = '"+dish+"'";
	        stmt.executeUpdate(sql);
	        System.out.println(sql);
	        sql = "INSERT INTO menu ( Meal_name,Type,Stock,Price) " + 
            		"VALUES('"+dish+"','"+type+"','"+stockname+"','"+price+"')";
            System.out.println(sql);
            if(stmt.executeUpdate(sql)!=0)
            {
            	System.out.println("更新成功");
            }
            else 
            {
            	System.out.println("更新失败");
            }

	      
	        stmt.close();
	        conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
	}
	
	public static void  showOnMenu()
	{
	        Connection conn = null;
	        Statement stmt = null;
	        try{
	            Class.forName(JDBC_DRIVER);
	            System.out.println("连接数据库...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	            System.out.println(" 实例化Statement对象...");
	            stmt = conn.createStatement();
	            String sql;
	            sql = "SELECT * FROM menu;";
	            ResultSet rs = stmt.executeQuery(sql);
	            while(rs.next()){
	                String mealname  = rs.getString("Meal_name");
	                String type= rs.getString("Type");
	                int stock = rs.getInt("Stock");
	                int price = rs.getInt("Price");
	                System.out.print("mealname: " + mealname);
	                System.out.print(", type: " + type);
	                System.out.print(", stock: " + stock);
	                System.out.print(", price: " + price);
	                System.out.print("\n");
	            }
	            rs.close();
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
	        System.out.println("Goodbye!");
	    }
	
	public static void  showColumnOnMenu()
	{
	        Connection conn = null;
	        Statement stmt = null;
	        try{
	            Class.forName(JDBC_DRIVER);
	            System.out.println("连接数据库...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	            System.out.println(" 实例化Statement对象...");
	            stmt = conn.createStatement();
	            String sql;
	            Scanner scanner = new Scanner(System.in);
	            System.out.println("请输入查询属性:菜名 or 种类 or 库存数 or 单价");
	            String column  = scanner.nextLine();
	            if(!(column.contentEquals("菜名")||column.contentEquals("种类")||column.contentEquals("库存数")||column.contentEquals("单价")))
	            {
	            	System.out.println("输入错误,查询失败.");
	            	return ;
	            }
	            System.out.println("请输入查询属性内容:");
	            String atrri_name = scanner.nextLine();
	            sql = "SELECT * FROM menu WHERE "+ menuMap.get(column)+ " = '" +  atrri_name+"'";
	            System.out.println(sql);
	            ResultSet rs = stmt.executeQuery(sql);
	            while(rs.next()){
	                String mealname  = rs.getString("Meal_name");
	                String type= rs.getString("Type");
	                int stock = rs.getInt("Stock");
	                int price = rs.getInt("Price");
	                System.out.print("mealname: " + mealname);
	                System.out.print(", type: " + type);
	                System.out.print(", stock: " + stock);
	                System.out.print(", price: " + price);
	                System.out.print("\n");
	            }
	            rs.close();
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
	        System.out.println("Goodbye!");
	    }
	}

