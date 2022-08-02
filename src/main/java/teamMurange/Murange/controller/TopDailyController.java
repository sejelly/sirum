package teamMurange.Murange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import teamMurange.Murange.domain.Music;
import teamMurange.Murange.dto.MusicResponseDto;
import teamMurange.Murange.repository.MusicRepository;
import teamMurange.Murange.service.MusicService;
import teamMurange.Murange.service.TopDailyService;
import teamMurange.Murange.service.TopWeeklyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = { "TopDaily Controller"})
@RestController
@RequiredArgsConstructor
public class TopDailyController {

    private final MusicService musicService;
    private final TopWeeklyService topWeeklyService;
    private final TopDailyService topDailyService;
    private final MusicRepository musicRepository;


    @ApiOperation(value = "자정마다 topDaily, topWeekly 변경", notes = "매일 자정마다 topDaily, topWeekly 업데이트")
    @Scheduled(cron = "0 0 24 * * *")
    public ResponseEntity updateTopDaily() {
        topDailyService.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "일간 인기 음악 추천", notes = "메인 화면에서의 음악 추천")
        @GetMapping("/recommend/today")
        @ResponseBody
        public List<Map<String,Object>> getTopDaily() throws Exception, Exception {
            List<Music> musicList = topDailyService.getTopDailyAll();

            // List<Music> musicList = musicRepository.findAll();

            List<Map<String,Object>> returnMap = new ArrayList<>();
        for (int i = 0; i < musicList.size() ; i ++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id", musicList.get(i).getId());
            map.put("title", musicList.get(i).getTitle());
            map.put("singer", musicList.get(i).getSinger());
            map.put("img_url", musicList.get(i).getImgUrl());
            returnMap.add(map);
        }

        return returnMap;
    }
}
