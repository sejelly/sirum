package teamMurange.Murange.IntegrationTest.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import teamMurange.Murange.domain.QTopWeekly;
import teamMurange.Murange.domain.TopDaily;
import teamMurange.Murange.domain.TopWeekly;
import teamMurange.Murange.dto.CalendarResponseDto;
import teamMurange.Murange.dto.MusicResponseDto;
import teamMurange.Murange.repository.CalendarRepository;
import teamMurange.Murange.repository.CategoryRepository;
import teamMurange.Murange.repository.MusicRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static teamMurange.Murange.domain.QTopDaily.topDaily;

@SpringBootTest
@Transactional
@Commit
public class TopWeeklyRepositoryTest {
    private MusicRepository musicRepository;
    private CategoryRepository categoryRepository;
    private CalendarRepository calendarRepository;

    @Autowired
    EntityManager em;

    @DisplayName("이번주 음악 조회하기 - 조회수 순서대로")
    @Test
    public void 이번주_인기_음악_조회() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<TopWeekly> result = queryFactory
                .select(QTopWeekly.topWeekly)
                .from(QTopWeekly.topWeekly)
                .where(QTopWeekly.topWeekly.date.eq(LocalDate.now().minusDays(8)))
                .fetch();
    }
}
