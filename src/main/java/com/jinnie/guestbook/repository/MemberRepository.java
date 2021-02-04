package com.jinnie.guestbook.repository;

import com.jinnie.guestbook.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {
}
