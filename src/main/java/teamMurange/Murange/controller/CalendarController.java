package teamMurange.Murange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamMurange.Murange.domain.Calendar;
import teamMurange.Murange.domain.Emotion;
import teamMurange.Murange.dto.CalendarResponseDto;
import teamMurange.Murange.service.CalendarService;
import teamMurange.Murange.service.LikeMusicService;
import teamMurange.Murange.service.MusicService;
import teamMurange.Murange.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = { "Calendar Controller"})
@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final MusicService musicService;
    private final LikeMusicService likeMusicService;
    private final UserService userService;
    private final CalendarService calendarService;

    @ApiOperation(value = "감정 조회", notes = "사용자의 날짜별 감정 불러오기")
    @ResponseBody
    @GetMapping("/date/{user-id}")
    public ResponseEntity getCalendar(@PathVariable(value = "user-id") Long userId) {

        List<Calendar> calendarList = userService.getCalendarList(userId);

        List<Map<String,Object>> returnMap = new ArrayList<>();
        for (int i = 0; i < calendarList.size() ; i ++) {
            Map<String,Object> map = new HashMap<>();
            map.put("date", calendarList.get(i).getDate());
            map.put("first-emotion", calendarList.get(i).getFirstEmotion());
            map.put("second-emotion", calendarList.get(i).getSecondEmotion());
            returnMap.add(map);
        }

        return new ResponseEntity(returnMap, HttpStatus.OK);
    }

    @ApiOperation(value = "감정 등록", notes = "감정 분석 후 감정 기록하기")
    @ResponseBody
    @PostMapping("/date/{user-id}/{first-emotion-id}/{second-emotion-id}")
    public ResponseEntity createCalendar(
            @PathVariable(value = "user-id") Long userId,
            @PathVariable(value = "first-emotion-id") Emotion firstEmotion,
            @PathVariable(value = "second-emotion-id") Emotion secondEmotion) throws Exception {

        CalendarResponseDto calendarResponseDto = CalendarResponseDto.builder().user_id(userId).firstEmotion(firstEmotion).secondEmotion(secondEmotion).build();
        calendarService.createCalendar(calendarResponseDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
