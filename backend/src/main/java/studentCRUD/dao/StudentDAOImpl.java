package studentCRUD.dao;

import static studentCRUD.dao.dbutil.DBUtil.openConnection;
import static studentCRUD.dao.dbutil.DBUtil.closeConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import studentCRUD.model.Student;

public class StudentDAOImpl implements IStudentDAO {

	@Override
	public void insert(Student student) throws SQLException {
		PreparedStatement pst = null;
		
		try {
			
			String sql = "INSERT INTO STUDENTS (FIRSTNAME, LASTNAME, DATEOFBIRTH) VALUES (?, ?, ?)";
			pst = openConnection().prepareStatement(sql);
			pst.setString(1,  student.getFirstName());
			pst.setString(2,  student.getLastName());
			pst.setString(3,  student.getDateOfBirth());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (openConnection() != null) closeConnection();
		}
	}

	@Override
	public void delete(Student student) throws SQLException {
		PreparedStatement pst = null;
		
		try {
			String sql = "DELETE FROM STUDENTS WHERE ID =  ?";
			pst = openConnection().prepareStatement(sql);
			pst.setInt(1,  student.getId());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (openConnection() != null) closeConnection();
		}
		
	}

	@Override
	public void update(Student oldStudent, Student newStudent) throws SQLException {
		PreparedStatement pst = null;
		
		try {
			
			String sql = "UPDATE STUDENTS SET FIRSTNAME = '" + newStudent.getFirstName() + "', " + "LASTNAME = '" 
					+ newStudent.getLastName() +"', " + "DATEOFBIRTH = '" + newStudent.getDateOfBirth() + "' WHERE ID = " + oldStudent.getId();
			pst = openConnection().prepareStatement(sql);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (openConnection() != null) closeConnection();
		}
		
	}

	@Override
	public List<Student> getStudentsByLastName(String lastName) throws SQLException {
		PreparedStatement pst = null;
		List<Student> students = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT ID, FIRSTNAME, LASTNAME, DATEOFBIRTH FROM STUDENTS WHERE LASTNAME LIKE '" + lastName + "%'";
			pst = openConnection().prepareStatement(sql);
			rs =  pst.executeQuery();
				
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("ID"));
				student.setFirstName(rs.getString("FIRSTNAME"));
				student.setLastName(rs.getString("LASTNAME"));
				student.setDateOfBirth(rs.getString("DATEOFBIRTH"));
				
				students.add(student);
			}
			
			return (students.size() > 0) ? students : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (openConnection() != null) closeConnection();
		}
	}

	@Override
	public Student getById(int id) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Student student = null;
		
		try {
			
			String sql = "SELECT * FROM STUDENTS WHERE ID = " + id;
			
			pst = openConnection().prepareStatement(sql);
			rs =  pst.executeQuery();
				
			if (rs.next()) {
				student = new Student();
				student.setId(rs.getInt("ID"));
				student.setFirstName(rs.getString("FIRSTNAME"));
				student.setLastName(rs.getString("LASTNAME"));
				student.setDateOfBirth(rs.getString("DATEOFBIRTH"));
			}
		
			return student;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (openConnection() != null) closeConnection();
		}	
	}
	
}
