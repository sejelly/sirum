//package teamMurange.Murange.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import teamMurange.Murange.domain.LikePlaylist;
//import teamMurange.Murange.service.LikeMusicService;
//import teamMurange.Murange.service.MusicService;
//import teamMurange.Murange.service.UserService;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Api(tags = { "LikePlaylist Controller"})
//@RestController
//@RequiredArgsConstructor
//public class LikePlaylistController {
//
//    private final MusicService musicService;
//    private final LikeMusicService likeMusicService;
//    private final UserService userService;
//
//    @ApiOperation(value = "좋아요한 플레이리스트 조회", notes = "사용자가 좋아요한 플레이리스트 불러오기")
//    @GetMapping("/likes/playlist/{user-id}")
//    @ResponseBody
//    public ResponseEntity getLikePlaylist(@PathVariable(value = "user-id") Long userId) {
//        List<LikePlaylist> likePlaylistList = userService.getLikePlaylistList(userId);
//        List<Map<String,Object>> returnMap = new ArrayList<>();
//
//        for (int i = 0; i < likePlaylistList.size() ; i ++) {
//            Map<String,Object> map = new HashMap<>();
//            map.put("id", likePlaylistList.get(i));
//            map.put("img_path", likePlaylistList.get(i).getMusic().getImgUrl());
//            returnMap.add(map);
//        }
//
//        return new ResponseEntity(returnMap, HttpStatus.OK);
//    }
//
//}
