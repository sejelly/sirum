package teamMurange.Murange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamMurange.Murange.domain.Follow;
import teamMurange.Murange.domain.Music;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    // user_id 받아와 follower 조회
    List<Follow> findAllByUserId(Long user_id);
}
