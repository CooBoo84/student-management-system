package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subject")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @GET
    @Path("{subjectName}")
    public List<Subject> getSubjectByName(@PathParam("subjectName") String category) {
        List<Subject> foundSubjects = subjectService.findSubjectByName(category);
        if( !foundSubjects.isEmpty()) {
            return subjectService.findSubjectByName(category);
        } else {
            throw new NotFoundException("Cant find subject");
        }
    }

    @GET
    @Path("getStudentsBySubjectName/{name}")
    public List<Student> getStudentsPerSubject(@PathParam("name") String name){
        try{
            return subjectService.findStudentsBySubject(name);}
        catch(NoResultException e){
            throw new NotFoundException("Not found matches to given subject. Make sure you spell subject's name right and that subject is registered");
        }
    }

    @GET
    @Path("getallsubjects")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @POST
    @Path("create")
    public Response createNewSubject(Subject subject){
        subjectService.createNewSubject(subject);
        return Response.ok(subject).build();
    }
}
