package bolbolestan.offering;

import bolbolestan.course.CourseEntity;
import bolbolestan.course.DaysOfWeek;

import java.util.Date;
import java.util.List;

public class OfferingEntity extends CourseEntity {
    private String code;
    private String instructor;
    private List<DaysOfWeek> classTimeDays;
    private Date classTimeStart;
    private Date classTimeEnd;
    private Integer registered = 0;

    public OfferingEntity() {}

    public OfferingEntity(
            CourseEntity courseEntity,
            String code,
            String instructor,
            List<DaysOfWeek> classTimeDays,
            Date classTimeStart,
            Date classTimeEnd,
            Integer registered) {
        super(courseEntity);
        this.code = code;
        this.instructor = instructor;
        this.classTimeDays = classTimeDays;
        this.classTimeStart = classTimeStart;
        this.classTimeEnd = classTimeEnd;
        this.registered = registered;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public List<DaysOfWeek> getClassTimeDays() {
        return classTimeDays;
    }

    public void setClassTimeDays(List<DaysOfWeek> classTimeDays) {
        this.classTimeDays = classTimeDays;
    }

    public void setClassTimeStart(Date classTimeStart) {
        this.classTimeStart = classTimeStart;
    }

    public Date getClassTimeStart() {
        return classTimeStart;
    }

    public void setClassTimeEnd(Date classTimeEnd) {
        this.classTimeEnd = classTimeEnd;
    }

    public Date getClassTimeEnd() {
        return classTimeEnd;
    }

    public void setRegistered(Integer registered) {
        this.registered = registered;
    }

    public Integer getRegistered() {
        return registered;
    }
}
