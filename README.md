# Simple restful API based on lib. javax.ws.rs

Java eclispse EE

Tomcat apache v9

Jersey v2.25.1

MySQL workbench

Using a model Student and a data transfer object (DTO) StudentDTO
For data access object (DAO) created an interface for future scale-ability and easier maintance IStudentDAO and its implementation StudentDAOImpl. 

For database connection used jdbs driver and DBUtil for opening and closing connection also in the DAO package.

The a service layer which implements DAO classes and then the controller listening for HTTP requests, in web.xml I connect the servlet to the web server.

![POST method creating a student](https://github.com/NickGiag/Java-simple-restAPI/blob/main/screenshots/POST%20createStudent.png)



