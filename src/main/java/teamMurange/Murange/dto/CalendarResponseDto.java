package teamMurange.Murange.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamMurange.Murange.domain.Calendar;
import teamMurange.Murange.domain.Emotion;
import teamMurange.Murange.domain.Music;

@Getter
@NoArgsConstructor
public class CalendarResponseDto {

    private Long user_id;
    private Emotion firstEmotion;
    private Emotion secondEmotion;

    @Builder
    public CalendarResponseDto(Long user_id, Emotion firstEmotion, Emotion secondEmotion) {
        this.user_id = user_id;
        this.firstEmotion = firstEmotion;
        this.secondEmotion = secondEmotion;
    }
}
