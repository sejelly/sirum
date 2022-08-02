package teamMurange.Murange.repository.MusicRepositoryCustom;

import teamMurange.Murange.dto.MusicResponseDto;

import java.util.List;

public interface MusicRepositoryCustom {

    List<MusicResponseDto> searchMusicByEmotionCategory(Long categoryIdCond);

    List<MusicResponseDto> searchMusicByPlaylist(Long playlistIdCond);

}
