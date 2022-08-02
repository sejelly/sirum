package teamMurange.Murange.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "top_weekly")
public class TopWeekly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 전략,,
    @Column(name="top_weekly_id")
    private Long id;

    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "music_id")
    private Music music;

    @Builder
    public TopWeekly (Music music, LocalDate date) {
        this.music = music;
        this.date = date;
    }
}
