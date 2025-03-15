package me.dylee.springbootdeveloper.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.dylee.springbootdeveloper.domain.Article;
import me.dylee.springbootdeveloper.dto.AddArticleRequest;
import me.dylee.springbootdeveloper.dto.ArticleResponse;
import me.dylee.springbootdeveloper.dto.UpdateArticleRequest;
import me.dylee.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest reqeust, Principal principal) {
        Article savedArticle = blogService.save(reqeust, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id,
                                                 @RequestBody UpdateArticleRequest reqeust) {
        Article updatedArticle = blogService.update(id, reqeust);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
