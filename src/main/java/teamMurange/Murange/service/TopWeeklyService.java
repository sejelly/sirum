package teamMurange.Murange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamMurange.Murange.domain.Music;
import teamMurange.Murange.domain.TopDaily;
import teamMurange.Murange.domain.TopWeekly;
import teamMurange.Murange.dto.MusicResponseDto;
import teamMurange.Murange.repository.TopDailyRepository;
import teamMurange.Murange.repository.TopWeeklyRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TopWeeklyService {

    private final TopWeeklyRepository topWeeklyRepository;
    private final TopDailyRepository topDailyRepository;

    @Transactional(readOnly = true)
    public List<Music> getTopWeeklyAll () {
        List<TopWeekly> topWeeklyList = topWeeklyRepository.findAll();
        // List<MusicResponseDto> musicResponseDtoList = null;
        List<Music> musicList = new ArrayList<>();

        for (TopWeekly topWeekly : topWeeklyList ) {
            Music music = topWeekly.getMusic();
            musicList.add(music);
        }
        return musicList;
    }

    // 매일 정오에 topWeekly 테이블 업데이트
    // 1. topDaily 추가하고,
    // 2. 8일 전 100개는 삭제하기
    public void updateTopWeekly () {

        // 1. 매일 정오에 TopDaily 100개 전부 추가
        List<TopDaily> topDailyList = topDailyRepository.findAll();
        List<TopWeekly> topWeeklyList = null;

        for (TopDaily topDaily : topDailyList ) {
            TopWeekly topWeekly = TopWeekly.builder().date(LocalDate.now()).music(topDaily.getMusic()).build();
            topWeeklyList.add(topWeekly);
        }
        topWeeklyRepository.saveAll(topWeeklyList); // 겹치는 것들 제외!

        // 2. 8일 전 음악 삭제하기
        topWeeklyRepository.deleteLastTopWeekly();

    }


}
