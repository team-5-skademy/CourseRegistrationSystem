package skademy;

import skademy.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired
    CourseRegistrationSystemRepository courseRegistrationSystemRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCompleted_수강신청완료(@Payload PaymentCompleted paymentCompleted){
        try {
            if (paymentCompleted.isMe()) {
                System.out.println("##### listener 수강신청완료 : " + paymentCompleted.toJson());
                System.out.println("1 ##" + paymentCompleted.getCourseId() + "##");
                Optional<CourseRegistrationSystem> courseRegistrationSystemOptional = courseRegistrationSystemRepository.findById(paymentCompleted.getCourseId());
                CourseRegistrationSystem courseRegistrationSystem = courseRegistrationSystemOptional.get();
                System.out.println("2 ##" + courseRegistrationSystem + "##");
                courseRegistrationSystem.setStatus("결제 완료");

                courseRegistrationSystemRepository.save(courseRegistrationSystem);


            }
        }catch(Exception e) {

        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOpenedCanceled_수강신청취소(@Payload OpenedCanceled openedCanceled){

        if(openedCanceled.isMe()){
            System.out.println("##### listener 수강신청취소 : " + openedCanceled.toJson());
        }
    }

}
