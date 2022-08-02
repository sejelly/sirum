package teamMurange.Murange.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teamMurange.Murange.domain.Music;

@Getter
@NoArgsConstructor
public class MusicResponseDto {

    private Long id;

    private String title;

    private String singer;

    private String img_url;

    private int streaming_cnt;

    private String path;

    public MusicResponseDto(Music music) {
        this.id = music.getId();
        this.title = music.getTitle();
        this.singer = music.getSinger();
        this.img_url = music.getImgUrl();
        this.streaming_cnt = music.getStreamingCnt();
        this.path = music.getPath();
    }

}
