package teamMurange.Murange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import teamMurange.Murange.domain.Follow;
import teamMurange.Murange.service.UserService;

import java.util.List;

@Api(tags = { "Follow Controller"})
@RestController
@RequiredArgsConstructor
public class FollowController {

    private final UserService userService;


    @ApiOperation(value = "팔로워 조회", notes = "내가 추가한 사용자 불러오기")
    @GetMapping("/likes/follow/{user-id}")
    public ResponseEntity getFollow(@PathVariable(value = "user-id") Long userId) {
        List<Follow> followerList = userService.getFollowList(userId);
        return new ResponseEntity(followerList, HttpStatus.OK);
    }

    @ApiOperation(value = "팔로워 삭제", notes = "사용자 팔로우 끊기")
    @DeleteMapping("/likes follow/{follow-id}")
    public ResponseEntity deleteFollow (@PathVariable(value = "follow-id") Long followId) {
        userService.deleteFollow(followId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
