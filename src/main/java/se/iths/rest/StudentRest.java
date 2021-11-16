package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.NotAcceptableExecption;
import se.iths.exception.NotFoundException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @GET
    @Path("{id}")
    public Response getStudent(@PathParam("id") Long id){
        Student foundStudent = studentService.findStudentById(id);
        if (foundStudent == null) {
            throw new NotFoundException("NoRecordOfId: " +id); }
        return Response.ok(foundStudent).build();
    }

    @GET
    @Path("search")
    public Response findByLastName(@QueryParam("lastName") String lastName) {
        List<Student> students = studentService.getStudentByLastName(lastName);
        if (students.isEmpty()) {
            throw new NotFoundException("NoRecordInDatabaseWithLastname:" +lastName); }
        return Response.ok(students).build();
    }

    @GET
    @Path("")
    public Response getAllStudents(){
        List<Student> foundStudents = studentService.getAllStudents();
        if (foundStudents.isEmpty()) {
            throw new NotFoundException("DatabaseIsEmpty");
        } else
            return Response.ok(foundStudents).build();
    }

    @POST
    @Path("")
    public Response createStudent(Student student){
        if (student.getFirstName().isEmpty() || student.getFirstName().getBytes().length < 2) {
            throw new NotAcceptableExecption("Firstname-Minimum2Characters");
        }

        if (student.getLastName().isEmpty() || student.getLastName().getBytes().length < 2) {
            throw new NotFoundException("Lastname-Minimum2Characters");
        }

        if (student.getEmail().isEmpty() || !student.getEmail().contains("@")) {
            throw new NotAcceptableExecption("Email-EmailIsRequiredAndWithCharacter@");
        }
        studentService.createStudent(student);
        return Response.ok(student).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("")
    public Response updateStudent(Student student){
        if (student.getFirstName().isEmpty() || student.getFirstName().getBytes().length < 2) {
            throw new NotAcceptableExecption("Firstname-Invalid");
        }
        if (student.getLastName().isEmpty() || student.getLastName().getBytes().length < 2) {
            throw new NotAcceptableExecption("Lastname-Invalid");
        }
        if (student.getEmail().isEmpty() || !student.getEmail().contains("@")) {
            throw new NotAcceptableExecption("Email-Invalid");
        }
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") Long id){
        if (studentService.findStudentById(id) == null) {
            throw new NotFoundException("Delete-NoRecordWithId: " + id);
        }
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}
