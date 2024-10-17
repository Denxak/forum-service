package ait.cohort46.post.service;

import ait.cohort46.post.dao.PostRepository;
import ait.cohort46.post.dto.CommentDto;
import ait.cohort46.post.dto.PostAddDto;
import ait.cohort46.post.dto.PostDto;
import ait.cohort46.post.dto.exception.PostNotFoundException;
import ait.cohort46.post.model.Comment;
import ait.cohort46.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final ModelMapper getModelMapper;
    private final PostRepository postRepository;

    @Override
    public PostDto addPost(String author, PostAddDto postAddDto) {
        Post post = getModelMapper.map(postAddDto, Post.class);
        post.setAuthor(author);
        post.setDateCreated(LocalDateTime.now());
        post.setLikes(0L);
        return getModelMapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public PostDto findPost(String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        return getModelMapper.map(post, PostDto.class);
    }

    @Override
    public void addLike(String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }

    @Override
    public Iterable<PostDto> findPostByAuthor(String author) {
        return postRepository.findAllByAuthor(author);
    }

    @Override
    public PostDto addComment(String id, String author, String comment) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        post.getComments().add(new Comment(author, comment, LocalDateTime.now(), 0L));
        return getModelMapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public PostDto removePost(String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);
        return getModelMapper.map(post, PostDto.class);
    }

    @Override
    public Iterable<PostDto> findPostsByTags(String values) {
        List<String> tags = Arrays.stream(values.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        return postRepository.findAllByTagsIn(tags);
    }

    @Override
    public Iterable<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        return postRepository.findAllByDateCreatedBetween(dateFrom.atTime(23, 59, 59), dateTo.atTime(23, 59, 59));
    }

    @Override
    public PostDto updatePost(String id, PostAddDto postAddDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        post.setTitle(postAddDto.getTitle());
        post.setContent(postAddDto.getContent());
        post.setTags(postAddDto.getTags());
        return getModelMapper.map(postRepository.save(post), PostDto.class);
    }
}
