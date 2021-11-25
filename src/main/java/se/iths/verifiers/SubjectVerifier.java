package se.iths.verifiers;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class SubjectVerifier {

    public void verifySubject(Subject subject) {
    }

    public List<Subject> list_subjectsCheck(List<Subject> subjects, String message) {
        List<Subject> subjectslists = subjects;
        if (subjectslists.size() > 0)
            return subjectslists;
        else
            throw new NotFoundException(message);
    }

    public Response SubjectExist(Subject foundsubject, SubjectService subjectService) {
        if (!(foundsubject == null)) {
            long removed = foundsubject.getId();
            subjectService.deleteSubject(foundsubject.getId());
            return Response.ok().entity("Subject with ID " + removed + " removed from registry").type(MediaType.TEXT_PLAIN_TYPE).build();
        } else {
            throw new NotFoundException("Subject attempting to delete is not registered");
        }

    }

    public Subject SubjectExist(Subject foundsubject, Long id) {
        if (foundsubject != null) {
            return foundsubject;
        } else {
            throw new NotFoundException("Not subject found with id " + id);
        }
    }
}
