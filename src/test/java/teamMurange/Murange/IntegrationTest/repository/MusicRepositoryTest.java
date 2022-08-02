package teamMurange.Murange.IntegrationTest.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import teamMurange.Murange.domain.*;
import teamMurange.Murange.dto.CalendarResponseDto;
import teamMurange.Murange.dto.MusicResponseDto;
import teamMurange.Murange.repository.CategoryRepository;
import teamMurange.Murange.repository.MusicRepository;
import teamMurange.Murange.repository.UserRepository;

import javax.persistence.EntityManager;

import java.util.List;

import static teamMurange.Murange.domain.QCalendar.calendar;
import static teamMurange.Murange.domain.QEnroll.enroll;
import static teamMurange.Murange.domain.QMusic.music;

@SpringBootTest
@Transactional
@Commit
public class MusicRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MusicRepository musicRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @DisplayName("음악 데이터 저장")
    @Test
    public void start() {
        Long category1 = categoryRepository.searchCategoryId(Emotion.angry, Emotion.sad);
        Category category = categoryRepository.getById(category1);
        Figure figure = Figure.builder().angry(0.1F).disgust(0.1F).happiness(0.1F).sad(0.2F).neutral(0.3F).scared(0.1F).surprised(0.1F).build();

        Music music1 = Music.builder().category(category).title("title1").singer("singer1").figure(figure).build();
        Music music2 = Music.builder().category(category).title("title2").singer("singer2").figure(figure).build();
        Music music3 = Music.builder().category(category).title("title3").singer("singer3").figure(figure).build();
        Music music4 = Music.builder().category(category).title("title4").singer("singer4").figure(figure).build();
        Music music5 = Music.builder().category(category).title("title5").singer("singer5").figure(figure).build();
        musicRepository.save(music1);
        musicRepository.save(music2);
        musicRepository.save(music3);
        musicRepository.save(music4);
        musicRepository.save(music5);
    };


    @DisplayName("감정 카테고리별 음악 조회하기")
    @Test
    public void 감정_카테고리별_음악_조회() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        Long categoryIdCond = 10L;
        List<MusicResponseDto> result = queryFactory
                .select(Projections.fields(MusicResponseDto.class,
                        music.id,
                        music.title,
                        music.singer,
                        music.imgUrl,
                        music.streamingCnt,
                        music.path))
                .from(music)
                .join(music.category, QCategory.category)
                .where(categoryIdEq(categoryIdCond))
                .orderBy(music.streamingCnt.desc())
                .limit(50)
                .fetch();
    };
    private Predicate categoryIdEq(Long categoryIdCond) {
        return categoryIdCond != null ? QCategory.category.id.eq(categoryIdCond) : null;

    };

    @DisplayName("플레이리스트별 음악 조회하기")
    @Test
    public void 플레이리스트별_음악_조회() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        Long playlistIdCond = 20L;

        List<MusicResponseDto> result = queryFactory
                .select(Projections.fields(MusicResponseDto.class,
                        enroll.music.id,
                        enroll.music.title,
                        enroll.music.singer,
                        enroll.music.imgUrl,
                        enroll.music.streamingCnt,
                        enroll.music.path
                ))
                .from(enroll)
                .where(enroll.playlist.id.eq(playlistIdCond))
                .orderBy(music.streamingCnt.desc())
                .limit(50)
                .fetch();
    };
}
