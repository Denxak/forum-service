package ait.cohort46.post.dto;

import lombok.Getter;

import java.util.Set;

@Getter
public class PostAddDto {
    private String title;
    private String content;
    private Set<String> tags;
}
