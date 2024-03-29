package bolbolestan.requestHandler;

import bolbolestan.bolbolestanExceptions.OfferingCodeNotInWeeklyScheduleException;
import bolbolestan.bolbolestanExceptions.OfferingNotFoundException;
import bolbolestan.bolbolestanExceptions.StudentNotFoundException;
import bolbolestan.offering.OfferingEntity;
import bolbolestan.offering.OfferingModel;
import bolbolestan.student.StudentEntity;
import bolbolestan.student.StudentModel;
import bolbolestan.tools.GetExceptionMessages;
import bolbolestan.weeklySchedule.WeeklyScheduleEntity;
import bolbolestan.weeklySchedule.WeeklyScheduleModel;

import java.util.HashMap;
import java.util.List;

public class RequestHandler {
    public HashMap<String, Object> finalizeSchedule(String studentId) {
        List<Exception> exceptionList;
        try {
            exceptionList = new WeeklyScheduleModel().finalizeWeeklySchedule(studentId);
            if (exceptionList.isEmpty()) {
                return makeSuccessResponse(null);
            } else {
                return makeErrorResponse(
                        GetExceptionMessages.getExceptionMessages(exceptionList)
                );
            }
        } catch (StudentNotFoundException e) {
            return makeErrorResponse(e.getMessage());
        }
    }

    public HashMap<String, Object> getWeeklySchedule(String studentId) {
        try {
            WeeklyScheduleEntity weeklyScheduleEntity =
                    new WeeklyScheduleModel().getWeeklySchedule(studentId);
            return makeSuccessResponse(weeklyScheduleEntity);
        } catch (StudentNotFoundException e) {
            return makeErrorResponse(e.getMessage());
        }
    }

    public HashMap<String, Object> removeFromWeeklySchedule(String studentId, String offeringCode) {
        try {
            new WeeklyScheduleModel().removeFromWeeklySchedule(studentId, offeringCode);
            return makeSuccessResponse(null);
        } catch (StudentNotFoundException | OfferingCodeNotInWeeklyScheduleException e) {
            return makeErrorResponse(e.getMessage());
        }
    }

    public HashMap<String, Object> addToWeeklySchedule(String studentId, String offeringCode) {
        try {
            new WeeklyScheduleModel().addToWeeklySchedule(studentId, offeringCode);
            return makeSuccessResponse(null);
        } catch (StudentNotFoundException | OfferingNotFoundException e) {
            return makeErrorResponse(e.getMessage());
        }
    }

    public HashMap<String, Object> getOffering(String studentId, String offeringCode) {
        try {
            new StudentModel().getStudent(studentId);
            OfferingEntity offeringEntity = new OfferingModel().getOffering(offeringCode);
            return makeSuccessResponse(offeringEntity);
        } catch (OfferingNotFoundException | StudentNotFoundException e) {
            return makeErrorResponse(e.getMessage());
        }
    }

    public HashMap<String, Object> getOfferings(String studentId) {
        try {
            new StudentModel().getStudent(studentId);
            List<OfferingEntity> offeringEntities = new OfferingModel().getOfferings();
            return makeSuccessResponse(offeringEntities);
        } catch (StudentNotFoundException e) {
            return makeErrorResponse(e.getMessage());
        }
    }

    public HashMap<String, Object> addStudent(StudentEntity studentEntity) {
        new StudentModel().addNewStudent(studentEntity);
        return makeSuccessResponse(null);
    }

    public HashMap<String, Object> addOffering(OfferingEntity offeringEntity) {
        new OfferingModel().addNewOffering(offeringEntity);
        return makeSuccessResponse(null);
    }

    private HashMap<String, Object> makeSuccessResponse(Object data) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", data);
        return response;
    }

    private HashMap<String, Object> makeErrorResponse(Object data) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", data);
        return response;
    }

}
