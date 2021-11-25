package se.iths.verifiers;

import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class TeacherVerifier {

    public List<Teacher> list_teachersCheck(List<Teacher> teacherList, String message) {
        if (teacherList.size() > 0)
            return teacherList;
        else
            throw new NotFoundException(message);
    }

    public Boolean verifyThatTeacherAndSubjectExists(Boolean teacher, Boolean subject){
        Boolean bothExists = null;
        if(teacher && subject)
            bothExists = true;
        if (!teacher && subject){
            bothExists = false;
        }
        if (!subject && teacher){
            bothExists = false;
        }
        else if (!subject && !teacher) {
            bothExists = false;
        }
        return bothExists;
    }

    public Response TeacherExist(Teacher foundTeacher, TeacherService teacherService) {
        if (!(foundTeacher == null)) {
            long removed = foundTeacher.getId();
            teacherService.deleteTeacher(foundTeacher.getId());
            return Response.ok().entity("Teacher with ID " + removed + " removed from registry").type(MediaType.TEXT_PLAIN_TYPE).build();
        } else {
            throw new NotFoundException("Teacher attempting to delete is not registered");
        }
    }
}
