package teamMurange.Murange.IntegrationTest.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import teamMurange.Murange.dto.CalendarResponseDto;
import teamMurange.Murange.dto.MusicResponseDto;
import teamMurange.Murange.repository.CalendarRepository;
import teamMurange.Murange.repository.CategoryRepository;
import teamMurange.Murange.repository.MusicRepository;

import java.util.List;

@SpringBootTest
@Transactional
@Commit
public class TopWeeklyServiceTest {
    private MusicRepository musicRepository;
    private CategoryRepository categoryRepository;
    private CalendarRepository calendarRepository;


    @DisplayName("주간 인기 음악 조회하기")
    @Test
    public List<MusicResponseDto> searchMusic(Long userIdCond) {
        CalendarResponseDto result1 = calendarRepository.searchUserEmotion(userIdCond);

        Long categoryId = categoryRepository.searchCategoryId(result1.getFirstEmotion(), result1.getSecondEmotion());
        List<MusicResponseDto> result2 = musicRepository.searchMusicByEmotionCategory(categoryId);
        return result2;

    }
}
