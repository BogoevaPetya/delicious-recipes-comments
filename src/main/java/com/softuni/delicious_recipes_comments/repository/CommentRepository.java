package com.softuni.delicious_recipes_comments.repository;

import com.softuni.delicious_recipes_comments.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
