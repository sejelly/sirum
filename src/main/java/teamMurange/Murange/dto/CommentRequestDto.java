package teamMurange.Murange.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamMurange.Murange.domain.Emotion;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private String contents;

    private Long user_id;
    private Long playlist_id;

    @Builder
    public CommentRequestDto(Long user_id, String contents, Long playlist_id) {
        this.contents = contents;
    }
}
