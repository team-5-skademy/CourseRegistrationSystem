package skademy;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//public interface CourseRegistrationSystemRepository extends PagingAndSortingRepository<CourseRegistrationSystem, Long>{
public interface CourseRegistrationSystemRepository extends CrudRepository<CourseRegistrationSystem, Long>{

    Optional<CourseRegistrationSystem> findById(Long id);
}