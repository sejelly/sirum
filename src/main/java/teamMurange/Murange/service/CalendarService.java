package teamMurange.Murange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamMurange.Murange.domain.Calendar;
import teamMurange.Murange.domain.User;
import teamMurange.Murange.dto.CalendarResponseDto;
import teamMurange.Murange.repository.CalendarRepository;
import teamMurange.Murange.repository.UserRepository;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    // 사용자의 마지막 감정 기록 조회
    @Transactional(readOnly = true)
    public CalendarResponseDto findUserCalendar(Long userId) {
        CalendarResponseDto result =calendarRepository.searchUserEmotion(userId);
        return result;
    }


    public void createCalendar(CalendarResponseDto calendarResponseDto) {
        User user = userRepository.getById(calendarResponseDto.getUser_id());
        LocalDate date = LocalDate.now();

        Calendar calendar = Calendar.builder().user(user)
                .date(date)
                .firstEmotion(calendarResponseDto.getFirstEmotion())
                .secondEmotion(calendarResponseDto.getSecondEmotion()).build();
        calendarRepository.save(calendar);
    }


}
