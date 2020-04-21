package skademy;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="CourseRegistrationSystem_table")
public class CourseRegistrationSystem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long lectureId;
    private Integer studentId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        CourseRegistered courseRegistered = new CourseRegistered();
        BeanUtils.copyProperties(this, courseRegistered);
        courseRegistered.publish();

        this.setLectureId(courseRegistered.getLectureId());
        this.setStudentId(12334);
        this.setStatus("수강신청중");

        System.out.println("##### POST CourseRegistrationSystem 수강신청 : " + this);

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.


        PaymentSystem paymentSystem = new PaymentSystem();
        paymentSystem.setCourseId(this.id);
        // mappings goes here

        //결제 시작
        PaymentService paymentService = Application.applicationContext.getBean(PaymentService.class);
        paymentService.makePayment(paymentSystem);

    }

    @PostUpdate
    public void onPostUpdate(){
        CourseRegistrationCompleted courseRegistrationCompleted = new CourseRegistrationCompleted();
        BeanUtils.copyProperties(this, courseRegistrationCompleted);
        courseRegistrationCompleted.publish();


    }

    @PostRemove
    public void onPostRemove(){
        CourseCanceled courseCanceled = new CourseCanceled();
        BeanUtils.copyProperties(this, courseCanceled);
        courseCanceled.publish();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
