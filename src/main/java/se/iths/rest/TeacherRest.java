package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Teacher;
import se.iths.exception.NotAcceptableExecption;
import se.iths.exception.NotFoundException;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @GET
    @Path("{id}")
    public Response getTeacher(@PathParam("id") Long id){
        Teacher foundTeacher = teacherService.findTeacherById(id);
        if (foundTeacher == null) {
            throw new se.iths.exception.NotFoundException("NoRecordOfId: " +id); }
        return Response.ok(foundTeacher).build();
    }

    @GET
    @Path("search")
    public Response findByLastName(@QueryParam("lastName") String lastName) {
        List<Teacher> teachers = teacherService.getTeacherByLastName(lastName);
        if (teachers.isEmpty()) {
            throw new se.iths.exception.NotFoundException("NoRecordInDatabaseWithLastname:" +lastName); }
        return Response.ok(teachers).build();
    }

    @GET
    @Path("")
    public Response getAllTeachers(){
        List<Teacher> foundTeachers = teacherService.getAllTeachers();
        if (foundTeachers.isEmpty()) {
            throw new se.iths.exception.NotFoundException("DatabaseIsEmpty");
        } else
            return Response.ok(foundTeachers).build();
    }

    @GET
    @Path("getstudentbysubjectandteacher/{subject}/{teacher}")
    public List<Student> getStudentBySubjectAndTeacher(@PathParam("subject") String subject, @PathParam("teacher") String teacher){
            return teacherService.getSpecifiedStudentsPerSubjectandTeacher(subject,teacher);
    }

    @POST
    @Path("")
    public Response createTeacher(Teacher teacher){
        if (teacher.getFirstName().isEmpty() || teacher.getFirstName().getBytes().length < 2) {
            throw new NotAcceptableExecption("Firstname-Minimum2Characters");
        }
        if (teacher.getLastName().isEmpty() || teacher.getLastName().getBytes().length < 2) {
            throw new se.iths.exception.NotFoundException("Lastname-Minimum2Characters");
        }
        if (teacher.getEmail().isEmpty() || !teacher.getEmail().contains("@")) {
            throw new NotAcceptableExecption("Email-EmailIsRequiredAndWithCharacter@");
        }
        teacherService.createTeacher(teacher);
        return Response.ok(teacher).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("")
    public Response updateTeacher(Teacher teacher){
        if (teacher.getFirstName().isEmpty() || teacher.getFirstName().getBytes().length < 2) {
            throw new NotAcceptableExecption("Firstname-Invalid");
        }
        if (teacher.getLastName().isEmpty() || teacher.getLastName().getBytes().length < 2) {
            throw new NotAcceptableExecption("Lastname-Invalid");
        }
        if (teacher.getEmail().isEmpty() || !teacher.getEmail().contains("@")) {
            throw new NotAcceptableExecption("Email-Invalid");
        }
        teacherService.updateTeacher(teacher);
        return Response.ok(teacher).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTeacher(@PathParam("id") Long id){
        if (teacherService.findTeacherById(id) == null) {
            throw new NotFoundException("Delete-NoRecordWithId: " + id);
        }
        teacherService.deleteTeacher(id);
        return Response.ok().build();
    }
}
