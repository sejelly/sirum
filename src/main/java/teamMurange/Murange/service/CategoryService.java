package teamMurange.Murange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamMurange.Murange.domain.Emotion;
import teamMurange.Murange.repository.CategoryRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 주감정, 부감정으로 카테고리 찾기
    @Transactional(readOnly = true)
    public  Long findCategoryId(Emotion firstEmotion, Emotion secondEmotion) {
        return categoryRepository.searchCategoryId(firstEmotion, secondEmotion);
    }
}
