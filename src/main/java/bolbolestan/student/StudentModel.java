package bolbolestan.student;

import bolbolestan.bolbolestanExceptions.OfferingNotFoundException;
import bolbolestan.bolbolestanExceptions.StudentNotFoundException;
import bolbolestan.offering.OfferingEntity;
import bolbolestan.offering.OfferingModel;
import bolbolestan.offeringRecord.OfferingRecordEntity;
import bolbolestan.offeringRecord.OfferingRecordModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentModel {
    public StudentEntity getStudent(String studentId) throws StudentNotFoundException {
        return StudentStorage.getById(studentId);
    }

    public void addNewStudent(StudentEntity studentEntity) {
        //todo: validate data
        StudentStorage.add(studentEntity);
    }

    public Map<String, Double> getGpaTpu(String studentId) throws OfferingNotFoundException {
        Map<String, Double> unitsGpa = new HashMap<>();
        List<OfferingRecordEntity> offeringRecordEntityList =
                new OfferingRecordModel().getStudentOfferingRecords(studentId);
        double studentGPA = 0.0;
        double unitsSum = 0.0;
        for (OfferingRecordEntity offeringRecordEntity: offeringRecordEntityList) {
            OfferingEntity offeringEntity = new OfferingModel().getOffering(offeringRecordEntity.getOfferingCode());
            studentGPA += offeringRecordEntity.getGrade() * offeringEntity.getUnits();
            unitsSum += offeringEntity.getUnits();
        }
        unitsGpa.put("gpa", studentGPA / unitsSum);
        unitsGpa.put("tpu", unitsSum);
        return unitsGpa;
    }

    public List<OfferingRecordEntity> getStudentPassedCourses(String studentId) {
        List<OfferingRecordEntity> offeringRecordEntityList =
                new OfferingRecordModel().getStudentOfferingRecords(studentId);
        List<OfferingRecordEntity> studentPassedCourses = new ArrayList<>();
        for (OfferingRecordEntity offeringRecordEntity: offeringRecordEntityList) {
            if (offeringRecordEntity.getStatus().equals(OfferingRecordEntity.COMPLETED_STATUS) &&
                    offeringRecordEntity.getGrade() >= 10) {
                studentPassedCourses.add(offeringRecordEntity);
            }
        }
        return studentPassedCourses;
    }
}
