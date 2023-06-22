package com.realstate.home.repository;

import com.realstate.home.domain.entity.Comment;
import com.realstate.home.domain.entity.Member;
import com.realstate.home.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByRoomOrderByCreatedAtDesc(Room room);
}
