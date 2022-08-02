package teamMurange.Murange;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import teamMurange.Murange.domain.Calendar;
import teamMurange.Murange.domain.Emotion;
import teamMurange.Murange.domain.QUser;
import teamMurange.Murange.domain.User;
import teamMurange.Murange.dto.CalendarResponseDto;
import teamMurange.Murange.dto.MusicResponseDto;
import teamMurange.Murange.repository.CalendarRepository;
import teamMurange.Murange.repository.CategoryRepository;
import teamMurange.Murange.repository.MusicRepository;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static teamMurange.Murange.domain.QCalendar.calendar;

@SpringBootTest
@Transactional
@Commit
public class QuerydslTest {

    @Autowired
    EntityManager em;

    private MusicRepository musicRepository;
    private CategoryRepository categoryRepository;
    private CalendarRepository calendarRepository;

    @Test
    public void startQuerydsl() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        User user = User.builder().name("user1").build();
        em.persist(user);

        //user1 찾아라.
        User findMember = queryFactory
                .select(QUser.user)
                .from(QUser.user)
                .where(QUser.user.name.eq("user1")) //파라미터 바인딩 처리
                .fetchOne();
        assertThat(findMember.getName()).isEqualTo("user1");
    }


    @Test
    public void searchUserEmotion() {
        Long userIdCond = 10L;
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        User user1 = User.builder().id(10L).name("user1").build();
        Calendar calendar1 = Calendar.builder().user(user1).date(LocalDate.now()).firstEmotion(Emotion.angry).secondEmotion(Emotion.happiness).build();
        em.persist(user1);
        em.persist(calendar1);

        CalendarResponseDto result = queryFactory
                .select(Projections.fields(CalendarResponseDto.class,
                        calendar.date,
                        calendar.firstEmotion,
                        calendar.secondEmotion))
                .from(calendar)
                .join(calendar.user, QUser.user)
                .where(userIdEq(userIdCond))
                .orderBy(calendar.date.desc())
                .fetchFirst();

        System.out.println("result");
        System.out.println(result);
        System.out.println(result.getFirstEmotion());
    }

    private Predicate userIdEq(Long userIdCond) {
        return userIdCond != null ? QUser.user.id.eq(userIdCond) : null;
    }


    @Test
    public List<MusicResponseDto> searchMusic(Long userIdCond) {
        CalendarResponseDto result1 = calendarRepository.searchUserEmotion(userIdCond);

        Long categoryId = categoryRepository.searchCategoryId(result1.getFirstEmotion(), result1.getSecondEmotion());
        List<MusicResponseDto> result2 = musicRepository.searchMusicByEmotionCategory(categoryId);
        return result2;

    }
}

