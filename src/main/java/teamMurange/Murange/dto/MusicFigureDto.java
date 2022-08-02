package teamMurange.Murange.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teamMurange.Murange.domain.Figure;
import teamMurange.Murange.domain.Music;

@Getter
@NoArgsConstructor
public class MusicFigureDto {
    private Figure figure;

    public MusicFigureDto(Music music) {
        this.figure = music.getFigure();
    }
}
