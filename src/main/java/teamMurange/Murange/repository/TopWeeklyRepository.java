package teamMurange.Murange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamMurange.Murange.domain.TopWeekly;
import teamMurange.Murange.repository.TopWeeklyRepositoryCustom.TopWeeklyRepositoryCustom;

import java.util.List;

@Repository
public interface TopWeeklyRepository extends JpaRepository<TopWeekly, Long>, TopWeeklyRepositoryCustom {

    // 인기있는 순서대로 조회
    List<TopWeekly> findAll();
}
