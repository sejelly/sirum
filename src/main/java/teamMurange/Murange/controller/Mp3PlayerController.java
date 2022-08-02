package teamMurange.Murange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javazoom.jl.player.advanced.AdvancedPlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import teamMurange.Murange.domain.Music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = { "Music Play Controller"})
@RestController
@RequiredArgsConstructor
public class Mp3PlayerController {

    private AdvancedPlayer player;

    public static void main(String[] args) {

        String file="C:\\Users\\suyou\\Desktop\\Winter Bear ⓒmonoRM912.mp3";
        mp3PlayerSample mp3 = new mp3PlayerSample(file);

        mp3.play();
        // mp3.stop();

    }


//    @ApiOperation(value = "음악 재생", notes = "음악 재생하기")
//    @GetMapping("/play/{music-id}")
//    public ResponseEntity playMusic(@PathVariable(value = "music-id") Long musicId) {
//
//        return new ResponseEntity(HttpStatus.OK);
//
//    }

    @ApiOperation(value = "음악 재생", notes = "음악 재생하기")
    @GetMapping("/play")
    @ResponseBody
    public void playMusic22() throws Exception, Exception {

    }

    @ApiOperation(value = "음악 일시 멈춤", notes = "재생중인 음악 일시 멈춤하기")
    @GetMapping("/play/pause/{music-id}")
    public ResponseEntity pauseMusic(@PathVariable(value = "music-id") Long musicId) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "음악 정지", notes = "음악 정지시키기")
    @GetMapping("/play/stop/{music-id}")
    public ResponseEntity stopMusic(@PathVariable(value = "music-id") Long musicId) {

        return new ResponseEntity(HttpStatus.OK);

    }

}
