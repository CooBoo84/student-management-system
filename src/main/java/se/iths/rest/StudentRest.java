package se.iths.rest;

import se.iths.entity.Student;
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
        return Response.ok(foundStudent).build();
    }

    @GET
    @Path("search")
    public Response findByLastName(@QueryParam("lastName") String lastName) {
        List<Student> students = studentService.getStudentByLastName(lastName);
        return Response.ok(students).build();
    }

    @GET
    @Path("")
    public Response getAllStudents(){
        List<Student> foundStudents = studentService.getAllStudents();
        return Response.ok(foundStudents).build();
    }

    @POST
    @Path("")
    public Response createStudent(Student student){
        studentService.createStudent(student);
        return Response.ok(student).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("")
    public Response updateStudent(Student student){
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") Long id){
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}
