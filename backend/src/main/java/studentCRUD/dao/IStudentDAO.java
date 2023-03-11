package studentCRUD.dao;

import java.sql.SQLException;
import java.util.List;

import studentCRUD.model.Student;

public interface IStudentDAO {
	
	void insert(Student student) throws SQLException;
	void delete(Student student) throws SQLException;
	void update(Student oldStudent, Student newStudent) throws SQLException;
	List<Student> getStudentsByLastName (String lastName) throws SQLException;
	Student getById(int id) throws SQLException;
}
