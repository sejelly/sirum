package teamMurange.Murange.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "like_music")
public class LikeMusic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="like_music_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music music;

    @Builder
    public LikeMusic (User user, Music music) {
        this.music = music;
        this.user = user;
    }

}
