package com.realstate.home.repository;

import com.realstate.home.domain.entity.Member;
import com.realstate.home.domain.entity.Room;
import com.realstate.home.domain.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByMemberAndRoom(Member member, Room room);

    Optional<List<Wishlist>> findAllByMember(Member member);
}
