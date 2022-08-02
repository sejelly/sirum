package teamMurange.Murange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import teamMurange.Murange.domain.Comment;
import teamMurange.Murange.dto.CommentRequestDto;
import teamMurange.Murange.repository.CommentRepository;
import teamMurange.Murange.service.CommentService;
import teamMurange.Murange.service.MusicService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = { "Comment Controller"})
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final MusicService musicService;
    private final CommentRepository commentRepository;

    @ApiOperation(value = "댓글 조회", notes = "댓글 조회하기")
    @GetMapping("/comments/{playlist-id}")
    public ResponseEntity getComments(@PathVariable(value = "playlist-id") Long playlistId) {
        List<Comment> commentList = commentService.getCommentList(playlistId);
        return new ResponseEntity(commentList, HttpStatus.OK);
    }


    @ApiOperation(value = "테스트용 전체 조회", notes = "테스트용")
    @GetMapping("/testSearchAll")
    @ResponseBody
    public ResponseEntity getComments22() throws Exception, Exception {
        List<Comment> comment = commentRepository.findAll();

        List<Map<String,Object>> returnMap = new ArrayList<>();
        for (int i = 0; i < comment.size() ; i ++) {
            Map<String,Object> map = new HashMap<>();
            map.put("contents", comment.get(i).getContents());
            map.put("createdAt", comment.get(i).getCreatedAt());
            returnMap.add(map);
        }

        return new ResponseEntity(returnMap, HttpStatus.OK);
    }

    @ApiOperation(value = "테스트용 하나 조회", notes = "테스트용")
    @GetMapping("/testSearchOne")
    @ResponseBody
    public Map<String,Object> getComments33() throws Exception, Exception {
        Comment comment = commentRepository.findAll().get(1);

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("contents", comment.getContents());
        returnMap.put("createdAt", comment.getCreatedAt());
        return returnMap;
    }

    @ApiOperation(value = "테스트용 하나 선택해서 조회", notes = "테스트용")
    @ResponseBody
    @GetMapping("/testSearchOne/{comment-id}")
    public ResponseEntity test22(@PathVariable(value = "comment-id") Long commentId) throws Exception {
        Comment comment = commentRepository.findAll().get(Math.toIntExact(commentId));
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("contents", comment.getContents());
        returnMap.put("createdAt", comment.getCreatedAt());
        return new ResponseEntity(returnMap, HttpStatus.OK);

    }

    @ApiOperation(value = "테스트용 등록", notes = "테스트용")
    @ResponseBody
    @PostMapping("/testCreate")
    public ResponseEntity test(@RequestBody Map<String, Object> inputData) throws Exception {
        String contents = (String) inputData.get("contents");
        CommentRequestDto commentRequestDto = CommentRequestDto.builder().contents(contents).build();
        commentService.createComment((CommentRequestDto) commentRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 등록", notes = "댓글 등록하기")
    @ResponseBody
    @PostMapping("/comments")
    public ResponseEntity saveComment(@RequestBody Map<String, Object> inputData) throws Exception {
        String contents = (String) inputData.get("contents");
        Long userId = (Long) inputData.get("user-id");
        Long playlistId = (Long) inputData.get("playlist-id");

        CommentRequestDto commentRequestDto = CommentRequestDto.builder().playlist_id(playlistId).user_id(userId).contents(contents).build();
        commentService.createComment(commentRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //---------------------------------

    @ApiOperation(value = "댓글 수정", notes = "댓글 수정하기")
    @PatchMapping("/comments/{comments-id}")
    public ResponseEntity updateComment(
            @PathVariable(value = "comments-id") Long commentId ,
            @RequestBody @Validated CommentRequestDto comment) {
        commentService.updateComment(commentId, comment);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글 삭제하기")
    @DeleteMapping("/comments/{comments-id}")
    public ResponseEntity deleteComment (@PathVariable(value = "comments-id") Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @ApiOperation(value = "댓글 등록", notes = "댓글 등록하기")
//    @ResponseBody
//    @PostMapping("/comments")
//    public ResponseEntity saveComment(@RequestBody @Validated CommentRequestDto commentRequestDto) {
//        commentService.createComment(commentRequestDto);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "댓글 수정", notes = "댓글 수정하기")
//    @PatchMapping("/comments/{comments-id}")
//    public ResponseEntity updateComment(
//            @PathVariable(value = "comments-id") Long commentId ,
//            @RequestBody @Validated CommentRequestDto comment) {
//        commentService.updateComment(commentId, comment);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "댓글 삭제", notes = "댓글 삭제하기")
//    @DeleteMapping("/comments/{comments-id}")
//    public ResponseEntity deleteComment (@PathVariable(value = "comments-id") Long commentId) {
//        commentService.deleteComment(commentId);
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
