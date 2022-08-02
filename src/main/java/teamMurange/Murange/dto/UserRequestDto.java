package teamMurange.Murange.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teamMurange.Murange.domain.Badge;
import teamMurange.Murange.domain.Calendar;
import teamMurange.Murange.domain.Follow;
import teamMurange.Murange.domain.Playlist;

import java.util.List;


@Getter
@NoArgsConstructor
public class UserRequestDto {

    private Long user_id;
    private String name;
    private String img_url;
    private String email;
    private Badge badge;

    private List<Playlist> likePlaylistList;
    private List<Calendar> calendarList;
    private List<Follow> followList;
}
