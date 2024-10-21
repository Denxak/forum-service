package ait.cohort46.post.controller;

import ait.cohort46.post.dto.PostAddDto;
import ait.cohort46.post.dto.PostDto;
import ait.cohort46.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {

    private final PostService postService;

    @PostMapping("/post/{user}")
    public PostDto createPost(@PathVariable String user, @RequestBody PostAddDto content) {
        return postService.addPost(user, content);
    }

    @GetMapping("/post/{postId}")
    public PostDto findPost(@PathVariable String postId) {
        return postService.findPost(postId);
    }

    @PatchMapping("/post/{postId}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void likePost(@PathVariable String postId) {
        postService.addLike(postId);
    }

    @GetMapping("/posts/author/{user}")
    public Iterable<PostDto> findPostsByAuthor(@PathVariable String user) {
        return postService.findPostByAuthor(user);
    }

    @PatchMapping("/post/{postId}/comment/{commenter}")
    public PostDto addComment(@PathVariable String postId, @PathVariable String commenter, @RequestBody String comment) {
        return postService.addComment(postId, commenter, comment);
    }

    @DeleteMapping("/post/{postId}")
    public PostDto removePost(@PathVariable String postId) {
        return postService.removePost(postId);
    }

    @GetMapping("/posts/tags")
    public Iterable<PostDto> findPostsByTags(@RequestParam String values) {
        return postService.findPostsByTags(values);
    }

    @GetMapping("/posts/period")
    public Iterable<PostDto> findPostsByPeriod(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo) {
        return postService.findPostsByPeriod(dateFrom, dateTo);
    }

    @PatchMapping("/post/{postId}")
    public PostDto updatePost(@PathVariable String postId, @RequestBody PostAddDto postAddDto) {
        return postService.updatePost(postId, postAddDto);
    }
}
