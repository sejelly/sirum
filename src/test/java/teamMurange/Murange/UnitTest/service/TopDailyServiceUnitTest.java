package teamMurange.Murange.UnitTest.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import teamMurange.Murange.domain.Music;
import teamMurange.Murange.domain.TopDaily;
import teamMurange.Murange.repository.TopDailyRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TopDailyServiceUnitTest {

    @Mock
    private TopDailyRepository topDailyRepository;


    private List<TopDaily> getStubTopDailyList() {
        List<TopDaily> topDailyList =  new ArrayList<>();

        Music music1 = Music.builder().singer("bts").title("butter").build();
        Music music2 = Music.builder().singer("10cm").title("americano").build();
        Music music3 = Music.builder().singer("jason").title("I'm Yours").build();

        TopDaily topDaily1 = TopDaily.builder().music(music1).rank(0).build();
        TopDaily topDaily2 = TopDaily.builder().music(music2).rank(1).build();
        TopDaily topDaily3 = TopDaily.builder().music(music3).rank(2).build();

        topDailyList.add(topDaily1);
        topDailyList.add(topDaily2);
        topDailyList.add(topDaily3);

        return topDailyList;
    }

    @DisplayName("오늘의 인기음악 조회하기")
    @Test
     void 오늘의_인기음악_조회 () throws Exception {
        // given
        when(topDailyRepository.findAll()).thenReturn(getStubTopDailyList());

        // when
        List<TopDaily> topDailyList = topDailyRepository.findAll();

        Music music1 = Music.builder().singer("bts").title("butter").build();
        Music music2 = Music.builder().singer("10cm").title("americano").build();
        Music music3 = Music.builder().singer("jason").title("I'm Yours").build();

        TopDaily topDaily1 = TopDaily.builder().music(music1).rank(0).build();
        TopDaily topDaily2 = TopDaily.builder().music(music2).rank(1).build();
        TopDaily topDaily3 = TopDaily.builder().music(music3).rank(2).build();

        // then
        assertEquals(topDailyList.size(), getStubTopDailyList().size());
        // 실제 인기 음악 0순위가 저장되었는지 확인
        //System.out.println(topDaily1.getRank());
        //System.out.println(getStubTopDailyList().get(0).getRank());
    }

}
