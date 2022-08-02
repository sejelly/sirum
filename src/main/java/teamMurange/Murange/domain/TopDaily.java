package teamMurange.Murange.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "top_daily")
public class TopDaily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 전략,,
    @Column(name="top_daily_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "music_id")
    private Music music;

    @Builder
    public TopDaily (Music music, int rank, Date date) {
        this.music = music;
    }

}
