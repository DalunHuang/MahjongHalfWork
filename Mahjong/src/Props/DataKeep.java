package Props;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Properties;

public class DataKeep {
	private static LinkedList<Integer> handTileList;
	public static LinkedList<Integer> readList;
	
	
	public DataKeep() {
		
	}
	
	public DataKeep(LinkedList<Integer> handTileList) {
		this.handTileList = handTileList;
	}
	
	
	private static LinkedList<Integer> getThis(){
		return handTileList;
	}
	
	
	public void toDataBase() {
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		
		try (Connection conn = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/eeit48", prop);) {
			
			LinkedList<Integer> handTileList = getThis();
			String sqlInserInto = "INSERT INTO `mahjonlog`(`userid`, `tiles`) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(sqlInserInto);
			ps.setInt(1, 1);
			ps.setObject(2, (Object)handTileList); //物件輸入SQL需要先序列化
			int n = ps.executeUpdate();
//			System.out.println(n);
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	
	public static void DataRead() {
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		
		try (Connection conn = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/eeit48", prop);) {
			
			LinkedList<Integer> handTileList = getThis();
			String sqlSelect = "SELECT * FROM mahjonlog WHERE userid = 1 ORDER BY id DESC";
			PreparedStatement ps = conn.prepareStatement(sqlSelect);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			InputStream in2 = rs.getBinaryStream("tiles");
			
//			System.out.println("ok");
			
			ObjectInputStream oin = new ObjectInputStream(in2);
			Object obj2 = oin.readObject();
			readList = (LinkedList<Integer>)obj2;
//			System.out.println("succese");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	public LinkedList<Integer> getread() {
		return readList;
	}

	
	public static void main(String[] args) {
//		DataKeep d = new DataKeep();
//		d.DataRead();
//		System.out.println(d.getread().get(0));
	}
	

}


