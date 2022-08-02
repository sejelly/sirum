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
import teamMurange.Murange.domain.Playlist;
import teamMurange.Murange.service.LikeMusicService;
import teamMurange.Murange.service.MusicService;
import teamMurange.Murange.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = { "Playlist Controller"})
@RestController
@RequiredArgsConstructor
public class PlaylistController {

    private final MusicService musicService;
    private final LikeMusicService likeMusicService;
    private final UserService userService;

    // 플레이리스트 수정
    @ApiOperation(value = "내 플레이리스트 조회", notes = "사용자가 만든 플레이리스트 불러오기")
    @GetMapping("/likes/my/{user-id}")
    @ResponseBody
    public ResponseEntity getMyPlaylist(@PathVariable(value = "user-id") Long userId) {
        List<Playlist> myPlaylistList = userService.getMyPlaylistList(userId);

        List<Map<String,Object>> returnMap = new ArrayList<>();
        for (int i = 0; i < myPlaylistList.size() ; i ++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id", myPlaylistList.get(i).getId());
            map.put("title", myPlaylistList.get(i).getTitle());
            map.put("img_url", myPlaylistList.get(i).getImg_url());
            returnMap.add(map);
        }

        return new ResponseEntity(myPlaylistList, HttpStatus.OK);
    }


}
