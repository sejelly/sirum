package teamMurange.Murange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamMurange.Murange.domain.Category;
import teamMurange.Murange.repository.CategoryRepositoryCustom.CategoryRepositoryCustom;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
}
