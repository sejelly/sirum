package teamMurange.Murange.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Emotion firstEmotion;

    @Enumerated(EnumType.STRING)
    private Emotion secondEmotion;

    @OneToMany(mappedBy = "category")
    private List<Music> musicList = new ArrayList<>();

    @Builder
    public Category(Emotion firstEmotion, Emotion secondEmotion) {
        this.firstEmotion = firstEmotion;
        this.secondEmotion = secondEmotion;
    }
}
