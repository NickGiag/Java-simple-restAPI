package studentCRUD.service;

import java.sql.SQLException;
import java.util.List;

import studentCRUD.dao.IStudentDAO;
import studentCRUD.dto.StudentDTO;
import studentCRUD.model.Student;

public class StudentServiceImpl implements IStudentService {

	private final IStudentDAO studentDAO;
	
	public StudentServiceImpl(IStudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	@Override
	public void insertStudent(StudentDTO studentDTO) throws SQLException {
		Student student = new Student();
		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setDateOfBirth(studentDTO.getDateOfBirth());
		
		try {
			studentDAO.insert(student); 
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void deleteStudent(StudentDTO studentDTO) throws SQLException {
		Student studentToDelete = new Student();
		studentToDelete.setId(studentDTO.getId());

		try {
			studentDAO.delete(studentToDelete);
		} catch (SQLException e) { 
			throw e;
		}
		
	}

	@Override
	public void updateStudent(StudentDTO oldStudentDTO, StudentDTO newStudentDTO) throws SQLException {
		Student studentToUpdate = new Student();
		studentToUpdate.setId(oldStudentDTO.getId());
		
		Student newStudent = new Student();
		newStudent.setFirstName(newStudentDTO.getFirstName());
		newStudent.setLastName(newStudentDTO.getLastName());
		newStudent.setDateOfBirth(newStudentDTO.getDateOfBirth());
		
		try {
			studentDAO.update(studentToUpdate, newStudent);
		} catch (SQLException e) { 
			throw e;
		}
		
	}

	@Override
	public List<Student> getStudentsByLastName(String lastName) throws SQLException {
		try {
			return studentDAO.getStudentsByLastName(lastName);
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public Student getStudentById(int id) throws SQLException {
		try {
			return studentDAO.getById(id);
		} catch (SQLException e) {
			throw e;
		}
	}
	
}
