package com.softuni.delicious_recipes_comments.service;

import com.softuni.delicious_recipes_comments.model.dto.AddCommentDTO;
import com.softuni.delicious_recipes_comments.model.entity.Comment;
import com.softuni.delicious_recipes_comments.model.dto.CommentDTO;
import com.softuni.delicious_recipes_comments.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    public void addComment(AddCommentDTO addCommentDTO){
        Comment comment = modelMapper.map(addCommentDTO, Comment.class);
        commentRepository.save(comment);
    }


    public List<CommentDTO> getAllOffers() {
        return commentRepository
                .findAll()
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }

    public CommentDTO findCommentById(Long id) {
        return commentRepository.findById(id)
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .orElseThrow(() -> new IllegalArgumentException("Not found!"));

    }
}
