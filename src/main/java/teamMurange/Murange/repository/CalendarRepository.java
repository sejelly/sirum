package teamMurange.Murange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamMurange.Murange.domain.Calendar;
import teamMurange.Murange.repository.CalendarRepositoryCustom.CalendarRepositoryCustom;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long>, CalendarRepositoryCustom {

        // user_id 받아와 calendar 조회
        List<Calendar> findAllByUserId(Long user_id);


    // user_id 받아와 calendar 조회
    List<Calendar> findAllByUserOrderByDateDesc(Long user_id);
}
