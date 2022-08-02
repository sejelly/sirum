package teamMurange.Murange.IntegrationTest.repository;

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
import teamMurange.Murange.repository.CalendarRepository;
import teamMurange.Murange.repository.CategoryRepository;
import teamMurange.Murange.repository.MusicRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static teamMurange.Murange.domain.QCategory.category;

@SpringBootTest
@Transactional
@Commit
public class CategoryRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private CalendarRepository calendarRepository;


    @DisplayName("카테고리 저장")
    @Test
    public void start() {
        Category category1 = Category.builder().firstEmotion(Emotion.angry).secondEmotion(Emotion.sad).build();;
        Category category2 = Category.builder().firstEmotion(Emotion.angry).secondEmotion(Emotion.neutral).build();;
        Category category3 = Category.builder().firstEmotion(Emotion.angry).secondEmotion(Emotion.happiness).build();;
        Category category4 = Category.builder().firstEmotion(Emotion.angry).secondEmotion(Emotion.disgust).build();;
        Category category5 = Category.builder().firstEmotion(Emotion.angry).secondEmotion(Emotion.scared).build();;
        Category category6 = Category.builder().firstEmotion(Emotion.angry).secondEmotion(Emotion.surprised).build();;

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
        categoryRepository.save(category6);

    };


    @DisplayName("주감정 부감정으로 카테고리 id 조회하기")
    @Test
    public void startQuerydsl() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        Emotion firstEmotion = Emotion.angry;
        Emotion secondEmotion = Emotion.sad;
        Long result = queryFactory
                .select(category.id)
                .from(category)
                .where(category.firstEmotion.eq(firstEmotion)
                        .and(category.secondEmotion.eq(secondEmotion)))
                .fetchOne();


    }
}
