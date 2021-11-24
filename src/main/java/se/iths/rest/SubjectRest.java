package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exception.NotAcceptableExecption;
import se.iths.exception.NotFoundException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @GET
    @Path("search")
    public Response getSubjectStudentsTeacherBySubjectName(@QueryParam("name") String name) {
        List<Subject> foundSubjects = subjectService.getSubjectStudentsTeacherBySubjectName(name);
        if(foundSubjects.isEmpty()) {
            throw new NotFoundException("NoRecordInDatabaseWithSubjectName:" +name); }
        return Response.ok(foundSubjects).build();
    }

    @GET
    @Path("")
    public Response getAllSubjects() {
        List<Subject> foundSubjects = subjectService.getAllSubjects();
        if (foundSubjects.isEmpty()) {
            throw new se.iths.exception.NotFoundException("DatabaseIsEmpty");
        } else
            return Response.ok(foundSubjects).build();
    }

    @POST
    @Path("")
    public Response createNewSubject(Subject subject){
        if (subject.getName().isEmpty()) {
            throw new NotAcceptableExecption("NameNeedsToBeFilledIn");
        }
        subjectService.createNewSubject(subject);
        return Response.ok(subject).build();
    }
}
