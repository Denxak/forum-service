package ait.cohort46.post.dao;

import ait.cohort46.post.dto.PostDto;
import ait.cohort46.post.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, String> {

    Iterable<PostDto> findAllByAuthor(String author);

    Iterable<PostDto> findAllByTagsIn(List<String> tags);

    Iterable<PostDto> findAllByDateCreatedBetween(LocalDateTime dateFrom, LocalDateTime dateTo);
}
