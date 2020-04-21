package skademy;

import org.springframework.beans.BeanUtils;

import javax.persistence.*;

public class PaymentSystem {

    private Long id;
    private Long courseId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }




}
