package teamMurange.Murange.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private LocalDate createdAt;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    public void updateComment(String newContents, LocalDate newDate) {
        this.contents = newContents;
        this.createdAt = newDate;
    }

    // 테스트용으로 빌더에 id 추가
    @Builder
    public Comment(Long id, LocalDate createdAt, String contents, User userComment, Playlist playlistComment) {
        this.contents = contents;
        this.createdAt = createdAt;
        this.user = userComment;
        this.playlist = playlistComment;
    }

}
