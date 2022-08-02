package teamMurange.Murange.UnitTest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import teamMurange.Murange.domain.LikeMusic;
import teamMurange.Murange.domain.Music;
import teamMurange.Murange.domain.User;
import teamMurange.Murange.repository.LikeMusicRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LikeMusicServiceUnitTest {

    @Mock
    private LikeMusicRepository likeMusicRepository;

    private LikeMusic getStubLikeMusic () {
        User user = User.builder().name("사용자이름").build();
        Music music = Music.builder().title("노래제목").build();
        LikeMusic likeMusic = LikeMusic.builder().user(user).music(music).build();

        return likeMusic;
    }

    @DisplayName("내가 좋아요한 음악에 저장")
    @Test
     void 음악_좋아요_저장 () throws Exception {
        // given
        LikeMusic likeMusic = getStubLikeMusic();
        when(likeMusicRepository.save(any(LikeMusic.class))).thenReturn(getStubLikeMusic());

        // when
        LikeMusic savedLikeMusic = likeMusicRepository.save(likeMusic);

        // then
        assertEquals(likeMusic.getUser().getName(), savedLikeMusic.getUser().getName());
        assertEquals(likeMusic.getMusic().getId(), savedLikeMusic.getMusic().getId());
    }


    @DisplayName("내가 좋아요한 음악에서 취소")
    @Test
     void 음악_좋아요_취소 () throws Exception {
        // given
        when(likeMusicRepository.getById(any())).thenReturn(getStubLikeMusic());
        LikeMusic likeMusic = likeMusicRepository.getById(any());
        Long likeMusicId = likeMusic.getId();

        // when
        likeMusicRepository.delete(likeMusic);
        LikeMusic deletedLikeMusic = likeMusicRepository.findById(likeMusicId).orElse(null);

        // then
        assertNull(deletedLikeMusic);
    }



}
