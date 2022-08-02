package teamMurange.Murange.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamMurange.Murange.domain.Emotion;
import teamMurange.Murange.domain.Music;
import teamMurange.Murange.dto.CalendarResponseDto;
import teamMurange.Murange.dto.MusicResponseDto;
import teamMurange.Murange.repository.MusicRepository;
import teamMurange.Murange.service.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = { "Music Controller"})
@RestController
@RequiredArgsConstructor
public class MusicController {


    private LikeMusicService likeMusicService;

    private final MusicService musicService;
    private final MusicRepository musicRepository;
    private final CalendarService calendarService;
    private final CategoryService categoryService;
    private final TopWeeklyService topWeeklyService;
    private final TopDailyService topDailyService;

    // 조회수 반영은 끊기지 않고 다 완곡 들었을 경우만 반영!
    @ApiOperation(value = "음악 첫 재생", notes = "음악 첫 재생(조회수 반영)")
    @GetMapping("/play/{music-id}")
    @ResponseBody
    public ResponseEntity playMusic(@PathVariable(value = "playlist-id") Long musicId) {
        musicService.playMusic(musicId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "감정별 음악 추천", notes = "표정 분석 페이지에서 분석 후")
    @GetMapping("/category/{first-emotion}/{second-emotion}")
    @ResponseBody
    public ResponseEntity getComments2(@PathVariable(value = "first-emotion") Emotion firstEmotion,
                                        @PathVariable(value = "second-emotion") Emotion secondEmotion) {
        Long categoryId = categoryService.findCategoryId(firstEmotion, secondEmotion);
        List<MusicResponseDto> musicList = musicService.getMusicsByCategory(categoryId);

        List<Map<String,Object>> returnMap = new ArrayList<>();
        for (int i = 0; i < musicList.size() ; i ++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id", musicList.get(i).getId());
            map.put("title", musicList.get(i).getTitle());
            map.put("singer", musicList.get(i).getSinger());
            map.put("img_path", musicList.get(i).getImg_url());
            returnMap.add(map);
        }

        return new ResponseEntity(returnMap, HttpStatus.OK);
    }

//    @ApiOperation(value = "유저의 마지막 감정 기반 음악 추천", notes = "메인 화면에서의 음악 추천")
//    @GetMapping("/recommend/{user-id}")
//    @ResponseBody
//    public ResponseEntity getMusicByUserEmotion(@PathVariable(value = "user-id") Long userId) {
//        CalendarResponseDto calendar = calendarService.findUserCalendar(userId);
//        Long categoryId = categoryService.findCategoryId(calendar.getFirstEmotion(), calendar.getSecondEmotion());
//        List<MusicResponseDto> musicResponseDtoList = musicService.getMusicsByCategory(categoryId);
//
//        List<Map<String,Object>> returnMap = new ArrayList<>();
//        for (int i = 0; i < musicResponseDtoList.size() ; i ++) {
//            Map<String,Object> map = new HashMap<>();
//            map.put("id", musicResponseDtoList.get(i).getId());
//            map.put("title", musicResponseDtoList.get(i).getTitle());
//            map.put("singer", musicResponseDtoList.get(i).getSinger());
//            map.put("img_path", musicResponseDtoList.get(i).getImg_url());
//            returnMap.add(map);
//        }
//
//        return new ResponseEntity(musicResponseDtoList, HttpStatus.OK);
//    }

    @ApiOperation(value = "테스트용 유저의 마지막 감정 기반 음악 추천", notes = "메인 화면에서의 음악 추천")
    @GetMapping("/recommend")
    @ResponseBody //
    public List<Map<String,Object>> getMusicByUserEmotion2() throws Exception, Exception {
//        CalendarResponseDto calendar = calendarService.findUserCalendar(1L);
//        Long categoryId = categoryService.findCategoryId(calendar.getFirstEmotion(), calendar.getSecondEmotion());
//        List<MusicResponseDto> musicResponseDtoList = musicService.getMusicsByCategory(categoryId);

        List<Music> musicList = musicRepository.findAll();

        List<Map<String,Object>> returnMap = new ArrayList<>();
        for (int i = 0; i < musicList.size() ; i ++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id", musicList.get(i).getId());
            map.put("title", musicList.get(i).getTitle());
            map.put("singer", musicList.get(i).getSinger());
            map.put("img_url", musicList.get(i).getImgUrl());
            returnMap.add(map);
        }

        // return new ResponseEntity(musicResponseDtoList, HttpStatus.OK);
        return returnMap;
    }


}
