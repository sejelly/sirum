package teamMurange.Murange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamMurange.Murange.domain.Playlist;

import java.util.List;
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    // category_id로 playlist 찾기
    List<Playlist> findAllByCategoryId(Long category_id);

    // user_id로 playlist 찾기
    List<Playlist> findAllByUserId(Long user_id);
}


