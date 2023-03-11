package studentCRUD.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import studentCRUD.dao.IStudentDAO;
import studentCRUD.dao.StudentDAOImpl;
import studentCRUD.dto.StudentDTO;
import studentCRUD.model.Student;
import studentCRUD.service.IStudentService;
import studentCRUD.service.StudentServiceImpl;

@Path("/student")
public class studentRestController {
	
	IStudentDAO studentDAO = new StudentDAOImpl();
	IStudentService studentService = new StudentServiceImpl(studentDAO);
	
	//GET ALL
	//**** TODO
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudent(@PathParam("id") int id) throws SQLException, JsonProcessingException {
		
		try {
			Student student = studentService.getStudentById(id);	
			if (student != null) {
				ObjectMapper objectMapper = new ObjectMapper(); 
	            String json = objectMapper.writeValueAsString(student);
				return Response.ok(json).build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND).build();
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createStudent(@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName, 
			@FormParam("dateOfBirth")String dateOfBirth) {
		
		try {
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setFirstName(firstName);
			studentDTO.setLastName(lastName);
			studentDTO.setDateOfBirth(dateOfBirth);
		
			
			studentService.insertStudent(studentDTO);
			return Response.status(Response.Status.CREATED).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStudent(@PathParam("id") int id,
			@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName, 
			@FormParam("dateOfBirth")String dateOfBirth) {
		
		try {
			StudentDTO oldStudentDTO = new StudentDTO();
			oldStudentDTO.setId(id);
			
			StudentDTO newStudentDTO = new StudentDTO();
			newStudentDTO.setFirstName(firstName);
			newStudentDTO.setLastName(lastName);
			newStudentDTO.setDateOfBirth(dateOfBirth);
			
			studentService.updateStudent(oldStudentDTO, newStudentDTO);
			return Response.status(Response.Status.OK).build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}	
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStudent(@PathParam("id") int id) {
		
		try {
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setId(id);
			
			studentService.deleteStudent(studentDTO);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		
		
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@FormDataParam("file") InputStream fis, @FormDataParam("file") FormDataContentDisposition fdcd) {
		
		final String FOLDER_PATH = "C:/my_files/";
		
		OutputStream os = null;
		String fileName = fdcd.getFileName();
		System.out.println("File Name: " + fdcd.getFileName());
        String filePath = FOLDER_PATH + fileName;
        
        try {
        	int read = 0;
            byte[] bytes = new byte[1024];
            os = new FileOutputStream(new File(filePath));
            
            while ((read = fis.read(bytes)) != -1) {
            	os.write(bytes, 0, read);
            }
            
            os.flush();
            os.close();
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (IOException e) {
        	e.printStackTrace();
        	return Response.status(Response.Status.BAD_REQUEST).build(); 
        } finally {
            if(os != null){
                try{os.close();} catch(Exception ex){}
            }
        }
        
        
	}
}
