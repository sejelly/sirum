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
@Table(name = "music")
public class Music {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="music_id")
    private Long id;

    private String title;

    private String singer;

    private String imgUrl;

    private String path;

    private int streamingCnt;

    @Embedded
    private Figure figure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "music")
    private List<LikeMusic> likeMusicList = new ArrayList<>();

    @OneToMany(mappedBy = "music")
    private List<Enroll> enrollList = new ArrayList<>();

    public void updateFigure(Figure figure) {
        Figure.builder()
                .angry(figure.getAngry())
                .disgust(figure.getDisgust())
                .happiness(figure.getHappiness())
                .sad(figure.getSad())
                .neutral(figure.getNeutral())
                .scared(figure.getScared())
                .surprised(figure.getSurprised())
                .build();
        this.figure = figure;
    }

    public void updateStreamingCnt() {
        this.streamingCnt = streamingCnt +1;
    }

    // 테스트용 음악 생성 빌더
    @Builder
    public Music(String title, String imgUrl, String singer, Figure figure, int streamingCnt, Category category, Playlist playlist) {
        this.figure = figure;
        this.imgUrl = imgUrl;
        this.title = title;
        this.singer = singer;
        this.category = category;
        this.streamingCnt = streamingCnt;
    }
    
}
