# Simple restful API based on lib. javax.ws.rs

Java eclispse EE

Tomcat apache v9

Jersey v2.25.1

MySQL workbench

Using a model Student and a data transfer object (DTO) StudentDTO
For data access object (DAO) created an interface for future scale-ability and easier maintance IStudentDAO and its implementation StudentDAOImpl. 

For database connection used jdbs driver and DBUtil for opening and closing connection also in the DAO package.

The a service layer which implements DAO classes and then the controller listening for HTTP requests, in web.xml I connect the servlet to the web server.

Using Postman sending a POST Method to the URL create a new student, waiting to consume application/x-www-form-urlencoded. Keys and Values as seen in the picture.
![POST method creating a student](https://github.com/NickGiag/Java-simple-restAPI/blob/main/screenshots/POST%20createStudent.png)

GET method path/{id} to retrieve the new student.
![GET method to get a student](https://github.com/NickGiag/Java-simple-restAPI/blob/main/screenshots/GET%20getStudent.png)

PUT method path/{id} to update the student with this {id}.
![PUT method to update student with {id}](https://github.com/NickGiag/Java-simple-restAPI/blob/main/screenshots/PUT%20updateStudent.png)

GET method to see the changes after the PUT method:
![changes to the student after PUT method](https://github.com/NickGiag/Java-simple-restAPI/blob/main/screenshots/after%20PUT%20method.png)

DELETE method to delete the student from database.
![DELETE method to delete student](https://github.com/NickGiag/Java-simple-restAPI/blob/main/screenshots/DELETE%20deleteStudent.png)

And lastly another GET method to see an 404 error as the student with that ID is NOT FOUND.
![GET student not found](https://github.com/NickGiag/Java-simple-restAPI/blob/main/screenshots/after%20DELETE%20method.png)



