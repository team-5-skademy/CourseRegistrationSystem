package skademy;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;

//public interface CourseRegistrationSystemRepository extends PagingAndSortingRepository<CourseRegistrationSystem, Long>{
public interface CourseRegistrationSystemRepository extends CrudRepository<CourseRegistrationSystem, Long>{


}