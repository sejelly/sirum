package teamMurange.Murange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamMurange.Murange.domain.*;
import teamMurange.Murange.dto.UserRequestDto;
import teamMurange.Murange.repository.*;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PlaylistRepository playlistRepository;
    private final LikeMusicRepository likeMusicRepository;
    private final LikePlaylistRepository likePlaylistRepository;
    private final CalendarRepository calendarRepository;
    private final FollowRepository followRepository;

    //유저 생성
    public void makeUser(UserRequestDto userRequestDto) {
        String newName = userRequestDto.getName();
        String newEmail = userRequestDto.getEmail();
        User user = User.builder().name(newName).email(newEmail).build();
        userRepository.save(user);


     // ! google oauth2 삽입시  변경 사항 확인
    }

    // 유저 id 받아 계정 조회
    @Transactional(readOnly = true)
    public User getUser(Long user_id) {
        User user = userRepository.getById(user_id);
        return user;
    }

    // user_id 받아 좋아요한 음악 조회
    @Transactional(readOnly = true)
    public List<LikeMusic> getLikeMusicList (Long user_id) {
        List<LikeMusic> likemusicList = likeMusicRepository.findAllByUserId(user_id);
        return likemusicList;
    }

    // user_id 받아 좋아요한 플레이리스트 조회
    @Transactional(readOnly = true)
    public List<LikePlaylist> getLikePlaylistList (Long user_id) {
        List<LikePlaylist> likeplaylistList = likePlaylistRepository.findAllByUserId(user_id);
        return likeplaylistList;
    }

    // 유저 id로 유저가 만든 플레이리스트 조회
    @Transactional
    public List<Playlist> getMyPlaylistList (Long user_id) {
        List<Playlist> myplaylistList = playlistRepository.findAllByUserId(user_id);
        return myplaylistList;
    }

    // 유저 id로 유저 캘린더 조회
    @Transactional
    public List<Calendar> getCalendarList (Long user_id) {
        List<Calendar> calendarList = calendarRepository.findAllByUserId(user_id);
        return calendarList;
    }

    // user_id 받아 팔로워 조회
    @Transactional(readOnly = true)
    public List<Follow> getFollowList (Long user_id) {
        List<Follow> followList = followRepository.findAllByUserId(user_id);
        return followList;
    }

    //팔로워 추가
//    public void createFollow (Long userId1, Long userId2) {
//        User user = userRepository.getById(userId1);
//        User followee = userRepository.getById(userId2);
//        Follow follow = Follow.builder().user1(user).user2(followee).build();
//        followRepository.save(follow);
//    }

    // 팔로워 삭제
    public void deleteFollow (Long followId) {
        Follow follow = followRepository.getById(followId);
        followRepository.delete(follow);
    }



}
