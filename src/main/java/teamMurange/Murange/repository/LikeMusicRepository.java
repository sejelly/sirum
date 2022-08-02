package teamMurange.Murange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamMurange.Murange.domain.LikeMusic;

import java.util.List;

@Repository
public interface LikeMusicRepository extends JpaRepository<LikeMusic, Long> {

    // user_id로 좋아요 누른 music 찾기
    List<LikeMusic> findAllByUserId(Long user_id);
}
