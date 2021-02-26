package bolbolestan.userInterface;


import bolbolestan.offering.OfferingEntity;
import bolbolestan.requestHandler.RequestHandler;
import bolbolestan.student.StudentEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class TerminalServer {
    final String ADD_OFFERING_CMD = "addOffering";
    final String ADD_STUDENT_CMD = "addStudent";
    final String GET_OFFERINGS_CMD = "getOfferings";
    final String ADD_TO_WEEKLY_SCHEDULE = "addToWeeklySchedule";
    final String REMOVE_FROM_WEEKLY_SCHEDULE = "removeFromWeeklySchedule";
    final String GET_WEEKLY_SCHEDULE = "getWeeklySchedule";
    final String FINALIZE = "finalize";

    private final ObjectMapper mapper = new ObjectMapper();

    public void runServer() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String command = userInput.substring(0, userInput.indexOf(' '));
        String data = userInput.substring(userInput.indexOf(' ') + 1);

        switch (command) {
            case ADD_OFFERING_CMD: {
                this.addOffering(data);
            } case ADD_STUDENT_CMD: {
                this.addStudent(data);
            } case GET_OFFERINGS_CMD: {
                this.getOfferings(data);
            } case ADD_TO_WEEKLY_SCHEDULE: {
                this.addToWeeklySchedule(data);
            } case REMOVE_FROM_WEEKLY_SCHEDULE: {
                this.removeFromWeeklySchedule(data);
            } case GET_WEEKLY_SCHEDULE: {
                this.getWeeklySchedule(data);
            } case FINALIZE: {
                this.finalizeSchedule(data);
            } default: {
                //todo proper message
            }
        }
    }

    private String getJsonField(String data, String fieldName) {
        try {
            JsonNode jsonNode = mapper.readTree(data);
            return jsonNode.get(fieldName).textValue();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //todo proper excep
        }
        return null;
    }

    private void finalizeSchedule(String data) {
        String studentId = getJsonField(data, "studentId");
        //todo call request handler
    }

    private void getWeeklySchedule(String data) {
        String studentId = getJsonField(data, "studentId");
        //todo call request handler
    }

    private void removeFromWeeklySchedule(String data) {
        String studentId = getJsonField(data, "studentId");
        String offeringCode = getJsonField(data, "code");
        //todo call request handler
    }

    private void addToWeeklySchedule(String data) {
        String studentId = getJsonField(data, "studentId");
        String offeringCode = getJsonField(data, "code");
        //todo call request handler
    }

    private void getOfferings(String data) {
        String studentId = getJsonField(data, "studentId");
        //todo call request handler
    }

    private void addStudent(String data) {
        try {
            StudentEntity studentEntity = mapper.readValue(data, StudentEntity.class);
            //todo call req handler
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // todo: proper exception
        }
    }

    private void addOffering(String data) {
        try {
            OfferingEntity offeringEntity = mapper.readValue(data, OfferingEntity.class);
            //todo: call req handler
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // todo: proper exception
        }
    }

}