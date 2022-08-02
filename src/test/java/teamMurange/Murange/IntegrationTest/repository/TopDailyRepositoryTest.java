package teamMurange.Murange.IntegrationTest.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import teamMurange.Murange.domain.Music;
import teamMurange.Murange.domain.QUser;
import teamMurange.Murange.domain.TopDaily;
import teamMurange.Murange.domain.User;
import teamMurange.Murange.dto.CalendarResponseDto;
import teamMurange.Murange.dto.MusicResponseDto;
import teamMurange.Murange.repository.CalendarRepository;
import teamMurange.Murange.repository.CategoryRepository;
import teamMurange.Murange.repository.MusicRepository;
import teamMurange.Murange.repository.TopDailyRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static teamMurange.Murange.domain.QTopDaily.topDaily;

@SpringBootTest
@Transactional
@Commit
public class TopDailyRepositoryTest {
    private MusicRepository musicRepository;
    private CategoryRepository categoryRepository;
    private CalendarRepository calendarRepository;
    private TopDailyRepository topDailyRepository;

    @Autowired
    EntityManager em;

    @DisplayName("오늘의 인기 음악 조회하기 - 조회수 순서대로")
    @Test
    public void 오늘의_인기_음악_조회() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<TopDaily> result = queryFactory
                .select(topDaily)
                .from(topDaily)
                .orderBy(topDaily.music.streamingCnt.desc())
                .limit(100)
                .fetch();
    }
}
