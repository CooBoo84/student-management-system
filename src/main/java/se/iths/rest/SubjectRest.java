package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exception.NotAcceptableExecption;
import se.iths.exception.NotFoundException;
import se.iths.service.SubjectService;
import se.iths.verifiers.SubjectVerifier;

import javax.inject.Inject;
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

    @Inject
    SubjectVerifier subjectVerifier;

    @GET
    @Path("{id}")
    public Response getSubject(@PathParam("id") Long id){
        Subject foundSubject = subjectService.findSubjectById(id);
        if (foundSubject == null) {
            throw new NotFoundException("NoRecordOfId: " +id); }
        return Response.ok(foundSubject).build();
    }

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

    @PUT
    @Path("")
    public Response updateSubject(Subject subject){
        if (subject.getName().isEmpty()) {
            throw new NotAcceptableExecption("Firstname-Invalid");
        }
        subjectService.updateSubject(subject);
        return Response.ok(subject).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteSubject(@PathParam("id") Long id){
        if (subjectService.findSubjectById(id) == null) {
            throw new NotFoundException("Delete-NoRecordWithId: " + id);
        }
        return subjectVerifier.SubjectExist(subjectService.findSubjectById(id), subjectService);
    }
}
