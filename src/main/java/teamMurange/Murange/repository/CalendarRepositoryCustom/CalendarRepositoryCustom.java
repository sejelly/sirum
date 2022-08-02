package teamMurange.Murange.repository.CalendarRepositoryCustom;

import teamMurange.Murange.dto.CalendarResponseDto;

public interface CalendarRepositoryCustom {

    CalendarResponseDto searchUserEmotion (Long userIdCond);
}
