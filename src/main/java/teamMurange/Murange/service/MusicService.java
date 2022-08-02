package teamMurange.Murange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamMurange.Murange.domain.Figure;
import teamMurange.Murange.domain.Music;
import teamMurange.Murange.dto.MusicResponseDto;
import teamMurange.Murange.repository.MusicRepository;
import teamMurange.Murange.repository.TopDailyRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;
    private final TopDailyRepository topDailyRepository;

    // 음악 처음 재생
    @Transactional(readOnly = true)
    public void playMusic(Long music_id) {
        Music music = musicRepository.getById(music_id);
        // 1. 음악 재생하기

        // 2. 음악 조회수 업데이트
        music.updateStreamingCnt();

    }

    // 음악 id로 음악 기본 데이터 조회
    @Transactional(readOnly = true)
    public Music getMusicData(Long music_id) {
        Music music = musicRepository.getById(music_id);
        return music;
    }

    // category_id로 음악 조회
    @Transactional(readOnly = true)
    public List<MusicResponseDto> getMusicsByCategory (Long category_id) {
        List<MusicResponseDto> musicList = musicRepository.searchMusicByEmotionCategory(category_id);
        return musicList;
    }

    // playlist_id로 음악 조회
    @Transactional(readOnly = true)
    public List<MusicResponseDto> getMusicsByPlaylist (Long playlist_id) {
        List<MusicResponseDto> musicList = musicRepository.searchMusicByPlaylist(playlist_id);
        return musicList;
    }

    // 음악 id로 감정별 수치 조회
    @Transactional(readOnly = true)
    public Figure getMusicFigure(Long music_id) {
        Music music = musicRepository.getById(music_id);
        Figure figure = music.getFigure();
        return figure;
    }

    // 음악 id로 감정별 수치 입력
    public void updateMusicFigure(Long music_id) {
        Music music = musicRepository.getById(music_id);
        Figure figure = music.getFigure();

        // ! 수치 변경하는 방법 결정하고 구현 예정
    }

}
