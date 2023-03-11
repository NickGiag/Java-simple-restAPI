package studentCRUD.service;

import java.sql.SQLException;
import java.util.List;

import studentCRUD.dto.StudentDTO;
import studentCRUD.model.Student;

public interface IStudentService {
	
	void insertStudent(StudentDTO studentDTO) throws SQLException;
	void deleteStudent(StudentDTO studentDTO) throws SQLException;
	void updateStudent(StudentDTO oldStudentDTO, StudentDTO newStudentDTO) throws SQLException;
	List<Student> getStudentsByLastName(String lastName) throws SQLException;
	Student getStudentById(int id) throws SQLException;
}
