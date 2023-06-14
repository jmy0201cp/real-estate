package com.realstate.home.repository;

import com.realstate.home.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
        Optional<Member> findByMemberName(String memberName);
}
