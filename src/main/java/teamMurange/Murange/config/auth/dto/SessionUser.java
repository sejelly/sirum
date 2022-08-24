package teamMurange.Murange.config.auth.dto;


import teamMurange.Murange.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    private Long userId;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getImg_url();
        this.userId = user.getId();
    }
}
