package com.softuni.delicious_recipes_comments.web;

import com.softuni.delicious_recipes_comments.model.dto.AddCommentDTO;
import com.softuni.delicious_recipes_comments.model.dto.CommentDTO;
import com.softuni.delicious_recipes_comments.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments(){
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity<CommentDTO> addComment(@RequestBody AddCommentDTO addCommentDTO){
        commentService.addComment(addCommentDTO);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("id") Long id){
//        return ResponseEntity.ok(commentService.findCommentById(id));
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommentDTO> deleteById(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
