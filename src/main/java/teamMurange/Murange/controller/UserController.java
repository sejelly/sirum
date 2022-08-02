package teamMurange.Murange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import teamMurange.Murange.domain.User;
import teamMurange.Murange.dto.UserRequestDto;
import teamMurange.Murange.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Api(tags = { "User Controller"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "유저 등록", notes = "유저 계정 생성하기")
    @PostMapping("/user")
    public ResponseEntity makeUser(@RequestBody @Validated UserRequestDto userRequestDto) {
        userService.makeUser(userRequestDto);
        return new ResponseEntity(HttpStatus.OK);
        // ! spring security, google oauth2 연결 후 변경사항 체크
    }

    @ApiOperation(value = "유저 조회", notes = "유저 계정 조회하기")
    @PostMapping("/user/{user-id}")
    @ResponseBody
    public Map<String,Object> getUser(@PathVariable(value = "user-id") Long userId) throws Exception {
        User user = userService.getUser(userId);

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("id", user.getId());
        returnMap.put("name", user.getName());
        returnMap.put("email", user.getEmail());
        returnMap.put("img_path", user.getImg_url());
        return returnMap;
    }


}
