package teamMurange.Murange.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "calendar")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    private Emotion firstEmotion;

    private Emotion secondEmotion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Calendar(LocalDate date, Emotion firstEmotion, Emotion secondEmotion, User user) {
        this.date = date;
        this.firstEmotion = firstEmotion;
        this.secondEmotion = secondEmotion;
        this.user = user;
    }

}
