package ait.cohort46.post.model;

import ait.cohort46.post.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Document
@NoArgsConstructor
public class Post {
    @Id
    private String id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String author;
    @Setter
    private LocalDateTime dateCreated;
    @Setter
    private Set<String> tags;
    @Setter
    private Long likes;
    @Setter
    private List<CommentDto> comments = new ArrayList<>();
}