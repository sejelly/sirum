//package teamMurange.Murange.UnitTest.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import teamMurange.Murange.repository.*;
//import teamMurange.Murange.domain.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.mockito.ArgumentMatchers.any;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private PlaylistRepository playlistRepository;
//    @Mock
//    private LikeMusicRepository likeMusicRepository;
//    @Mock
//    private LikePlaylistRepository likePlaylistRepository;
//    @Mock
//    private CalendarRepository calendarRepository;
//    @Mock
//    private FollowRepository followRepository;
//
//    private User getStubUser () {
//        User user = User.builder().name("유저").email("이메일").build();
//
//        return user;
//    }
//    private Follow getStubFollow () {
//        User user1 = User.builder().name("사용자1").build();
//        User user2 = User.builder().name("사용자2").build();
//        Follow follow = Follow.builder().user1(user1).user2(user2).build();
//
//        return follow;
//    }
//
//    @Test
//    void testMakeUser() throws Exception{
//        //given
//        User user = getStubUser();
//        when(userRepository.save(any(User.class))).thenReturn(getStubUser());
//
//        //when
//        User savedUser = userRepository.save(user);
//
//        //then
//        assertEquals(user.getName(),savedUser.getName());
//        assertEquals(user.getEmail(),savedUser.getEmail());
//    }
//
//
//
//
//
//    @Test
//    void testCreateFollow() {
//        // given
//        Follow follow = getStubFollow();
//        when(followRepository.save(any(Follow.class))).thenReturn(getStubFollow());
//
//        // when
//        Follow savedFollow = followRepository.save(follow);
//
//        // then
//        assertEquals(follow.getUser().getName(), savedFollow.getUser().getName());
//        assertEquals(follow.getFollowee().getId(), savedFollow.getFollowee().getId());
//    }
//
//    @Test
//    void testDeleteFollow() throws Exception {
//        // given
//        when(followRepository.getById(any())).thenReturn(getStubFollow());
//        Follow follow = followRepository.getById(any());
//        Long followId = follow.getId();
//
//        // when
//        followRepository.delete(follow);
//        Follow deletedFollow = followRepository.findById(followId).orElse(null);
//
//        // then
//        assertNull(deletedFollow);
//    }
//}