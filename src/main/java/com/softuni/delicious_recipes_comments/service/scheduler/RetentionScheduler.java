package com.softuni.delicious_recipes_comments.service.scheduler;

import com.softuni.delicious_recipes_comments.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RetentionScheduler {
    private final Logger LOGGER = LoggerFactory.getLogger(RetentionScheduler.class);
    private final CommentService commentService;

    public RetentionScheduler(CommentService commentService) {
        this.commentService = commentService;
    }

    @Scheduled(cron = "0 0 2 * * SAT")
    public void deleteOldRecords(){
        LOGGER.info("Start deleting old comments");
        commentService.cleanUpOldOffers();
        LOGGER.info("Already deleted");
    }
}
