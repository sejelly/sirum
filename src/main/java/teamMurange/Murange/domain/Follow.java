package teamMurange.Murange.domain;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.ConnectionBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "follow")
public class Follow {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="follow_id") // 그냥 테이블 번호
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 사용자 본인
    private User user;

    @Column(name = "followee_id") // 팔로우하려는 인물
    private Long followee;

    @Builder
    public Follow (User user1, Long user2) {
        this.user = user1;
        this.followee = user2;
    }

}
