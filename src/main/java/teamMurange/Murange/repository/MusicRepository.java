package teamMurange.Murange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamMurange.Murange.domain.Music;
import teamMurange.Murange.repository.MusicRepositoryCustom.MusicRepositoryCustom;

import java.util.List;
@Repository
public interface MusicRepository extends JpaRepository<Music, Long>, MusicRepositoryCustom {

    // category_id로 music 찾기
    List<Music> findAllByCategoryId(Long category_id);
}


