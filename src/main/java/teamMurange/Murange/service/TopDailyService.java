package teamMurange.Murange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamMurange.Murange.domain.Music;
import teamMurange.Murange.domain.TopDaily;
import teamMurange.Murange.dto.MusicResponseDto;
import teamMurange.Murange.repository.MusicRepository;
import teamMurange.Murange.repository.TopDailyRepository;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
@RequiredArgsConstructor
public class TopDailyService {

    private final MusicRepository musicRepository;
    private final TopDailyRepository topDailyRepository;
    private Long minStreamingCnt;

    @Transactional(readOnly = true)
    public List<Music> getTopDailyAll() {
        List<TopDaily> topDailyList = topDailyRepository.getTopDailyAll();
        // List<MusicResponseDto> musicResponseDtoList = new ArrayList<>();
        List<Music> musicList = new ArrayList<>();

        for (TopDaily topDaily : topDailyList ) {
            Music music = topDaily.getMusic();
            // Long musicId = topDaily.getMusic().getId();
            // Music music = musicRepository.getById(musicId);
            musicList.add(music);
        }
        return musicList;
    }

    // 조회수가 많아지면 새로운 TopDaily 생성
    public void createTopDaily (Long musicId) {
        Music music = musicRepository.getById(musicId);
        TopDaily topDaily = TopDaily.builder().music(music).build();

        int total = topDailyRepository.countTopDaily();

        // 테이블에 100개 차면 마지막꺼 지우기 ?? 근데 topWeekly 가 어제-오늘 겹칠수도 있으니까 100개 이상 저장하기
        if (total == 100) {
            TopDaily lastTopDaily = topDailyRepository.searchLowestTopDaily();
            topDailyRepository.delete(lastTopDaily);
        }
        topDailyRepository.save(topDaily);
    }

    // 정오에 topDaily 다 지우기
    public void deleteAll() {
        topDailyRepository.deleteAll();
    }

}
