package teamMurange.Murange.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "badge")
public class Badge {
    @Id
    @Column(name = "badge_id")
    private Long id;

    @Column(name = "likes")
    private Integer likes;

    @OneToOne(mappedBy = "badge")
    private User user;

}
