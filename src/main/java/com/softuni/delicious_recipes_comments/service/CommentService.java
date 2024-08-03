package com.softuni.delicious_recipes_comments.service;

import com.softuni.delicious_recipes_comments.model.dto.AddCommentDTO;
import com.softuni.delicious_recipes_comments.model.entity.Comment;
import com.softuni.delicious_recipes_comments.model.dto.CommentDTO;
import com.softuni.delicious_recipes_comments.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final Logger LOGGER = LoggerFactory.getLogger(CommentRepository.class);
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final Period retentionPeriod;


    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper, @Value("${comments.retention.period}") Period retentionPeriod) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.retentionPeriod = retentionPeriod;
    }

    public void addComment(AddCommentDTO addCommentDTO){
        Comment comment = modelMapper.map(addCommentDTO, Comment.class);
        commentRepository.save(comment);
    }


    public List<CommentDTO> getAllComments() {
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

    public void cleanUpOldOffers(){

        Instant now = Instant.now();
        Instant deleteBefore = now.minus(retentionPeriod);

        LOGGER.info("Removing old comments...");
        commentRepository.deleteOldComments(deleteBefore);
        LOGGER.info("Old comments were removed...");
    }
}
