package teamMurange.Murange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamMurange.Murange.domain.User;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // user_id로 user 찾기
    List<User> findAllById(Long user_id);

}


