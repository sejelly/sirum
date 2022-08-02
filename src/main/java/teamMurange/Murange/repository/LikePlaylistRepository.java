package teamMurange.Murange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamMurange.Murange.domain.LikePlaylist;

import java.util.List;

@Repository
public interface LikePlaylistRepository extends JpaRepository<LikePlaylist, Long> {

    // user_id로 좋아요 누른 playlist 찾기
    List<LikePlaylist> findAllByUserId(Long user_id);
}
