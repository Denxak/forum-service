package ait.cohort46.post.service;

import ait.cohort46.post.dto.PostAddDto;
import ait.cohort46.post.dto.PostDto;

import java.time.LocalDate;

public interface PostService {
    PostDto addPost(String author, PostAddDto postAddDto);

    PostDto findPost(String id);

    void addLike(String id);

    Iterable<PostDto> findPostByAuthor(String author);

    PostDto addComment(String id, String author, String comment);

    PostDto removePost(String id);

    Iterable<PostDto> findPostsByTags(String tags);

    Iterable<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo);

    PostDto updatePost(String id, PostAddDto postAddDto);

}
