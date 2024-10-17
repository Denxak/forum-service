package ait.cohort46.post.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    private String user;
    @Setter
    private String message;
    private LocalDateTime dateCreated;
    private Long likes;

    public Comment(String user, String message, LocalDateTime dateCreated, Long likes) {
        this.user = user;
        this.message = message;
        this.dateCreated = dateCreated;
        this.likes = likes;
    }
}