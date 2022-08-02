package teamMurange.Murange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamMurange.Murange.domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // playlist_id로 comment 조회
    List<Comment> findAllByPlaylistOrderByCreatedAtDesc(Long playlist_id);
}
