package teamMurange.Murange.UnitTest.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import teamMurange.Murange.domain.Comment;
import teamMurange.Murange.domain.Playlist;
import teamMurange.Murange.repository.CommentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceUnitTest {

    @Mock
    private CommentRepository commentRepository;

    private List<Comment> getStubCommentList() {
        Playlist playlist = Playlist.builder().id(100L).title("Piano playlist").build();

        List<Comment> commentList =  new ArrayList<>();
        Comment comment1 = Comment.builder().id(1L).playlistComment(playlist).contents("댓글내용1").build();
        Comment comment2 = Comment.builder().id(2L).playlistComment(playlist).contents("댓글내용2").build();
        Comment comment3 = Comment.builder().id(3L).playlistComment(playlist).contents("댓글내용3").build();

        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);

        return commentList;
    }

    private Comment getComment() {
        Long id = 1L;
        String content = "댓글 내용입니다";
        LocalDate createdAt = LocalDate.now();

        return Comment.builder()
                .createdAt(createdAt)
                .contents(content)
                .build();
    }

    @DisplayName("댓글 전체 조회하기")
    @Test
     void 플레이리스트의_댓글_전체_조회 () throws Exception{
        // given
        when(commentRepository.findAllByPlaylistOrderByCreatedAtDesc(any())).thenReturn(getStubCommentList());

        // when
        List<Comment> commentList = commentRepository.findAllByPlaylistOrderByCreatedAtDesc(any());

        // then
        assertEquals(commentList.size(), getStubCommentList().size());
        assertEquals(commentList.get(1).getPlaylist().getId(), getStubCommentList().get(1).getPlaylist().getId());
        assertEquals(commentList.get(1).getPlaylist().getId(), commentList.get(2).getPlaylist().getId());

    }
    
    @DisplayName("댓글 쓰기")
    @Test
     void 댓글_입력 () throws Exception{
        // given
        Comment comment = getComment();
        when(commentRepository.save(any(Comment.class))).thenReturn(getComment());

        // when
        Comment savedComment = commentRepository.save(comment); //저장한 comment 리턴
        Long commentId = comment.getId();

        // then
        assertEquals(savedComment.getContents(), comment.getContents());
        assertEquals(savedComment.getId(), commentId);

    }

    @DisplayName("댓글 수정하기")
    @Test
     void 댓글_수정 () throws Exception{
        // given
        when(commentRepository.getById(any())).thenReturn(getComment());

        Comment comment = commentRepository.getById(any());
        String newContent = "댓글 내용 수정";
        LocalDate createdAt = LocalDate.now();
        Long commentId = comment.getId();

        // when
        comment.updateComment(newContent, createdAt);

        // then
        assertEquals(comment.getId(), commentId);
        assertEquals(comment.getContents(), newContent);
        assertEquals(comment.getCreatedAt(), createdAt);
    }

    @DisplayName("댓글 삭제하기")
    @Test
     void 댓글_삭제 () throws Exception{
        //given
        when(commentRepository.getById(any())).thenReturn(getComment());
        Comment comment = commentRepository.getById(any());
        Long commentId = comment.getId();

        //when
        commentRepository.deleteById(commentId);
        Comment deleteComment = commentRepository.findById(commentId).orElse(null);

        //then
        assertNull(deleteComment);
    }




}
