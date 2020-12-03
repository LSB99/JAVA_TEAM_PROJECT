package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


import bean.Food;


public class fooddao {
	private String driver = "com.mysql.cj.jdbc.Driver" ;
	private String url = "jdbc:mysql://localhost/order2?characterEncoding=UTF-8&serverTimezone=UTC" ;
	
	private String username = "root" ;
	private String password = "root" ;
	private Connection conn = null ;
	
	public fooddao() {
		try {
			Class.forName(driver) ;			
		} catch (ClassNotFoundException e) {
			System.out.println("jar 파일 확인"); 
			e.printStackTrace();		
		}
	}

	private Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password) ;
			conn.setAutoCommit( false );
		} catch (SQLException e) {
			System.out.println("connection 오류");
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
		String sql = "insert into food(foodid,foodname,foodamount,pay,price)values(?,?,?,?,?)";
	
		try {
								
			conn = getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getId());
			pstmt.setString(2, bean.getName());
			pstmt.setInt(3, bean.getAmount());
			pstmt.setString(4, bean.getPay());
			pstmt.setInt(5, bean.getPrice());
			//	pstmt.setString(5, bean.getSize());
						
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
	} // addfood 끝
	
	public void resetfood() {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "truncate table food" ;
		
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 
		}
		 catch (Exception e) {
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
			
	}
	
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
				food.setId(rs.getInt("foodid"));
				food.setName(rs.getString("foodname"));
				food.setAmount(rs.getInt("foodamount"));
			//	food.setSize(rs.getString("size"));
				food.setPay(rs.getString("pay"));
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
	}//GetAllSellList 끝
		
	
	
	public Object[][] makeArr(Vector<Food> lists){//벡터를 받아서 전체를 2차원 배열로 만들어주는 메소드, 주문내역용
		
		Object [][] foodarr = new Object [lists.size()][5]; 
				
				
			for(int i=0; i<lists.size();i++){
				foodarr[i][0]=lists.get(i).getId();
				foodarr[i][1]=lists.get(i).getName();
				foodarr[i][2]=lists.get(i).getAmount();
				foodarr[i][3]=lists.get(i).getPay();
				foodarr[i][4]=lists.get(i).getPrice()*lists.get(i).getAmount();
				//	foodarr[i][4]=lists.get(i).getSize();
			}	
		
			
		return foodarr;
		
	}//makeArr
	
	
	
	public Object[][] makelistArr(Vector<Food> lists){//벡터를 받아서 판매량을 2차원 배열로 만들어주는 메소드, 각 음식별 판매갯수 확인용
		
		Object [][] foodarr = new Object [lists.size()][2]; 
				
				
			for(int i=0; i<lists.size();i++){
				foodarr[i][0]=lists.get(i).getName();
				foodarr[i][1]=lists.get(i).getPrice();
			}	
		
			
		return foodarr;
		
	}// makeArr

}