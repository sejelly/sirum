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
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="playlist_id")
    private Long id;

    private String title;

    private String img_url;

    @Embedded
    private Figure figure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "playlist")
    private List<LikePlaylist> likePlaylist = new ArrayList<>();

    @OneToMany(mappedBy = "playlist")
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Playlist(Long id, String title, String img_url, Figure figure, Category category) {
        this.id = id;
        this.title = title;
        this.img_url = img_url;
        this.figure = figure;
        this.category = category;
    }


}
