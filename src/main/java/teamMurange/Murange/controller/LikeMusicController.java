package teamMurange.Murange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import teamMurange.Murange.domain.LikeMusic;
import teamMurange.Murange.service.LikeMusicService;
import teamMurange.Murange.service.MusicService;
import teamMurange.Murange.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = { "LikeMusic Controller"})
@RestController
@RequiredArgsConstructor
public class LikeMusicController {

    private final MusicService musicService;
    private final LikeMusicService likeMusicService;
    private final UserService userService;

    @ApiOperation(value = "좋아요한 노래 조회", notes = "사용자가 좋아요한 노래 불러오기")
    @GetMapping("/likes/music/{user-id}")
    @ResponseBody
    public ResponseEntity getLikeMusic(@PathVariable(value = "user-id") Long userId) {
        List<LikeMusic> likeMusicList = userService.getLikeMusicList(userId);

        List<Map<String,Object>> returnMap = new ArrayList<>();
        for (int i = 0; i < likeMusicList.size() ; i ++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id", likeMusicList.get(i).getId());
            map.put("title", likeMusicList.get(i).getMusic().getTitle());
            map.put("singer", likeMusicList.get(i).getMusic().getSinger());
            map.put("img_path", likeMusicList.get(i).getMusic().getImgUrl());
            returnMap.add(map);
        }

        return new ResponseEntity(returnMap, HttpStatus.OK);
    }

}
