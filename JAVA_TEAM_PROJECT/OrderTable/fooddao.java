package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


import bean.Food;


public class fooddao {
	private String driver = "oracle.jdbc.driver.OracleDriver" ;
	private String url = "jdbc:oracle:thin:@localhost:8088:xe" ;
	private String username = "root" ;
	private String password = "root" ;
	private Connection conn = null ;
	
	public fooddao() {
		try {
			Class.forName(driver) ;			
		} catch (ClassNotFoundException e) {
			System.out.println("Ŭ������ �߰ߵ��� �ʽ��ϴ�(jar ���� ����)"); 
			e.printStackTrace();		
		}
	}

	private Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password) ;
		} catch (SQLException e) {
			System.out.println("Ŀ�ؼ� ���� ����");
			e.printStackTrace();
		}
		return conn ;
	}


	private void closeConnection() {
		try {
			if(conn != null) {conn.close(); }
		} catch (Exception e2) {
			e2.printStackTrace(); 
		}		
	}

	public int foodadd(Food bean){//�ܼ�â���� �����͸� �Է¹޾� ��ü ����
		int result =-1;
		
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql = "insert into food(foodname,foodamount,size,foodamount,price)values(?,?,?,?,?)";
	
		try {
								
			conn = getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getAmount());
		//	pstmt.setString(3, bean.getSize());
			pstmt.setString(4, bean.getPay());
			pstmt.setInt(5, bean.getPrice());
						
			result = pstmt.executeUpdate();
			conn.commit();	
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();  
			}
		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		
		return result;
	}//foodadd
	
	
	
	
	
	
	public  Vector<Food> Getsellcount(){// ���� �Ǹŵ� ����
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select foodname , count(*)  from food group by  foodname   order by count(*) desc" ;
		Vector<Food> lists = new Vector<Food>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 
			
			rs = pstmt.executeQuery() ;
			
			while(rs.next()){
				Food food = new Food() ;
				food.setName(rs.getString("foodname"));
				food.setPrice( rs.getInt("count(*)") ); 
				
				lists.add( food ) ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//Getsellcount
	
	
	
	
	
	public Vector<Food> GetAllSellList() {//db���� �����͸� �޾Ƽ� ���ͷ� ��ȯ�ϴ� �޼ҵ�
		//��� ��ǰ ��ϵ��� �����Ѵ�.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from food" ;
		Vector<Food> lists = new Vector<Food>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 
			
			rs = pstmt.executeQuery() ;
			
			while(rs.next()){
				Food food = new Food() ;
				food.setName(rs.getString("foodname"));
				food.setAmount(rs.getInt("foodamount"));
			//	food.setSize(rs.getString("size"));
				food.setPay(rs.getString("foodamount"));
				food.setPrice( rs.getInt("price") ); 
				
				lists.add( food ) ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//GetAllSellList
		
	
	
	public Object[][] makeArr(Vector<Food> lists){//���͸� �޾Ƽ� ��ü�� 2���� �迭�� ������ִ� �޼ҵ�
		
		Object [][] foodarr = new Object [lists.size()][5]; 
				
				
			for(int i=0; i<lists.size();i++){
				foodarr[i][0]=lists.get(i).getName();
				foodarr[i][1]=lists.get(i).getAmount();
			//	foodarr[i][2]=lists.get(i).getSize();
				foodarr[i][3]=lists.get(i).getPay();
				foodarr[i][4]=lists.get(i).getPrice();
			}	
		
			
		return foodarr;
		
	}//makeArr
	
	
	
	public Object[][] makelistArr(Vector<Food> lists){//���͸� �޾Ƽ� �Ǵ뷮�� 2���� �迭�� ������ִ� �޼ҵ�
		
		Object [][] foodarr = new Object [lists.size()][2]; 
				
				
			for(int i=0; i<lists.size();i++){
				foodarr[i][0]=lists.get(i).getName();
				foodarr[i][1]=lists.get(i).getPrice();
			}	
		
			
		return foodarr;
		
	}//makeArr

	
}