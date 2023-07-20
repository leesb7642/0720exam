package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import inter.PhonebookInter;
import vo.PhonebookVO;

@Repository
public class PhonbookDAO implements PhonebookInter {
	PreparedStatement pstmt;
	Connection conn;
	public PhonbookDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test", "1111");
			}catch(Exception e) {
				e.printStackTrace();
			}
			}
	
	@Override
	public int insert(PhonebookVO phonebook) {
		try {
			String sql = "insert into phonebook values(phonebook_seq_idx.nextval,?,?,?)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,phonebook.getName());
			pstmt.setString(2,phonebook.getHp());
			pstmt.setString(3,phonebook.getMemo());
			int result = pstmt.executeUpdate();
			pstmt.close();
			
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<PhonebookVO> findAll() {
		try {
			String sql="select * from phonebook order by idx asc";
		    pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			List<PhonebookVO> list=new ArrayList<PhonebookVO>();
			while(rs.next()) {
				int idx = rs.getInt("idx");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String memo = rs.getString("memo");
				list.add(new PhonebookVO(idx,name,hp,memo));
			}
			rs.close();
			pstmt.close();
			return list;
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<PhonebookVO> search(String keyword) {
		try {
			String sql = "select * from phonebook where name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
			ResultSet rs = pstmt.executeQuery();
						
			List<PhonebookVO> list=new ArrayList<PhonebookVO>();
			while(rs.next()) {
				int idx = rs.getInt("idx");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String memo = rs.getString("memo");
				list.add(new PhonebookVO(idx,name,hp,memo));
			}
			rs.close();
			pstmt.close();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PhonebookVO findOne(int idx) {
		try {
			pstmt = conn.prepareStatement("select * from phonebook where idx=?");
			pstmt.setInt(1, idx);
			ResultSet rs = pstmt.executeQuery();
			PhonebookVO phonebook = null;
			if(rs.next()) {
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String memo = rs.getString("memo");
				idx = rs.getInt("idx");
				phonebook = new PhonebookVO(idx,name,hp,memo);
				
				return phonebook;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(int idx, PhonebookVO phonebook) {
		try {
			String sql = "update phonebook set name=?, hp=?, memo=? where idx=? "; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phonebook.getName());
			pstmt.setString(2, phonebook.getHp());
			pstmt.setString(3, phonebook.getMemo());
			pstmt.setInt(4, phonebook.getIdx());
			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
			}catch(Exception e) {
				e.printStackTrace();
			}
		return 0;
		}
	@Override
	public int delete(int idx) {
		try {
			String sql = "delete from phonebook where idx=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
			}catch(Exception e) {
				e.printStackTrace();
			}
		return 0;
		}

}
