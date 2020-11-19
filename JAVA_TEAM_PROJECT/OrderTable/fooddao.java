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
			System.out.println("클래스가 발견되지 않습니다(jar 파일 누락)"); 
			e.printStackTrace();		
		}
	}

	private Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password) ;
		} catch (SQLException e) {
			System.out.println("커넥션 생성 오류");
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

	public int foodadd(Food bean){//콘솔창에서 데이터를 입력받아 객체 생성
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
	
	
	
	
	
	
	public  Vector<Food> Getsellcount(){// 오늘 판매된 음식
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
	
	
	
	
	
	public Vector<Food> GetAllSellList() {//db에서 데이터를 받아서 벡터로 반환하는 메소드
		//모든 상품 목록들을 리턴한다.
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
		
	
	
	public Object[][] makeArr(Vector<Food> lists){//벡터를 받아서 전체를 2차원 배열로 만들어주는 메소드
		
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
	
	
	
	public Object[][] makelistArr(Vector<Food> lists){//벡터를 받아서 판대량을 2차원 배열로 만들어주는 메소드
		
		Object [][] foodarr = new Object [lists.size()][2]; 
				
				
			for(int i=0; i<lists.size();i++){
				foodarr[i][0]=lists.get(i).getName();
				foodarr[i][1]=lists.get(i).getPrice();
			}	
		
			
		return foodarr;
		
	}//makeArr

	
}