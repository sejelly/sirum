package teamMurange.Murange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamMurange.Murange.domain.LikeMusic;
import teamMurange.Murange.repository.LikeMusicRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeMusicService {

    private final LikeMusicRepository likeMusicRepository;

    public void createLikeMusic (Long userId, Long musicId) {
        // User user = userRepository.getById(userId);
        // Music music = musicRepository.getById(musicId);
        // LikeMusic likeMusic = LikeMusic.builder().user(user).music(music).build();
        // likeMusicRepository.save(likeMusic);
    }

    public void deleteLikes (Long likeMusicId) {
        LikeMusic likeMusic = likeMusicRepository.getById(likeMusicId);
        likeMusicRepository.delete(likeMusic);
    }

}
