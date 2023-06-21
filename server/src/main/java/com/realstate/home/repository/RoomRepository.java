package com.realstate.home.repository;

import com.realstate.home.domain.RoomType;
import com.realstate.home.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByRoomType(RoomType roomType);
}
